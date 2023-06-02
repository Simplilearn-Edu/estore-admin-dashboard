package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.estorebackend.model.Response;
import com.example.estorebackend.model.Shipments;
import com.example.estorebackend.repository.ShipmentsRepository;

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
@RequestMapping("/shipments")
public class ShipmentsController {

  @Autowired
	ShipmentsRepository repository;
	
	final String TAG = "Shipment";
	
	@PostMapping(path= "/add")
	public Response<Shipments> addShipment(@RequestParam Integer orderId, @RequestParam Integer shipmentStatus, @RequestParam String shipmentTitle,
  @RequestParam Date shipmentDate, @RequestParam String shipmentMethod, @RequestParam String shipmentCompany, @RequestParam Date expectedDeliveryDate) {
		Date date = new Date();
		
		Shipments shipment = new Shipments(null, orderId, shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany, expectedDeliveryDate);
		repository.save(shipment);
		
		return new Response<Shipments>(101, TAG+" Saved Successfully at "+date, null);
		
	}
	
	@GetMapping(path="/get")
	public Response<Shipments> getShipments(){
		
		ArrayList<Shipments> list = new ArrayList<Shipments>();
		repository.findAll().forEach((shipment) -> list.add(shipment));
		
		Date date = new Date();
		return new Response<Shipments>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
	}
	

	@GetMapping(path = "/get/{shipmentStatus}")
	public Response<Shipments> getShipmentById(@PathVariable("shipmentStatus") Integer shipmentStatus){
		
		List<Shipments> list = repository.findByShipmentStatus(shipmentStatus);
		Date date = new Date();
		return new Response<Shipments>(101, TAG+" Fetched Successfully at "+date, list);
		
	}
	
	@PostMapping(path= "/update")
	public Response<Shipments> updateShipment(@RequestParam Integer shipmentId, @RequestParam Integer orderId, @RequestParam Integer shipmentStatus, @RequestParam String shipmentTitle,
  @RequestParam Date shipmentDate, @RequestParam String shipmentMethod, @RequestParam String shipmentCompany, @RequestParam Date expectedDeliveryDate) {

		
		Date date = new Date();
		
		Shipments shipment = new Shipments(shipmentId, orderId, shipmentStatus, shipmentTitle, shipmentDate, shipmentMethod, shipmentCompany, expectedDeliveryDate);
		repository.save(shipment);
		
		return new Response<Shipments>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Shipments> deleteShipment(@PathVariable("id") Integer id){
		
		Shipments shipment = new Shipments();
		shipment.setShipmentId(id);
		repository.delete(shipment);
		
		Date date = new Date();
		return new Response<Shipments>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
