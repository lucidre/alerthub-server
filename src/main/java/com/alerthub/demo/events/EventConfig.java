package com.alerthub.demo.events;

 

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    @Bean
    CommandLineRunner commandLineRunner(EventRepository EventRepository) {
        return args -> {

        };
    }
}
