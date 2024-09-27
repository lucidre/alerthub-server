
package com.alerthub.demo.events;

 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository studentRepository;
    
    @Autowired
    public EventService(EventRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Event> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Event student) {
       /*  Optional<Event> data = studentRepository.findStudentByEmail(student.getEmail());
        if (data.isPresent()) {
            throw new IllegalStateException("email taken");
      }
      studentRepository.save(student); */
    }

    public void deleteStudent(Long id) {
    //   studentRepository.delete(studentRepository.findById(id).get());
    }
}