package com.bubblebear.backendProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bubblebear.backendProject.entity.OrdersHasProducts;
import com.bubblebear.backendProject.service.OrdersHasProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/ordershasproducts")
public class OrdersHasProductsController {

    @Autowired
    private OrdersHasProductService ordersHasProductService;

    @GetMapping
    public List<OrdersHasProducts> getAllOrdersHasProducts() {
        return ordersHasProductService.getAllOrdersHasProducts();
    }

    @GetMapping("{id}")
    public OrdersHasProducts getOrdersHasProductsById(@PathVariable long id) {
        return ordersHasProductService.getOrderHasProductById(id, id); // Ajusta según tus parámetros
    }

    @PostMapping
    public OrdersHasProducts createOrdersHasProducts(@RequestBody OrdersHasProducts newEntry) {
        return ordersHasProductService.createOrderHasProduct(newEntry);
    }

    @PutMapping("{id}")
    public OrdersHasProducts updateOrdersHasProducts(@PathVariable long id, @RequestBody OrdersHasProducts updatedEntry) {
        return ordersHasProductService.updateOrderHasProduct(updatedEntry, id, id); // Ajusta según tus parámetros
    }

    @DeleteMapping("{id}")
    public void deleteOrdersHasProducts(@PathVariable long id) {
        ordersHasProductService.deleteOrderHasProduct(id, id); // Ajusta según tus parámetros
    }
}
