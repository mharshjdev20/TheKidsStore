package com.claim.kidsstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.claim.kidsstore.model.Order;
import com.claim.kidsstore.repository.OrderRepository;
import com.claim.kidsstore.service.OrderService;


public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	public OrderServiceImpl (OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	
	@Override
	public Optional<Order> findOrderById(long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findByOrderId(orderId);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(long orderId) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(orderId);
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepository.findById(order.getOrderId()).ifPresent(o ->{
			o.setNotes(order.getNotes());
			o.setPickUpDate(order.getPickUpDate());
			o.setTotalPrice(order.getTotalPrice());
		});

	}
	@Override
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepository.save(order);
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		order.setOrderDate(order.getOrderDate());
		order.setNotes(order.getNotes());
		order.setPickUpDate(order.getPickUpDate());
	}

}
