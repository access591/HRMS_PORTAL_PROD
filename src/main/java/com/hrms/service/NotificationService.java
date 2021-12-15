package com.hrms.service;

import java.util.List;

import com.hrms.model.Notification;

public interface NotificationService {
	void addNotification(Notification notification);
	
	List<Notification> getAllNotifications();

	Notification findNotificationById(long id);

	public void updateNotification(Notification n);

	public void removeNotification(long id);

}
