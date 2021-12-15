package com.hrms.service;

import java.util.List;

import com.hrms.model.ContactUs;
public interface ContactUsService {

	List<ContactUs> getAllContactUs();

	void addContactUs(ContactUs contactUs);

	ContactUs findContactUsById(long id);

	public void updateContactUs(ContactUs c);

	public void removeContactUs(long id);

}
