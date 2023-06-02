package com.example.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.estorebackend.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
  public List<Cart> findAllProductsByUserId(Integer userId);
}
