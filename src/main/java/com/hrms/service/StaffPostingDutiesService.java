package com.hrms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrms.model.StaffPostingDuties;
import com.hrms.util.SaffPostingDutiesUtil;

public interface StaffPostingDutiesService {

	void addStaffPostingDuties(StaffPostingDuties staffduties);

	void removestaffDuties(String id);

	List<StaffPostingDuties> getAllStaffPostingDuties();

	StaffPostingDuties staffPostingDutieById(String id);

	void updateStaffPostingDuties(StaffPostingDuties staffduties);

	void staffPostingDutiesGenratepdf(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<SaffPostingDutiesUtil> dataList);

}
