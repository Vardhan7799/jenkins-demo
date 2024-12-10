package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements BasicService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Object> getAll() {
        return Arrays.asList(studentRepository.findAll().toArray());
    }

    @Override
    public Optional<?> getById(int Id){
       return studentRepository.findById(Id);
    }

    @Override
    public void insert(Object s) {
        studentRepository.save((Student) s);
    }

    @Override
    public String deleteById(int id){
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    @Override
    public ResponseEntity<?> update(Object body) {
        Student s=(Student)body;
        Optional<Student> s1=studentRepository.findById(s.getStuId());
        if(s1.isPresent()) {
            s1.get().setStuId(s.getStuId());
            s1.get().setFirstName(s.getFirstName() != null ? s.getFirstName() : s1.get().getFirstName());
            s1.get().setLastName(s.getLastName() != null ? s.getLastName() : s1.get().getLastName());
            s1.get().setTotalMarks(s.getTotalMarks() != 0 ? s.getTotalMarks() : s1.get().getTotalMarks());
            return ResponseEntity.ok(studentRepository.save(s1.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Student Id");
    }
}
