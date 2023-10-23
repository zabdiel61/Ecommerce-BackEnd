package com.compras.services;

import java.util.List;

import com.compras.models.entities.Product;

public interface ProductService {

	List<Product> findAll();
}
