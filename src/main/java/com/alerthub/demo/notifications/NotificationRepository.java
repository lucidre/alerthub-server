package com.alerthub.demo.notifications;

 
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notifications, String> {

    @Query("{ 'uid': ?0 }")
    Optional<Notifications> findByUid(String uid);
 
 

}
