package com.teste.system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.system.Repositories.DisciplineRepository;
import com.teste.system.model.Discipline;
import com.teste.system.model.StudentDiscipline;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServices {
    @Autowired
    private DisciplineRepository disciplineRepository;

    public Discipline createDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineById(Long id) {
        return disciplineRepository.findById(id);
    }

    public Discipline updateDiscipline(Long id, Discipline disciplineDetails) {
        if (disciplineRepository.existsById(id)) {
            disciplineDetails.setId(id);
            return disciplineRepository.save(disciplineDetails);
        }
        return null;
    }

    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteById(id);
    }

    public double calculateClassAverage(Long disciplineId) {
        Optional<Discipline> disciplineOpt = disciplineRepository.findById(disciplineId);
        if (disciplineOpt.isPresent()) {
            Discipline discipline = disciplineOpt.get();
            return discipline.getStudentDisciplines().stream()
                    .mapToDouble(StudentDiscipline::getNote)
                    .average()
                    .orElse(0.0);
        }
        return 0.0;
    }

}
