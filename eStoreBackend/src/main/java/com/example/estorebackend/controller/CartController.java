package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.example.estorebackend.model.Cart;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartRepository repository;

  final String TAG = "Cart";

  @PostMapping(path= "/add")
	public Response<Cart> addCart(@RequestParam Integer productId, @RequestParam Integer userId) {
		Date date = new Date();
		
		Cart admin = new Cart(null, productId, userId);
		repository.save(admin);
		
		return new Response<Cart>(101, TAG+" Saved Successfully at "+date, Arrays.asList(admin));
		
	}
	
	@GetMapping(path="/get")
	public Response<Cart> getCarts(){
		
		ArrayList<Cart> list = new ArrayList<Cart>();
		repository.findAll().forEach((admin) -> list.add(admin));
		
		Date date = new Date();
		return new Response<Cart>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);	
	}
	
	@GetMapping(path = "/get-user-cart/{userId}")
	public Response<Cart> getUserCartProducts(@PathVariable("userId") Integer userId) {
		Date date = new Date();
		ArrayList<Cart> list = new ArrayList<Cart>();
		repository.findAllProductsByUserId(userId).forEach((cart) -> list.add(cart));
		
		return new Response<Cart>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
	}

	@GetMapping(path = "/get/{id}")
	public Response<Cart> getCartItemsByUserId(@PathVariable("id") Integer id){
		
		ArrayList<Cart> list = new ArrayList<Cart>();
		Cart admin = repository.findById(id).get();
		list.add(admin);
		
		Date date = new Date();
		return new Response<Cart>(101, TAG+" Fetched Successfully at "+date, list);
		
	}
	
	@PostMapping(path= "/update")
	public Response<Cart> updateUserCart(@RequestParam Integer cartId, @RequestParam Integer productId, @RequestParam Integer userId) {
    Date date = new Date();
		Cart admin = new Cart(cartId, productId, userId);
		repository.save(admin);
		
		return new Response<Cart>(101, TAG+" Updated Successfully at "+date, Arrays.asList(admin));
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Cart> deleteItemFromUserCart(@PathVariable("id") Integer id){
		
		Cart admin = new Cart();
		admin.setCartId(id);
		repository.delete(admin);
		
		Date date = new Date();
		return new Response<Cart>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
