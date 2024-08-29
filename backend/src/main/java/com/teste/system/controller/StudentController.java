package com.teste.system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teste.system.Repositories.DisciplineRepository;
import com.teste.system.Repositories.StudentDisciplineRepository;
import com.teste.system.Repositories.StudentRepository;
import com.teste.system.model.Discipline;
import com.teste.system.model.Student;
import com.teste.system.model.StudentDiscipline;
import com.teste.system.services.StudentServices;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private StudentDisciplineRepository studentDisciplineRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentServices.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/add-disciplines")
    public Student addDisciplinesToStudent(@PathVariable Long studentId, @RequestBody List<Long> disciplineIds) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        List<Discipline> disciplines = disciplineRepository.findAllById(disciplineIds);

        for (Discipline discipline : disciplines) {
            StudentDiscipline studentDiscipline = new StudentDiscipline();
            studentDiscipline.setStudent(student);
            studentDiscipline.setDiscipline(discipline);
            studentDisciplineRepository.save(studentDiscipline);
        }

        student.setStudentDisciplines(studentDisciplineRepository.findByStudentId(studentId));
        return studentRepository.save(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServices.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        Optional<Student> student = studentServices.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentServices.updateStudent(id, studentDetails);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        try {
            studentServices.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}