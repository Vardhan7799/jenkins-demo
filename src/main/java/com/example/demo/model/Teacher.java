package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tId;
    private String fullName;
    private String subject;
    private String courseId;
}
