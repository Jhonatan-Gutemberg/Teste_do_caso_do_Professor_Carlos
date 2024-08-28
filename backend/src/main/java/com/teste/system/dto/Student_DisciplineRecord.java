package com.teste.system.dto;

import jakarta.validation.constraints.NotBlank;

public record Student_DisciplineRecord(Long id,
        @NotBlank String name,
        double note,
        double frequency) {

}
