package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateRecordDto(@NotBlank String name, @NotNull BigDecimal value, @NotNull Long categoryId) {

}

