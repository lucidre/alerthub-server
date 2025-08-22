package com.alerthub.demo.healthcenters;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HealthCenterService {

    private final HealthCenterRepository healthcenterRepository;
    private static final double EARTH_RADIUS = 6371.0; // Earth radius in kilometers

    @Autowired
    public HealthCenterService(HealthCenterRepository healthcenterRepository) {
        this.healthcenterRepository = healthcenterRepository;

    }

    private PageRequest pageRequest(Integer page) {
        return PageRequest.of(page, 80, Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    public HealthCenter getHealthCenter(String id) {
        if (id == null) {
            throw new IllegalArgumentException("HealthCenter ID must not be null");
        }

        Optional<HealthCenter> optionalHealthCenter = healthcenterRepository.findByUid(id);
        if (optionalHealthCenter.isPresent()) {
            HealthCenter healthcenter = optionalHealthCenter.get();
            return healthcenter;
        } else {
            throw new IllegalStateException("HealthCenter not found");
        }
    }

    private boolean isWithinRadius(double healthcenterLat, double healthcenterLng, double lat, double lng, double radius) {
        double latDistance = Math.toRadians(healthcenterLat - lat);
        double lngDistance = Math.toRadians(healthcenterLng - lng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(healthcenterLat))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;
        return distance <= radius;
    }

    public List<HealthCenter> getNearbyHealthCenters(Double radius, Double lng, Double lat, Integer page) {

        if (radius == null || lat == null || lng == null) {
            throw new IllegalArgumentException("Radius, Latitude, and Longitude cannot be null");
        }

        List<HealthCenter> healthcenters = healthcenterRepository.findAll();

        return healthcenters.stream()
                .filter(healthcenter -> isWithinRadius(healthcenter.getLat(), healthcenter.getLng(), lat, lng, radius))
                .collect(Collectors.toList());
    }

    public List<HealthCenter> getMapHealthCenters(Double radius, Double lng, Double lat) {

        if (radius == null || lat == null || lng == null) {
            throw new IllegalArgumentException("Radius, Latitude, and Longitude cannot be null");
        }

        List<HealthCenter> healthcenters = healthcenterRepository.findAll();
        return healthcenters;
    }

    public List<HealthCenter> searchHealthCenters(String query, Integer page) {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        PageRequest pageable = pageRequest(page);
        return healthcenterRepository.searchByNameOrLocation(query, pageable).getContent();
    }

    public void createHealthCenter(HealthCenter healthcenter) {
        if (healthcenter == null) {
            throw new IllegalArgumentException("HealthCenter cannot be null");
        }
        healthcenter.setMongoId(null);

        healthcenterRepository.save(healthcenter);
    }

    public void editHealthCenter(String uid, HealthCenter center) {
        if (uid == null || center == null) {
            throw new IllegalArgumentException("HealthCenter ID, User ID, and HealthCenter must not be null");
        }

        Optional<HealthCenter> optionalCenter = healthcenterRepository.findByUid(uid);

        if (optionalCenter.isPresent()) {
            HealthCenter existingCenter = optionalCenter.get();

            center.setMongoId(existingCenter.getMongoId());
            center.setUserId(existingCenter.getUserId());
            healthcenterRepository.save(center);

        } else {
            throw new IllegalStateException("HealthCenter not found");
        }
    }

    public void deleteHealthCenter(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException(" User ID must not be null");
        }

        Optional<HealthCenter> optionalCenter = healthcenterRepository.findByUid(uid);

        if (optionalCenter.isPresent()) {
            HealthCenter center = optionalCenter.get();
            healthcenterRepository.deleteById(center.getMongoId());

        } else {
            throw new IllegalStateException("HealthCenter not found");
        }
    }

}
