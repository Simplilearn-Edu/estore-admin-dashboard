package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Date;

import com.example.estorebackend.model.Categories;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.CategoriesRepository;

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
@RequestMapping("/categories")
public class CategoriesController {
  @Autowired
	CategoriesRepository repository;
	
	final String TAG = "Categories";
	
	@PostMapping(path= "/add")
	public Response<Categories> addCategory(@RequestParam String categoryName, @RequestParam String categoryDescription, @RequestParam String categoryImageUrl,	@RequestParam Integer active) {
		Date date = new Date();
		
		Categories category = new Categories(null, categoryName, categoryDescription, categoryImageUrl, active, date);
		repository.save(category);
		
		return new Response<Categories>(101, TAG+" Saved Successfully at "+date, null);
	}
	
	@GetMapping(path="/get")
	public Response<Categories> getAllCategories(){
		
		ArrayList<Categories> list = new ArrayList<Categories>();
		repository.findAll().forEach((category) -> list.add(category));
		
		Date date = new Date();
		return new Response<Categories>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
		
	}
	

	@GetMapping(path = "/get/{id}")
	public Response<Categories> getCategoryById(@PathVariable("id") Integer id){
		
		ArrayList<Categories> list = new ArrayList<Categories>();
		Categories category = repository.findById(id).get();
		list.add(category);
		
		Date date = new Date();
		return new Response<Categories>(101, TAG+" Fetched Successfully at "+date, list);
		
	}
	
	@PostMapping(path= "/update")
	public Response<Categories> updateCategory(@RequestParam Integer categoryId, @RequestParam String categoryName, @RequestParam String categoryDescription, @RequestParam String categoryImageUrl,	@RequestParam Integer active) {

		
		Date date = new Date();
		
		Categories category = new Categories(categoryId, categoryName, categoryDescription, categoryImageUrl, active, date);
		repository.save(category);
		
		return new Response<Categories>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Categories> deleteCategory(@PathVariable("id") Integer id){
		
		Categories category = new Categories();
		category.setCategoryId(id);
		repository.delete(category);
		
		Date date = new Date();
		return new Response<Categories>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
