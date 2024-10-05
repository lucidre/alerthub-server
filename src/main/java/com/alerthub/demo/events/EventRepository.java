package com.alerthub.demo.events;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    @Query("{ 'creatorId': ?0 }")
    List<Event> findByCreatorId(String creatorId, Pageable pageable);

    @Query("{ 'creatorId': ?0, 'updatedAt': { $lt: ?1 } }")
    List<Event> findByCreatorIdAndUpdatedAtLessThan(String creatorId, Integer updatedAt, Pageable pageable);

    @Query("{ 'updatedAt': { $lt: ?0 } }")
    List<Event> findByUpdatedAtLessThan(Integer updatedAt, Pageable pageable);

    @Query("{ '$or': [ { 'name': { '$regex': ?0, '$options': 'i' } }, { 'location': { '$regex': ?0, '$options': 'i' } } ] }")
    Page<Event> searchByNameOrLocation(String query, Pageable pageable);

}
