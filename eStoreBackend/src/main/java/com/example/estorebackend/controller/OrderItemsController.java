package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.estorebackend.model.OrderItems;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.OrderItemsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orderitems")
public class OrderItemsController {

  @Autowired
	OrderItemsRepository repository;
	
	final String TAG = "Order Items";
	
	@PostMapping(path= "/add")
	public Response<OrderItems> addOrderItem(@RequestParam Integer orderId, @RequestParam Integer productId, @RequestParam String productTitle,
  @RequestParam String productDescription, @RequestParam String productCode, @RequestParam String productImg, @RequestParam String productCategory, @RequestParam Integer price,
  @RequestParam Integer quantity, @RequestParam Integer totalPrice) {
		Date date = new Date();
		
		OrderItems category = new OrderItems(null, orderId, productId, productTitle, productDescription, productCode, productImg, productCategory, price, quantity, totalPrice);
		repository.save(category);
		
		return new Response<OrderItems>(101, TAG+" Saved Successfully at "+date, null);
		
	} 
	
	@PostMapping(path = "/add-items", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<OrderItems> addAllOrderItems(@RequestBody List<OrderItems> orderItems) {
		Date date = new Date();
		List<OrderItems> response = (List<OrderItems>) repository.saveAll(orderItems);
		return new Response<OrderItems>(101, response.size()+" "+TAG+"s Saved Successfully at "+date, response);
	}
	
	@GetMapping(path="/get")
	public Response<OrderItems> getAllOrderItems(){
		ArrayList<OrderItems> list = (ArrayList<OrderItems>) repository.findAll();
		Date date = new Date();
		return new Response<OrderItems>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);	
	}
	

	@GetMapping(path = "/get/{orderId}")
	public Response<OrderItems> getOrderItemByOrderId(@PathVariable("orderId") Integer orderId){
		List<OrderItems> response = repository.findByOrderId(orderId);
		Date date = new Date();
		return new Response<OrderItems>(101, TAG+" Fetched Successfully at "+date, response);
	}
	
	@PostMapping(path= "/update")
	public Response<OrderItems> updateOrderItem(@RequestParam Integer orderItemId, @RequestParam Integer orderId, @RequestParam Integer productId, @RequestParam String productTitle,
  @RequestParam String productDescription, @RequestParam String productCode, @RequestParam String productImg, @RequestParam String productCategory, @RequestParam Integer price,
  @RequestParam Integer quantity, @RequestParam Integer totalPrice) {

		
		Date date = new Date();
		
		OrderItems category = new OrderItems(orderItemId, orderId, productId, productTitle, productDescription, productCode, productImg, productCategory, price, quantity, totalPrice);
		repository.save(category);
		
		return new Response<OrderItems>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<OrderItems> deleteOrderItem(@PathVariable("id") Integer id){
		
		OrderItems category = new OrderItems();
		category.setOrderItemId(id);
		repository.delete(category);
		
		Date date = new Date();
		return new Response<OrderItems>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
