package com.hrms.service;

import java.util.List;

import com.hrms.model.ReqAdvertisementDetail;

public interface ReqAdvertisementDetailService {
	
	public void addActivity(ReqAdvertisementDetail reqAdvertisementDetail);
	List<ReqAdvertisementDetail>getAllReqAdvertisement();
	ReqAdvertisementDetail findReqAdvertisementById(String id);
	public void updateReqAdvertisement(ReqAdvertisementDetail a);
	public void removeReqAdvertisement(Long id);
	
	

}
