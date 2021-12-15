package com.hrms.service;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.MedicalReimbursement;
import com.hrms.repository.MedicalReimbursementDao;

@Service
public class MedicalReimbursementServiceImpl implements MedicalReimbursementService  {
@Autowired
MedicalReimbursementDao medicalReimbursementDao;
@Autowired
SessionFactory sessionfactory;
@Override
public boolean addMedicalReimbursement(MedicalReimbursement m2) {
	
	m2.setSlipNo(medicalReimbursementDao.getMaxId("SLP"));
	this.medicalReimbursementDao.saveOrUpdate(m2);
	return true;
}

@Override
public List<MedicalReimbursement> getAllMedicalReimbursement() {
	return  medicalReimbursementDao.findAll();
	
}

@Override
public MedicalReimbursement findByIdMedicalReimbursementMaster(String id) {
	
	return this.medicalReimbursementDao.findById(id);
	
}

@Override
public void removeMedicalReimbursement(String id) {
	
	Session session = sessionfactory.openSession();
	Object o = session.get(MedicalReimbursement.class, id);
	MedicalReimbursement e = (MedicalReimbursement) o;
	Transaction tx = session.beginTransaction();
	session.delete(e);
	tx.commit();
	session.close();
	

	
}

@Override
public void updateMedicalReimbursement(MedicalReimbursement medicalReimbursement) {
	
	
	this.medicalReimbursementDao.saveOrUpdate(medicalReimbursement);
	
}

@Override
public void approvedByMrId(String slipNo) {
	this.medicalReimbursementDao.approvedByMrId(slipNo);
	
}

@Override
public List<MedicalReimbursement> getAllMedicalReimbursementApproval() {
	return medicalReimbursementDao.getAllMedicalReimbursementApproval();
	
}
}
