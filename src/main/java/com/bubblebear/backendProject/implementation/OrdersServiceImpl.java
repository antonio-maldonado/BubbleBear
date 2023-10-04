package com.bubblebear.backendProject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bubblebear.backendProject.entity.Orders;
import com.bubblebear.backendProject.repository.OrdersRepository;
import com.bubblebear.backendProject.service.OrderService;

@Service
public class OrdersServiceImpl implements OrderService{

	@Autowired
	OrdersRepository orderRepository;
	
	
	@Override
	public Orders createOrder(Orders order) {
		order.setOrder_id(null);
		return saveOrder(order);
	}

	@Override
	public Orders saveOrder(Orders order) {		
		return orderRepository.save(order);
	}
	
	@Override
	public Orders getOrderById(Integer id) {		
		return orderRepository.findById(id).
				orElseThrow( ()-> new IllegalStateException("Order does not exist with id "+ id) );
	}

	@Override
	public List<Orders> getAllOrders() {
		
		return (List<Orders>)orderRepository.findAll();
	}

	@Override
	public Orders updateOrder(Orders order, int id) {
		
		Orders existingOrder = orderRepository.findById(id);
		
		if(existingOrder == null) {
			throw new IllegalStateException("Order not found");
		}
		existingOrder.setOrder_id( order.getOrder_id() );
		existingOrder.setTotal_amount( order.getTotal_amount() );
		existingOrder.setPurchase_date( order.getPurchase_date() );
		existingOrder.setFk_user_id( order.getFk_user_id() );
		return orderRepository.save( existingOrder );	
	}

	@Override
	public void deleteOrder(Integer id) {
		Orders existingProduct = orderRepository.findById(id).
				orElseThrow( ()-> new IllegalStateException("Order does not exist with id "+ id) );
		orderRepository.delete(existingProduct);
		
	}

	

	
}
