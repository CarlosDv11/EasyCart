package com.carlos.EasyCart.infrastructure.dto;

import java.math.BigDecimal;

public record ProductRequestDTO(String nome, String descricao, BigDecimal preco, Integer estoque) {}
