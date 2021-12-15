package com.hrms.service;

import java.util.List;

import com.hrms.model.OrderIssueTracking;

public interface OrderIssueTrackingService {

	public void saveOrderIssueTracking(OrderIssueTracking orderIssueTracking);
	public List<OrderIssueTracking> getAllOrderIssueTracking();
	public OrderIssueTracking findOrderIssueTrackingById(Long orderIssueTrackingId);
	public void updateOrderIssueTracking(OrderIssueTracking orderIssueTracking);
	public void removeOrderIssueTracking(Long orderTrackinId);
	
	public OrderIssueTracking findOrderIssueTrackingByIssuedby(String empCode);
}
