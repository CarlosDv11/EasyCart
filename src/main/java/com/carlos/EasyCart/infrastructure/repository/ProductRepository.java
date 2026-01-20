package com.carlos.EasyCart.infrastructure.repository;

import com.carlos.EasyCart.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}