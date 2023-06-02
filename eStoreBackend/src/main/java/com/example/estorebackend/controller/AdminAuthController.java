package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.estorebackend.model.Admins;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.AdminsRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/adminauth")
public class AdminAuthController {

	@Autowired
	AdminsRepository repository;
	
	final String TAG = "Admin";
	
	@PostMapping(path= "/login")
	public Response<Admins> loginAdmin(@RequestParam String email, @RequestParam String password) {

		Date date = new Date();
		
		Admins admin = repository.findByEmailAndPassword(email, password).get();
		if(!admin.getFullName().isEmpty()) {
			return new Response<Admins>(101, TAG+" Logged In Successfully at "+date, null);
		}else {
			return new Response<Admins>(201, TAG+" Authentication Failed at "+date, null);
		}
		
	}
	
	@PostMapping(path= "/add")
	public Response<Admins> addAdmin(@RequestParam String email, @RequestParam String password, @RequestParam String fullName, @RequestParam Integer loginType) {
		Date date = new Date();
		
		Admins admin = new Admins(null, email, password, fullName, loginType, date);
		repository.save(admin);
		
		return new Response<Admins>(101, TAG+" Saved Successfully at "+date, null);
		
	}
	
	@GetMapping(path="/get")
	public Response<Admins> getAdmins(){
		
		ArrayList<Admins> list = new ArrayList<Admins>();
		repository.findAll().forEach((admin) -> list.add(admin));
		
		Date date = new Date();
		return new Response<Admins>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
		
	}
	

	@GetMapping(path = "/get/{id}")
	public Response<Admins> getAdminById(@PathVariable("id") Integer id){
		
		ArrayList<Admins> list = new ArrayList<Admins>();
		Admins admin = repository.findById(id).get();
		list.add(admin);
		
		Date date = new Date();
		return new Response<Admins>(101, TAG+" Fetched Successfully at "+date, list);
		
	}
	
	@PostMapping(path= "/update")
	public Response<Admins> updateAdmin(@RequestParam Integer adminId, @RequestParam String email, @RequestParam String password, @RequestParam String fullName, @RequestParam Integer loginType) {

		
		Date date = new Date();
		
		Admins admin = new Admins(adminId, email, password, fullName, loginType, date);
		repository.save(admin);
		
		return new Response<Admins>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Admins> deleteAdmin(@PathVariable("id") Integer id){
		
		Admins admin = new Admins();
		admin.setAdminId(id);
		repository.delete(admin);
		
		Date date = new Date();
		return new Response<Admins>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
	
	
}
