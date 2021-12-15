package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Notification;
import com.hrms.repository.NotificationDao;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	NotificationDao notificationDao;

	@Override
	public void addNotification(Notification notification) {

		try {
			this.notificationDao.save(notification);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public List<Notification> getAllNotifications() {

		try {
			return notificationDao.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Notification findNotificationById(long id) {

		try {
			return notificationDao.findById(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateNotification(Notification n) {

		try {
			this.notificationDao.saveOrUpdate(n);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	@Override
	public void removeNotification(long id) {
		try {
			this.notificationDao.delete(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
