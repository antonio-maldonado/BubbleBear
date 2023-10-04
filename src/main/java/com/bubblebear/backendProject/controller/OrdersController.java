package com.bubblebear.backendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bubblebear.backendProject.entity.Orders;
import com.bubblebear.backendProject.service.OrderService;

import lombok.extern.log4j.Log4j2;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/orders")
@Log4j2
public class OrdersController {
	
	
	@Autowired
	//OrdersRepository ordersRepository;
	 OrderService orderService;
	
	
	@GetMapping
	public ResponseEntity<List<Orders>>  getAllOrders() {
		log.info("Trayendo ordenes");
		List<Orders> orders = (List<Orders>) orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@GetMapping("{id}") 
	
		public ResponseEntity<Orders> getOrderById(@PathVariable int id){
		log.info("Trayendo orden");
		return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
		
	}
	
//	@PutMapping("{id}") //localhosts:8080/api/orders/#
//	public Orders updateOrder(@RequestBody Orders order, @PathVariable int id) {
//		Orders existingOrder = ordersRepository.findById(id);
//		
//		if(existingOrder == null) {
//			throw new IllegalStateException("Order not found");
//		}
//		existingOrder.setOrder_id( order.getOrder_id() );
//		existingOrder.setTotal_amount( order.getTotal_amount() );
//		existingOrder.setPurchase_date( order.getPurchase_date() );
//		existingOrder.setFk_user_id( order.getFk_user_id() );
//		return ordersRepository.save( existingOrder );	
//		
//	}
	@PutMapping("{id}") 
	public ResponseEntity<Orders> updateOrder(@RequestBody Orders order, @PathVariable int id) {
		log.info("Actualizando orden");
		return new ResponseEntity<>(orderService.updateOrder(order, id), HttpStatus.CREATED);	
		
	}
	
//	@PostMapping
//	public Orders insertOrder(@RequestBody Orders order) {
//		order.setOrder_id(null);
//		
//		
//		
//		Orders newOrder = ordersRepository.save(order);
//		return newOrder;	
//		
//	}
	@PostMapping
	public ResponseEntity<Orders>  insertOrder(@RequestBody Orders order) {
		log.info("Añadiendo orden");
		return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);	
		
	}
	
//	@DeleteMapping("{id}")
//	public void deleteOrder(@PathVariable int id) {
//		Orders findOrder = ordersRepository.findById(id);
//		ordersRepository.delete(findOrder);
//	}

	@DeleteMapping("{id}")
	public void deleteOrder(@PathVariable int id) {
		log.info("Eliminando orden");
		orderService.deleteOrder(id);
	}

}
