package com.boostmytool.beststore.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boostmytool.beststore.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer>{

}
