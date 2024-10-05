package com.alerthub.demo.notifications;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.notifications")
public class NotificationConfig {
    @Bean
    CommandLineRunner commandLineRunner(NotificationRepository NotificationRepository) {
        return args -> {

        };
    }
}
