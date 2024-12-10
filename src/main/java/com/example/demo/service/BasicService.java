package com.example.demo.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BasicService{
    List<Object> getAll();
    Optional<?> getById(int id);

    void insert(Object s);

    String deleteById(int id);

    ResponseEntity<?> update(Object body);

}
