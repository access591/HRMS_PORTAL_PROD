package com.hrms.service;

import java.util.List;

import com.hrms.model.ReqAdvertisement;

public interface RequisitionAdvertisementService {
	
	public void addActivity(ReqAdvertisement reqAdvertisement);
	List<ReqAdvertisement>getAllReqAdvertisement();
	ReqAdvertisement findReqAdvertisementById(String id);
	public void updateReqAdvertisement(ReqAdvertisement a);
	public void removeReqAdvertisement(String id);

}
