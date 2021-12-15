package com.hrms.repository;

import org.springframework.stereotype.Repository;

import com.hrms.dao.AbstractGenericDao;
import com.hrms.model.Notification;
@Repository
public class NotificationDaoImpl extends AbstractGenericDao<Notification> implements NotificationDao{

}
