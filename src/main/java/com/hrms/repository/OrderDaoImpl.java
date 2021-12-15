package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Order;

@Repository
public class OrderDaoImpl extends  AbstractGenericDao<Order> implements OrderDao{

}
