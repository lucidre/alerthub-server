package com.alerthub.demo.events;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.events")
public class EventConfig {
    @Bean
    CommandLineRunner commandLineRunner(EventRepository EventRepository) {
        return args -> {

        };
    }
}
