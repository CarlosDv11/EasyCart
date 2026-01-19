package com.carlos.EasyCart.infrastructure.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(String nome, String descricao, BigDecimal preco, Integer estoque) {}
