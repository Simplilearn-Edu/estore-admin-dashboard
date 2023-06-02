package com.example.estorebackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.estorebackend.model.Orders;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {

	public List<Orders> findByOrderStatus(Integer orderStatus);
	public List<Orders> findByUserId(Integer userId);
}
