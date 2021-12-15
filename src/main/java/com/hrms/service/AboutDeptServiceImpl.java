package com.hrms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.AboutDept;
import com.hrms.repository.AboutDeptDao;

@Service
public class AboutDeptServiceImpl implements AboutDeptService {

@Autowired
AboutDeptDao aboutDept;

@Override
public void addAboutDept(AboutDept aboutDept) {

	this.aboutDept.save(aboutDept);
}

@Override
public List<AboutDept> getAllAboutsDepts() {

	try {
		return aboutDept.findAll();
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	return null;
}

@Override
public AboutDept findAboutDeptById(long id) {

	try {
		return aboutDept.findById(id);
	} catch (Exception e) {
	
		e.printStackTrace();
	}
	return null;
}

@Override
public void updateAboutDept(AboutDept about) {

	try {
		this.aboutDept.saveOrUpdate(about);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
}

@Override
public void removeAboutDept(long id) {
	try {
		this.aboutDept.delete(id);
	} catch (Exception e) {

		e.printStackTrace();
	}

}
}
