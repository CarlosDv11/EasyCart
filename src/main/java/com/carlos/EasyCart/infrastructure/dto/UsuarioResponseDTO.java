package com.carlos.EasyCart.infrastructure.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Integer id,
        String nome,
        String email,
        String telefone,
        Boolean ativo,
        LocalDateTime dataCriacao
) {}