package com.teste.system.model;

import java.util.List;

import com.teste.system.dto.DisciplineRecord;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String workload;
    @OneToMany(mappedBy = "discipline")
    private List<StudentDiscipline> studentDisciplines;


    public double calculateAverageGrade() {
        return studentDisciplines.stream()
                .mapToDouble(StudentDiscipline::getNote)
                .average()
                .orElse(0.0);
    }

    
}
