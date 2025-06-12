package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1, "John", "Doe", 85); // Adjust constructor based on your class
    }

    @Test
    void testGetAll() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Object> result = studentService.getAll();
        assertEquals(1, result.size());
        assertTrue(result.get(0) instanceof Student);
    }

    @Test
    void testGetById_Existing() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Optional<?> result = studentService.getById(1);
        assertTrue(result.isPresent());
        assertEquals("John", ((Student) result.get()).getFirstName());
    }

    @Test
    void testGetById_NotFound() {
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        Optional<?> result = studentService.getById(2);
        assertTrue(result.isEmpty());
    }

    @Test
    void testInsert() {
        studentService.insert(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteById() {
        String result = studentService.deleteById(1);
        verify(studentRepository).deleteById(1);
        assertEquals("Student deleted successfully", result);
    }

    @Test
    void testUpdate_ValidStudent() {
        Student updatedStudent = new Student(1, "Jane", "Doe", 90);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        ResponseEntity<?> response = studentService.update(updatedStudent);
        assertEquals(200, response.getStatusCodeValue());

        Student savedStudent = (Student) response.getBody();
        assertEquals("Jane", savedStudent.getFirstName());
        assertEquals("Doe", savedStudent.getLastName()); // kept old
        assertEquals(90, savedStudent.getTotalMarks());
    }

    @Test
    void testUpdate_InvalidStudent() {
        Student updatedStudent = new Student(2, "Jane", null, 90);
        when(studentRepository.findById(2)).thenReturn(Optional.empty());

        ResponseEntity<?> response = studentService.update(updatedStudent);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Invalid Student Id", response.getBody());
    }
}
