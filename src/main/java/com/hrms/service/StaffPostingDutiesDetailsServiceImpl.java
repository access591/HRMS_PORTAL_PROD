package com.hrms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.StaffPostingDutiesDetails;
import com.hrms.repository.StaffPostingDutiesDetailsDao;

@Service
public class StaffPostingDutiesDetailsServiceImpl implements StaffPostingDutiesDetailsService{
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	StaffPostingDutiesDetailsDao saffPostingDutiesDetails;
	@Override
	public boolean addStaffPostingDutiesDetails(StaffPostingDutiesDetails spdd) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
	
		s.save(spdd);
		s.getTransaction().commit();
		s.clear();
		s.close();
		return true;
	}
	@Override
	public List<StaffPostingDutiesDetails> getAllStaffpDetails() {
	
		try {
			return saffPostingDutiesDetails.findAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
				
	}
}
