package com.example.springboot.dtos;

import com.example.springboot.models.MoveModel;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MoveRecordDto(
        @NotNull UUID productId,
        @NotNull Integer quantity,
        @NotNull MoveModel.TipoMovimentacao tipoMovimentacao
) {}
