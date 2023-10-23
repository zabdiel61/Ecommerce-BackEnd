package com.compras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compras.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
