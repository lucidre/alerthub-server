package com.alerthub.demo.drivers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private static final double EARTH_RADIUS = 6371.0; // Earth radius in kilometers

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;

    }

    public List<Driver> getAllHealthCenterDrivers(String id, Integer page) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        PageRequest pageable = pageRequest(page);
        return driverRepository.getAllHealthCenterDrivers(id, pageable).getContent();
    }

    private PageRequest pageRequest(Integer page) {
        return PageRequest.of(page, 80, Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    public Driver getDriver(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Driver ID must not be null");
        }

        Optional<Driver> optionalDriver = driverRepository.findByUid(id);
        if (optionalDriver.isPresent()) {
            Driver driver = optionalDriver.get();
            return driver;
        } else {
            throw new IllegalStateException("Driver not found");
        }
    }

    public void createDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        driver.setId(null);

        driverRepository.save(driver);
    }

    public void editDriver(String uid, Driver center) {
        if (uid == null || center == null) {
            throw new IllegalArgumentException("Driver ID, User ID, and Driver must not be null");
        }

        Optional<Driver> optionalCenter = driverRepository.findByUid(uid);

        if (optionalCenter.isPresent()) {
            Driver existingCenter = optionalCenter.get();

            center.setId(existingCenter.getId()); 
            driverRepository.save(center);

        } else {
            throw new IllegalStateException("Driver not found");
        }
    }

    public void deleteDriver(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException(" User ID must not be null");
        }

        Optional<Driver> optionalCenter = driverRepository.findByUid(uid);

        if (optionalCenter.isPresent()) {
            Driver center = optionalCenter.get();
            driverRepository.deleteById(center.getId());

        } else {
            throw new IllegalStateException("Driver not found");
        }
    }

}
