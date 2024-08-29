package com.teste.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teste.system.Repositories.StudentDisciplineRepository;
import com.teste.system.model.StudentDiscipline;
import java.util.List;

@RestController
@RequestMapping("/student-discipline")
@CrossOrigin("*")
public class StudentDisciplinaController {
    
    @Autowired
    private StudentDisciplineRepository studentDisciplineRepository;

    @PostMapping
    public ResponseEntity<StudentDiscipline> create(@RequestBody StudentDiscipline studentDiscipline) {
        StudentDiscipline saved = studentDisciplineRepository.save(studentDiscipline);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<StudentDiscipline> getAll() {
        return studentDisciplineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDiscipline> getById(@PathVariable Long id) {
        return studentDisciplineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
