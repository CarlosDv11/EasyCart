package com.carlos.EasyCart.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(Integer id, String nome, String descricao, BigDecimal preco, Integer estoque, LocalDateTime dataCriacao) {}
