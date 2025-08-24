package com.alerthub.demo.panic_modes;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class ClientInfo {

    private final SseEmitter emitter;
    private final Double userLatitude;
    private final Double userLongitude;
    private final Double maxDistance;

    public ClientInfo(SseEmitter emitter, Double userLatitude, Double userLongitude, Double maxDistance) {
        this.emitter = emitter;
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
        this.maxDistance = maxDistance;
    }

    public SseEmitter getEmitter() {
        return emitter;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }
}
