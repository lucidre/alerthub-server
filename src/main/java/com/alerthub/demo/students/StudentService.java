
package com.alerthub.demo.students;

 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> data = studentRepository.findStudentByEmail(student.getEmail());
        if (data.isPresent()) {
            throw new IllegalStateException("email taken");
      }
      studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
      studentRepository.delete(studentRepository.findById(id).get());
    }
}