package com.alerthub.demo.users;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.users")
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository EventRepository) {
        return args -> {

        };
    }
}
