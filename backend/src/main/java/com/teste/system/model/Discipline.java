package com.teste.system.model;

import java.util.List;

import com.teste.system.dto.DisciplineRecord;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String workload;
    @OneToMany(mappedBy = "discipline")
    private List<StudentDiscipline> studentDisciplines;

    public Discipline(DisciplineRecord date) {
        this.name = date.name();
        this.workload = date.workload();
    }

    public Discipline() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public double calculateAverageGrade() {
        return studentDisciplines.stream()
                .mapToDouble(StudentDiscipline::getNote)
                .average()
                .orElse(0.0);
    }

    public List<StudentDiscipline> getStudentDisciplines() {
        return studentDisciplines;
    }

    public void setStudentDisciplines(List<StudentDiscipline> studentDisciplines) {
        this.studentDisciplines = studentDisciplines;
    }
}
