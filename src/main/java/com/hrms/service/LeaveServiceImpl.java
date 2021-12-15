package com.hrms.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Leave;
import com.hrms.repository.LeaveDao;
@Service
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveDao leaveDao;

	@Override
	public void addLeave(Leave leave) {
		leave.setInsDate(new Date());
		leave.setLevCode(leaveDao.getMaxId("LTC"));
		this.leaveDao.saveOrUpdate(leave);
	}

	@Override
	public List<Leave> getAllLeaves() {
		List<Leave> listLeave = leaveDao.findAll();
		return listLeave;
	}

	@Override
	public Leave findLeaveById(String id) {
		return leaveDao.findById(id);
	}

	@Override
	public void updateLeave(Leave d) {
		d.setLevCode(d.getLevCode());
		d.setUpdDate(d.getUpdDate());
		d.setActive(d.getActive());
		this.leaveDao.saveOrUpdate(d);

	}

	@Override
	public void removeLeave(String id) {
		this.leaveDao.delete(id);

	}

}
