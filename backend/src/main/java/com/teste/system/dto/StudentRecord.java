package com.teste.system.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teste.system.model.StudentDiscipline;

import jakarta.validation.constraints.NotBlank;

public record StudentRecord(Long id,
        @NotBlank String name,
        String email,
        String address,
        String registration,
        double frequency,
        Date dateBirth,
        @JsonIgnore List<StudentDiscipline> studentDisciplines) {

}
