package com.alerthub.demo.information;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.informations")
public class InformationConfig {

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner commandLineRunner(InformationRepository InformationRepository) {
        return args -> {

        };
    }
}
