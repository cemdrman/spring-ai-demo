package com.bilisimio.aidemo.order;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    public List<Order> findAll() {
        return orders;
    }
}
