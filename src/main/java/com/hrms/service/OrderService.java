package com.hrms.service;

import java.util.List;

import com.hrms.model.Order;

public interface OrderService {
	void addOrder(Order order);
	List<Order> getAllOrder();

	Order findOrderById(long id);

	public void updateOrder(Order o);

	public void removeOrder(long id);

	
	
}
