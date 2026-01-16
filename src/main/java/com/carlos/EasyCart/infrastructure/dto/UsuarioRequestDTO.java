package com.carlos.EasyCart.infrastructure.dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha,
        String cpf,
        String telefone,
        Boolean ativo
) {}