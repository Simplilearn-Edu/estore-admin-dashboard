package com.example.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.estorebackend.model.Products;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
