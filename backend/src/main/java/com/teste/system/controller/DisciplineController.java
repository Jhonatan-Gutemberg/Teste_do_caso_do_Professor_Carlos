package com.teste.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teste.system.model.Discipline;
import com.teste.system.services.DisciplineServices;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discipline")
@CrossOrigin("*")
public class DisciplineController {

    @Autowired
    private DisciplineServices disciplineServices;

    @PostMapping
    public ResponseEntity<Discipline> createDiscipline(@RequestBody Discipline discipline) {
        Discipline createdDiscipline = disciplineServices.createDiscipline(discipline);
        return new ResponseEntity<>(createdDiscipline, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Discipline>> getAllDisciplines() {
        List<Discipline> disciplines = disciplineServices.getAllDisciplines();
        return new ResponseEntity<>(disciplines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        Optional<Discipline> discipline = disciplineServices.getDisciplineById(id);
        return discipline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id,
            @RequestBody Discipline disciplineDetails) {
        Discipline updatedDiscipline = disciplineServices.updateDiscipline(id, disciplineDetails);
        return updatedDiscipline != null ? ResponseEntity.ok(updatedDiscipline) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineServices.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}
