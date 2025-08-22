package com.alerthub.demo.healthcenters;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.healthcenters")
public class HealthCenterConfig {

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner commandLineRunner(HealthCenterRepository HealthCenterRepository) {
        return args -> {

        };
    }
}
