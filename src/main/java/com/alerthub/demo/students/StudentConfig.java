package com.alerthub.demo.students;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
         /*    Student student = new Student("Lorem", LocalDate.now(), "email", 20);
            Student student2 = new Student("Lorem", LocalDate.now(), "email", 20);
            Student student3 = new Student("Lorem", LocalDate.now(), "email", 20);

            studentRepository.saveAll(List.of(student, student2, student3)); */
        };
    }
}
