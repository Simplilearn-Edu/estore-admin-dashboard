package com.example.estorebackend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.example.estorebackend.model.OrderItems;
import com.example.estorebackend.model.OrderProducts;
import com.example.estorebackend.model.Orders;
import com.example.estorebackend.model.Response;
import com.example.estorebackend.repository.OrderItemsRepository;
import com.example.estorebackend.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	OrdersRepository repository;
	
	@Autowired
	OrderItemsRepository orItemsRepository;
	
	final String TAG = "Order";
	
	@PostMapping(path= "/add")
	public Response<Orders> addOrder(@RequestParam Date orderDate, @RequestParam Integer orderStatus, @RequestParam Integer totalItems,
      @RequestParam Double itemsSubTotal, @RequestParam Double shipmentCharges, @RequestParam Double totalAmount, @RequestParam Integer paymentStatus,
      @RequestParam String paymentStatusTitle, @RequestParam Integer paymentMethod, @RequestParam String paymentMethodTitle, @RequestParam Integer userId, @RequestParam String name,
      @RequestParam String email, @RequestParam Long contact, @RequestParam String address) {
		Date date = new Date();
		
		Orders order = new Orders(null, orderDate, orderStatus, totalItems, itemsSubTotal, shipmentCharges, totalAmount, paymentStatus, paymentStatusTitle, paymentMethod, paymentMethodTitle, userId, name, email, contact, address);
		repository.save(order);
		
		return new Response<Orders>(101, TAG+" Saved Successfully at "+date, Arrays.asList(order));
		
	}

	@GetMapping(path="/get-user-orders/{userId}")
	public Response<OrderProducts> getUserOrders(@PathVariable("userId") Integer userId) {
		List<OrderProducts> orders = new ArrayList<OrderProducts>();
		for(Orders order: repository.findByUserId(userId)) {
			List<OrderItems> oProducts = orItemsRepository.findByOrderId(order.getOrderId());
			OrderProducts oProduct = new OrderProducts(
				order.getOrderId(), 
				order.getOrderDate(), 
				order.getOrderStatus(), 
				order.getTotalItems(), 
				order.getItemsSubTotal(), 
				order.getShipmentCharges(), 
				order.getTotalAmount(), 
				order.getPaymentStatus(), 
				order.getPaymentStatusTitle(), 
				order.getPaymentMethod(), 
				order.getPaymentMethodTitle(), 
				order.getUserId(), 
				order.getName(), 
				order.getEmail(), 
				order.getContact(), 
				order.getAddress(), 
				oProducts
			);
			orders.add(oProduct);
		}
		
		Date date = new Date();
		return new Response<OrderProducts>(101, orders.size()+" "+TAG+"s Fetched Successfully at "+date, orders);
	}

	@GetMapping(path="/get-orders/{orderStatus}")
	public Response<OrderProducts> getOrderByStatus(@PathVariable("orderStatus") Integer orderStatus) {
		List<OrderProducts> orders = new ArrayList<OrderProducts>();
		for(Orders order: repository.findByOrderStatus(orderStatus)) {
			List<OrderItems> oProducts = orItemsRepository.findByOrderId(order.getOrderId());
			OrderProducts oProduct = new OrderProducts(
				order.getOrderId(), 
				order.getOrderDate(), 
				order.getOrderStatus(), 
				order.getTotalItems(), 
				order.getItemsSubTotal(), 
				order.getShipmentCharges(), 
				order.getTotalAmount(), 
				order.getPaymentStatus(), 
				order.getPaymentStatusTitle(), 
				order.getPaymentMethod(), 
				order.getPaymentMethodTitle(), 
				order.getUserId(), 
				order.getName(), 
				order.getEmail(), 
				order.getContact(), 
				order.getAddress(), 
				oProducts
			);
			orders.add(oProduct);
		} 
		Date date = new Date();
		return new Response<OrderProducts>(101, orders.size()+" "+TAG+"s Fetched Successfully at "+date, orders);
	}

	@GetMapping(path="/get")
	public Response<Orders> getOrders(){
		
		ArrayList<Orders> list = new ArrayList<Orders>();
		repository.findAll().forEach((order) -> list.add(order));
		Date date = new Date();
		return new Response<Orders>(101, list.size()+" "+TAG+"s Fetched Successfully at "+date, list);
	}

	@GetMapping(path = "/get/{id}")
	public Response<Orders> getOrderById(@PathVariable("id") Integer id){
		ArrayList<Orders> list = new ArrayList<Orders>();
		Orders order = repository.findById(id).get();
		list.add(order);
		Date date = new Date();
		return new Response<Orders>(101, TAG+" Fetched Successfully at "+date, list);
	}
	
	@PostMapping(path= "/update")
	public Response<Orders> updateOrder(@RequestParam Integer orderId, @RequestParam @DateTimeFormat(iso = ISO.DATE_TIME) Date orderDate, @RequestParam Integer orderStatus, @RequestParam Integer totalItems,
    @RequestParam Double itemsSubTotal, @RequestParam Double shipmentCharges, @RequestParam Double totalAmount, @RequestParam Integer paymentStatus,
    @RequestParam String paymentStatusTitle, @RequestParam Integer paymentMethod, @RequestParam String paymentMethodTitle, @RequestParam Integer userId, @RequestParam String name,
    @RequestParam String email, @RequestParam Long contact, @RequestParam String address) {

		Date date = new Date(); 
		
		Orders order = new Orders(orderId, orderDate, orderStatus, totalItems, itemsSubTotal, shipmentCharges, totalAmount, paymentStatus, paymentStatusTitle, paymentMethod, paymentMethodTitle, userId, name, email, contact, address);
		repository.save(order);
		
		return new Response<Orders>(101, TAG+" Updated Successfully at "+date, null);
		
	}
	
	@GetMapping(path = "/delete/{id}")
	public Response<Orders> deleteOrder(@PathVariable("id") Integer id){
		
		Orders order = new Orders();
		order.setOrderId(id);
		repository.delete(order);
		
		Date date = new Date();
		return new Response<Orders>(101, TAG+" Deleted Successfully at "+date, null);
		
	}
}
