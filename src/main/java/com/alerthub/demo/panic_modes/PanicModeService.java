package com.alerthub.demo.panic_modes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alerthub.demo.users.User;
import com.alerthub.demo.users.UserService;

@Service
public class PanicModeService {
    private static final long PANIC_TIMEOUT_MS = 10 * 60 * 1000;
    private final PanicModeRepository panicModeRepository;
    private final UserService userService;


    @Autowired
    public PanicModeService(PanicModeRepository panicModeRepository, UserService userService) {


        this.panicModeRepository = panicModeRepository;
        this.userService = userService;

    }



    public void togglePanicMode(
            String uid,
            Double latitude,
            Double longitude,
            Boolean isOnOrOff,
            Boolean broadcastToCommunity,
            Boolean broadcastToProviders,
            Boolean broadcastToContacts
    ) {
    if (uid == null) {
        throw new IllegalArgumentException("User Id cannot be null");
    }

    // Fetch all PanicMode records for the user
    List<PanicMode> panicModes = panicModeRepository.findAllByUid(uid);

    PanicMode model;

    if (panicModes.isEmpty()) {
        // No existing record: create new
        model = new PanicMode(uid, isOnOrOff, broadcastToCommunity, broadcastToProviders,
                broadcastToContacts, latitude, longitude, System.currentTimeMillis());
    } else {
        // Update the first record
        model = panicModes.get(0);
        model.setUpdatedAt(System.currentTimeMillis());
        model.setLongitude(longitude);
        model.setLatitude(latitude);
        model.setBroadcastToContacts(broadcastToContacts);
        model.setBroadcastToProviders(broadcastToProviders);
        model.setBroadcastToCommunity(broadcastToCommunity);
        model.setIsOnOrOff(isOnOrOff);
        model.setUid(uid);

        // Delete duplicates if any
        if (panicModes.size() > 1) {
            List<PanicMode> duplicates = panicModes.subList(1, panicModes.size());
            panicModeRepository.deleteAll(duplicates);
        }
    }

    // Save the updated or newly created model
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

        activePanics.forEach(panic -> {
            Optional<User> userOpt = userService.getOptionalUser(panic.getUid());
            userOpt.ifPresent(user -> {
                panic.setUser(user);
            });
        });

        return activePanics;
    }
}
