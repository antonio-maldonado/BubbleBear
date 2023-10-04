package com.bubblebear.backendProject.implementation;

import com.bubblebear.backendProject.entity.OrdersHasProducts;
import com.bubblebear.backendProject.repository.OrdersHasProductsRepository;
import com.bubblebear.backendProject.service.OrdersHasProductService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrdersHasProductServiceImpl implements OrdersHasProductService {

    private final OrdersHasProductsRepository ordersHasProductsRepository;


    public OrdersHasProductServiceImpl(OrdersHasProductsRepository ordersHasProductsRepository) {
        this.ordersHasProductsRepository = ordersHasProductsRepository;
    }

    @Override
    public OrdersHasProducts createOrderHasProduct(OrdersHasProducts orderProduct) {
        return ordersHasProductsRepository.save(orderProduct);
    }

    @Override
    public OrdersHasProducts getOrderHasProductById(long orderId, long productId) {
        return ordersHasProductsRepository.findById(orderId);
    }

    @Override
    public OrdersHasProducts updateOrderHasProduct(OrdersHasProducts orderProduct, long orderId, long productId) {
        OrdersHasProducts existingOrderProduct = ordersHasProductsRepository.findById(orderId);
        if (existingOrderProduct != null) {
            existingOrderProduct.setQuantity(orderProduct.getQuantity());
            existingOrderProduct.setPriceProduct(orderProduct.getPriceProduct());
            return ordersHasProductsRepository.save(existingOrderProduct);
        }
        return null;
    }

    @Override
    public void deleteOrderHasProduct(long orderId, long productId) {
        ordersHasProductsRepository.deleteById(orderId);
    }

    @Override
    public List<OrdersHasProducts> getAllOrdersHasProducts() {
        return (List<OrdersHasProducts>) ordersHasProductsRepository.findAll();
    }

	@Override
	public List<OrdersHasProducts> findAll() {
		return null;
	}

}
