package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ContactUs;
import com.hrms.repository.ContactUsDao;

@Service
public class ContactUsServiceImpl implements ContactUsService {
@Autowired ContactUsDao contactUsDao;
	@Override
	public List<ContactUs> getAllContactUs() {
		
		try {
			return contactUsDao.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addContactUs(ContactUs contactUs) {
		
		try {
			this.contactUsDao.save(contactUs);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public ContactUs findContactUsById(long id) {
	
		try {
			return contactUsDao.findById(id);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateContactUs(ContactUs c) {
	
		try {
			this.contactUsDao.saveOrUpdate(c);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void removeContactUs(long id) {
	try {
		this.contactUsDao.delete(id);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
		
	}

}
