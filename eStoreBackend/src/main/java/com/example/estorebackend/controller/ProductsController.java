package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.example.estorebackend.model.Products;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/products")
public class ProductsController {

  @Autowired
	ProductsRepository repository;
	
	final String TAG = "Product";
	
	@PostMapping(path= "/add")
	public Response<Products> addProduct(@RequestParam String productTitle, @RequestParam String productDescription, @RequestParam String productCode,
			@RequestParam List<String> images, @RequestParam Integer categoryId, @RequestParam Integer thumbnailImage, @RequestParam Integer price, @RequestParam Integer rating) {
		Date date = new Date();
		
		Products product = new Products(null, productTitle, productDescription, productCode, images, categoryId, thumbnailImage, price, date, rating);
		repository.save(product);
		
		return new Response<Products>(101, TAG+" Saved Successfully at "+date, null);
		
	}
	
	@GetMapping(path="/get")
	public Response<Products> getProducts(){
		
		ArrayList<Products> list = new ArrayList<Products>();
		repository.findAll().forEach((product) -> list.add(product));
		
		Date date = new Date();
		return new Response<Products>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
		
	}
	

	@GetMapping(path = "/get/{id}")
	public Response<Products> getProductById(@PathVariable("id") Integer id){
		
		ArrayList<Products> list = new ArrayList<Products>();
		Products product = repository.findById(id).get();
		list.add(product);
		
		Date date = new Date();
		return new Response<Products>(101, TAG+" Fetched Successfully at "+date, list);
		
	}
	
	@GetMapping(path = "/get-products")
	public Response<Products> getAllProductsByUserId(@Param("productsIds") String productIds) {
		List<Integer> prdIds = new ArrayList<Integer>();
		for(String id: productIds.split(",")) {
			prdIds.add(Integer.parseInt(id));
		}
		
		Date date = new Date();
		ArrayList<Products> list = new ArrayList<Products>();
		repository.findAllById(prdIds).forEach((product) -> list.add(product));
		
		return new Response<Products>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
	}
	
	@PostMapping(path= "/update")
	public Response<Products> updateProduct(@RequestParam Integer productId, @RequestParam String productTitle, @RequestParam String productDescription, @RequestParam String productCode,
  @RequestParam List<String> images, @RequestParam Integer categoryId, @RequestParam Integer thumbnailImage, @RequestParam Integer price, @RequestParam Integer rating) {

		
		Date date = new Date();
		
		Products product = new Products(productId, productTitle, productDescription, productCode, images, categoryId, thumbnailImage, price, date, rating);
		repository.save(product);
		
		return new Response<Products>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Products> deleteProduct(@PathVariable("id") Integer id){
		
		Products product = new Products();
		product.setProductId(id);
		repository.delete(product);
		
		Date date = new Date();
		return new Response<Products>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
