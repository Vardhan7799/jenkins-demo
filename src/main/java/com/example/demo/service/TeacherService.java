package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements BasicService{

    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public List<Object> getAll() {
        return Arrays.asList(teacherRepository.findAll().toArray());
    }

    @Override
    public Optional<?> getById(int id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void insert(Object s) {
        teacherRepository.save((Teacher) s);
    }

    @Override
    public String deleteById(int id) {
        teacherRepository.deleteById(id);
        return "Teacher deleted Successfully";
    }

    @Override
    public ResponseEntity<?> update(Object body) {
        Teacher t=(Teacher)body;
        Optional<Teacher> t1=teacherRepository.findById(t.getTId());
        if(t1.isPresent()) {
            t1.get().setTId(t.getTId());
            t1.get().setFullName(t.getFullName() != null ? t.getFullName() : t1.get().getFullName());
            t1.get().setCourseId(t.getCourseId() != null ? t.getCourseId() : t1.get().getCourseId());
            t1.get().setSubject(t.getSubject() != null ? t.getSubject() : t1.get().getSubject());
            return ResponseEntity.ok(teacherRepository.save(t1.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Teacher Id");
    }


}
