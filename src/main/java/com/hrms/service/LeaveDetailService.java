package com.hrms.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrms.model.LeaveDetail;

public interface LeaveDetailService {
	
	public void addLeaveDetail(LeaveDetail leaveDetail);

	List<LeaveDetail> getAllLeaveDetails();

	LeaveDetail findLeaveDetailById(String id);

	public void updateLeaveDetail(LeaveDetail c);

	public void removeLeaveDetail(String id);

	public void leaveReportGenratepdf(HttpServletRequest request, HttpServletResponse response, String reportFileName,
			List<LeaveDetail> dataList);
	
	public LeaveDetail findLeaveDetailByLvCode(String lvCode);
	
	public List<LeaveDetail> findLeaveDetailByLeaveType(String leaveType);
	public void generateEXCELleaveDetailService(HttpServletRequest request, HttpServletResponse response,
			String reportFileName, String filename, List<LeaveDetail> dataList) throws IOException;

}
