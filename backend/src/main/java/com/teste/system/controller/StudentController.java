package com.teste.system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teste.system.Repositories.StudentDisciplineRepository;
import com.teste.system.Repositories.StudentRepository;
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
    private StudentDisciplineRepository studentDisciplineRepository;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentServices.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/add-disciplines")
    public ResponseEntity<Student> addDisciplinesToStudent(@PathVariable Long studentId,
            @RequestBody List<StudentDiscipline> studentDisciplines) {
        Student student = studentRepository.findById(studentId).orElseThrow();

        for (StudentDiscipline sd : studentDisciplines) {
            sd.setStudent(student);
            studentDisciplineRepository.save(sd);
        }

        student.setStudentDisciplines(studentDisciplineRepository.findByStudentId(studentId));
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update-frequency/{id}")
    public ResponseEntity<Student> updateFrequency(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        List<StudentDiscipline> disciplines = studentDisciplineRepository.findByStudent(student);
        double totalNotes = 0;
        int count = disciplines.size();

        for (StudentDiscipline sd : disciplines) {
            totalNotes += sd.getNote();
        }

        double averageNote = count > 0 ? totalNotes / count : 0;

        student.setFrequency(averageNote);
        studentRepository.save(student);

        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentServices.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
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

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateStudentData(@PathVariable Long id) {
        studentServices.updateStudentData(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/average-grade")
    public ResponseEntity<Double> getStudentAverageGrade(@PathVariable Long id) {
        double averageGrade = studentServices.getStudentAverageGrade(id);
        return ResponseEntity.ok(averageGrade);
    }

    @GetMapping("/{id}/frequency")
    public ResponseEntity<Double> getStudentFrequency(@PathVariable Long id) {
        double frequency = studentServices.getStudentFrequency(id);
        return ResponseEntity.ok(frequency);
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

    @GetMapping("/below-75-percent-frequency")
    public ResponseEntity<List<Student>> getStudentsBelow75PercentFrequency() {
        List<Student> students = studentServices.getStudentsBelow75PercentFrequency();
        return ResponseEntity.ok(students);
    }
}