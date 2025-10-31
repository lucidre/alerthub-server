package com.alerthub.demo.panic_modes;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PanicModeRepository extends MongoRepository<PanicMode, String> {

    @Query("{ 'uid': ?0 }")
    List<PanicMode> findAllByUid(String uid);

    @Query("{ 'isOnOrOff': ?0 }")
    List<PanicMode> findActivePanics(Boolean isOnOrOff, Long minUpdatedAt);
}
