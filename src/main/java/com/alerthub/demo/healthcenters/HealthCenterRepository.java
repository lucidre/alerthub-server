package com.alerthub.demo.healthcenters;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthCenterRepository extends MongoRepository<HealthCenter, String> {

    @Query("{ 'userId': ?0 }")
    Optional<HealthCenter> findByUid(String id);

    @Query("{ '$or': [ { 'fullName': { '$regex': ?0, '$options': 'i' } }, { 'location': { '$regex': ?0, '$options': 'i' } } ] }")
    Page<HealthCenter> searchByNameOrLocation(String query, Pageable pageable);

}
