package com.claim.kidsstore.service;

import java.util.List;
import java.util.Optional;


import com.claim.kidsstore.model.Order;

public interface OrderService {
	
	Optional<Order> findOrderById(long orderId);
	
	List <Order> findAll();
	
	void deleteOrder (long orderId);
	
	void updateOrder (Order order);
	
	void saveOrder(Order order);
	
	void addOrder(Order order);
}
