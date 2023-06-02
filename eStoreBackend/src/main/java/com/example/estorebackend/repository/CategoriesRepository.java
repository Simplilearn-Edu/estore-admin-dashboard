package com.example.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.estorebackend.model.Categories;

@Repository
public interface CategoriesRepository extends CrudRepository<Categories, Integer> {

}
