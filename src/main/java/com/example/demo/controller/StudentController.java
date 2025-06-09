package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student/")
public class StudentController {
    @Autowired
    @Qualifier("studentService")
    private BasicService basicService;

    @GetMapping("test")
    public String test()
    {
        return "Hello Student Controller";
    }
    @PostMapping("add")
    public void postStudent(@RequestBody Student s)
    {
        basicService.insert(s);
    }

    @GetMapping("getAll")
    public List<Object> getAllStudents()
    {
        return basicService.getAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> findById(@PathVariable int id)
    {
        Optional<?> p=basicService.getById(id);
        if(p.isPresent())
            return ResponseEntity.ok((Student)p.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with given Id");
    }

    @DeleteMapping("delete/{id}")
    public String deleteById(@PathVariable int id){ return basicService.deleteById(id); }

    @PatchMapping("update")
    public ResponseEntity<?> update(@RequestBody Student s){ return basicService.update(s);}
}
