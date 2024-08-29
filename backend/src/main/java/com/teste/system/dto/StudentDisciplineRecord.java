package com.teste.system.dto;

import com.teste.system.model.Discipline;
import com.teste.system.model.Student;

import jakarta.validation.constraints.NotBlank;

public record StudentDisciplineRecord(Long id,
                @NotBlank String name,
                double note,
                double frequency,
                Student student,
                Discipline discipline) {

}
