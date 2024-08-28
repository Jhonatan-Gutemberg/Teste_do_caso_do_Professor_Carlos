package com.teste.system.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public record StudentRecord(Long id,
        @NotBlank String name,
        String email,
        String address,
        String registration,
        Date dateBirth) {

}
