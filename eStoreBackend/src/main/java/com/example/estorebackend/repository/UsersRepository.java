package com.example.estorebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.estorebackend.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
	public Optional<Users> findByEmailAndPassword(String email, String password);

}
