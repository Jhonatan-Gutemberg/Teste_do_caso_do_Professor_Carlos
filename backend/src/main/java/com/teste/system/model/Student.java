package com.teste.system.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    private String email;
    private String address;

    private double averageGrade;

    private String registration;
    private double frequency;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateBirth;

    @OneToMany(mappedBy = "student")
    private List<StudentDiscipline> StudentDisciplines;

    public double calculateAverageGrade() {
        return StudentDisciplines.stream()
                .mapToDouble(StudentDiscipline::getNote)
                .average()
                .orElse(0.0);
    }

    public double calculateFrequency() {
        return StudentDisciplines.stream()
                .mapToDouble(StudentDiscipline::getFrequency)
                .average()
                .orElse(0.0);
    }

}
