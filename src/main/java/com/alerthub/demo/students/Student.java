/* package com.alerthub.demo.students;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table
public class Student {
    private String name;
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private LocalDate dob;
    private String email;
    private Integer age;

    public Student(String name, Long id, LocalDate dob, String email, Integer age) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, LocalDate dob, String email, Integer age) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Students [name=" + name + ", id=" + id + ", dob=" + dob + ", email=" + email + ", age=" + age + "]";
    }

}
 */