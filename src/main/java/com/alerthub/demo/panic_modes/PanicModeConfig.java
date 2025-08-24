package com.alerthub.demo.panic_modes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.alerthub.demo.panic_modes")
public class PanicModeConfig {
    @Bean
    CommandLineRunner commandLineRunner(PanicModeRepository PanicModeRepository) {
        return args -> {

        };
    }
}
