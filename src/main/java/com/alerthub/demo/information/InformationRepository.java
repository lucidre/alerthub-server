package com.alerthub.demo.information;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends MongoRepository<Information, String> {

}
