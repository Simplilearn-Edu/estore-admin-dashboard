package com.example.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.estorebackend.model.Wishlist;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
	
	
	public List<Wishlist> findAllProductsByUserId(Integer userId);
}
