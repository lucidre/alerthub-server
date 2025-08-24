package com.alerthub.demo.panic_modes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanicModeService {

    private final PanicModeRepository panicModeRepository;

    // 10 minutes in milliseconds
    private static final long PANIC_TIMEOUT_MS = 10 * 60 * 1000;

    @Autowired
    public PanicModeService(PanicModeRepository panicModeRepository) {
        this.panicModeRepository = panicModeRepository;
    }

    public void togglePanicMode(String uid, Double latitude, Double longitude, Boolean isOnOrOff,
            Boolean broadcastToCommunity, Boolean broadcastToProviders, Boolean broadcastToContacts) {

        if (uid == null) {
            throw new IllegalArgumentException("User Id cannot be null");
        }

        PanicMode model;
        final Optional<PanicMode> optionalData = panicModeRepository.findByUid(uid);

        if (optionalData.isPresent()) {
            model = optionalData.get();
            model.setUpdatedAt(System.currentTimeMillis());
            model.setLongitude(longitude);
            model.setLatitude(latitude);
            model.setBroadcastToContacts(broadcastToContacts);
            model.setBroadcastToProviders(broadcastToProviders);
            model.setBroadcastToCommunity(broadcastToCommunity);
            model.setIsOnOrOff(isOnOrOff);
            model.setUid(uid);
        } else {
            model = new PanicMode(uid, isOnOrOff, broadcastToCommunity, broadcastToProviders,
                    broadcastToContacts, latitude, longitude, System.currentTimeMillis());
        }

        panicModeRepository.save(model);
    }

    /**
     * Get list of currently active panic modes An active panic is one where: -
     * isOnOrOff is true - updatedAt is within the last 10 minutes
     *
     * @return List of active PanicMode objects, empty list if none active
     */
    public List<PanicMode> getActivePanics() {
        long currentTime = System.currentTimeMillis();
        long tenMinutesAgo = currentTime - PANIC_TIMEOUT_MS;

        // Get all panics that are turned on and updated within last 10 minutes
        List<PanicMode> activePanics = panicModeRepository.findActivePanics(true, tenMinutesAgo);

        return activePanics;
    }
}
