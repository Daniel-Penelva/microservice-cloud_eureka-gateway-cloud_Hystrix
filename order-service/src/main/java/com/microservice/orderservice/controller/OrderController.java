package com.microservice.orderservice.controller;

import com.microservice.orderservice.entity.Order;
import com.microservice.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // http://localhost:9192/order/bookOrder
    @PostMapping("/bookOrder")
    public Order bookOrder(@RequestBody Order order) {
        return orderService.save(order);
    }
}
