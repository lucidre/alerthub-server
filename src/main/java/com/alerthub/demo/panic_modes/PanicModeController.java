package com.alerthub.demo.panic_modes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.alerthub.demo.NetworkResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping(path = "api/v1/panic")
@Tag(name = "PanicMode", description = "PanicMode APIs")
public class PanicModeController {

    private final String fetchSuccessful = "Operation Successful";
    private final PanicModeService panicModeService;

    // Store active SSE connections with client location info
    private final List<ClientInfo> clientInfos = new CopyOnWriteArrayList<>();

    @Autowired
    public PanicModeController(PanicModeService panicModeService) {
        this.panicModeService = panicModeService;
    }

    @PostMapping(path = "{uid}")
    @Operation(summary = "Toggle Panic Mode", description = "")
    public ResponseEntity<NetworkResult> togglePanicMode(
            @PathVariable String uid,
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Boolean isOnOrOff,
            @RequestParam Boolean broadcastToCommunity,
            @RequestParam Boolean broadcastToProviders,
            @RequestParam Boolean broadcastToContacts
    ) {
        try {
            panicModeService.togglePanicMode(uid, latitude, longitude, isOnOrOff,
                    broadcastToCommunity, broadcastToProviders, broadcastToContacts);

            // After updating panic state, notify all connected clients
            notifyPanicUpdate();

            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listen", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Listen to Panic Updates", description = "SSE endpoint for real-time panic updates within specified range")
    public SseEmitter listenToPanic(
            @RequestParam Double userLatitude,
            @RequestParam Double userLongitude,
            @RequestParam Double maxDistance) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // No timeout

        // Create a client info object to store location and distance preferences
        ClientInfo clientInfo = new ClientInfo(emitter, userLatitude, userLongitude, maxDistance);

        // Add client info to active connections
        clientInfos.add(clientInfo);

        // Remove emitter when connection closes
        emitter.onCompletion(() -> clientInfos.removeIf(info -> info.getEmitter().equals(emitter)));
        emitter.onTimeout(() -> clientInfos.removeIf(info -> info.getEmitter().equals(emitter)));
        emitter.onError((ex) -> clientInfos.removeIf(info -> info.getEmitter().equals(emitter)));

        try {
            // Send initial panic list within range when client connects
            List<PanicMode> nearbyPanics = getFilteredPanics(userLatitude, userLongitude, maxDistance);
            emitter.send(SseEmitter.event()
                    .name("panic-update")
                    .data(nearbyPanics));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    /**
     * Notify all connected clients about panic updates based on their location
     * preferences
     */
    private void notifyPanicUpdate() {

        List<PanicMode> allActivePanics = panicModeService.getActivePanics();

        // Create a list of client infos to remove (dead connections)
        List<ClientInfo> deadClients = new CopyOnWriteArrayList<>();

        for (ClientInfo clientInfo : clientInfos) {
            try {
                // Filter panics based on client's location and max distance
                List<PanicMode> nearbyPanics = filterPanicsForClient(allActivePanics, clientInfo);

                clientInfo.getEmitter().send(SseEmitter.event()
                        .name("panic-update")
                        .data(nearbyPanics));
            } catch (IOException e) {
                deadClients.add(clientInfo);
            }
        }

        // Remove dead connections
        clientInfos.removeAll(deadClients);
    }

    /**
     * Filter panics based on client's location and distance preferences
     */
    private List<PanicMode> filterPanicsForClient(List<PanicMode> allPanics, ClientInfo clientInfo) {
        return allPanics.stream()
                .filter(panic -> {
                    double distance = calculateDistance(
                            panic.getLatitude(),
                            panic.getLongitude(),
                            clientInfo.getUserLatitude(),
                            clientInfo.getUserLongitude()
                    );
                    return distance <= clientInfo.getMaxDistance();
                })
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Get filtered panics within specified range
     */
    private List<PanicMode> getFilteredPanics(Double userLatitude, Double userLongitude, Double maxDistance) {
        List<PanicMode> allActivePanics = panicModeService.getActivePanics();
        return allActivePanics.stream()
                .filter(panic -> {
                    double distance = calculateDistance(
                            panic.getLatitude(),
                            panic.getLongitude(),
                            userLatitude,
                            userLongitude
                    );
                    return distance <= maxDistance;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Calculate distance between two coordinates
     *
     * @param panicLat Panic latitude
     * @param panicLng Panic longitude
     * @param userLat User latitude
     * @param userLng User longitude
     * @return Distance in kilometers (or your preferred unit)
     */
    private double calculateDistance(Double panicLat, Double panicLng, Double userLat, Double userLng) {
        // Haversine formula for calculating distance between two points on Earth
        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(userLat - panicLat);
        double lonDistance = Math.toRadians(userLng - panicLng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(panicLat)) * Math.cos(Math.toRadians(userLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km

        return distance;
    }

}
