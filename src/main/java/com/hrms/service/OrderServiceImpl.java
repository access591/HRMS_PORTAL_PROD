package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Order;
import com.hrms.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService{
@Autowired OrderDao  orderDao;
	@Override
	public void addOrder(Order order) {
	try {
		this.orderDao.save(order);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
		
	}

	@Override
	public List<Order> getAllOrder() {
	
		try {
			return orderDao.findAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Order findOrderById(long id) {
		
		try {
			return orderDao.findById(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateOrder(Order o) {
	try {
		this.orderDao.saveOrUpdate(o);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
		
	}

	@Override
	public void removeOrder(long id) {
		try {
			this.orderDao.delete(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
