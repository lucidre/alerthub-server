package com.alerthub.demo.drivers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.drivers")
public class DriverConfig {

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner commandLineRunner(DriverRepository DriverRepository) {
        return args -> {

        };
    }
}
