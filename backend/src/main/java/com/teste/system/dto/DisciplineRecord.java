package com.teste.system.dto;

import jakarta.validation.constraints.NotBlank;

public record DisciplineRecord(
    @NotBlank Long id,
    String name,
    String workload
) {
    
}
