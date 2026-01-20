package com.carlos.EasyCart.infrastructure.dto;

public record UserRequestDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone,
        Boolean ativo
) {}