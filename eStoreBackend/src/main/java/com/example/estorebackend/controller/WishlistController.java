package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Date;

import com.example.estorebackend.model.Response;
import com.example.estorebackend.model.Wishlist;
import com.example.estorebackend.repository.WishlistRepository;

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
@RequestMapping("/wishlist")
public class WishlistController {

  @Autowired
  WishlistRepository repository;
  
  final String TAG = "Wishlist";

  @PostMapping(path= "/add")
	public Response<Wishlist> addItemToWishlist(@RequestParam Integer productId, @RequestParam Integer userId) {
		Date date = new Date();
		
		Wishlist wishlist = new Wishlist(null, productId, userId);
		repository.save(wishlist);
		
		return new Response<Wishlist>(101, TAG+" Saved Successfully at "+date, null);
		
	}
	
	@GetMapping(path="/get")
	public Response<Wishlist> getWishlistItems(){
		
		ArrayList<Wishlist> list = new ArrayList<Wishlist>();
		repository.findAll().forEach((wishlist) -> list.add(wishlist));
		
		Date date = new Date();
		return new Response<Wishlist>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);	
	}

	@GetMapping(path = "/get/{id}")
	public Response<Wishlist> getWishlistItemsById(@PathVariable("id") Integer id){
		
		ArrayList<Wishlist> list = new ArrayList<Wishlist>();
		Wishlist wishlist = repository.findById(id).get();
		list.add(wishlist);
		
		Date date = new Date();
		return new Response<Wishlist>(101, TAG+" Fetched Successfully at "+date, list);
		
	}

	@GetMapping(path = "/get-user-wishlist/{userId}")
	public Response<Wishlist> getUserCartProducts(@PathVariable("userId") Integer userId) {
		Date date = new Date();
		ArrayList<Wishlist> list = new ArrayList<Wishlist>();
		repository.findAllProductsByUserId(userId).forEach((cart) -> list.add(cart));
		
		return new Response<Wishlist>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
	}
	
	@PostMapping(path= "/update")
	public Response<Wishlist> updateWishlist(@RequestParam Integer WishlistId, @RequestParam Integer productId, @RequestParam Integer userId) {
    Date date = new Date();
		Wishlist wishlist = new Wishlist(WishlistId, productId, userId);
		repository.save(wishlist);
		
		return new Response<Wishlist>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Wishlist> deleteItemFromUserWishlist(@PathVariable("id") Integer id){
		
		Wishlist wishlist = new Wishlist();
		wishlist.setWishListId(id);
		repository.delete(wishlist);
		
		Date date = new Date();
		return new Response<Wishlist>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
