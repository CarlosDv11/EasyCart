package com.carlos.EasyCart.infrastructure.repository;

import com.carlos.EasyCart.infrastructure.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {}