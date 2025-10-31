package com.alerthub.demo.drivers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {

    @Query("{ 'userId': ?0 }")
    Optional<Driver> findByUid(String id);

    @Query("{ 'healthCenterId': ?0 }")
    Page<Driver> getAllHealthCenterDrivers(String id, Pageable pageable);

}
