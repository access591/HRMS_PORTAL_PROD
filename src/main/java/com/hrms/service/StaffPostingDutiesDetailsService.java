package com.hrms.service;

import java.util.List;

import com.hrms.model.StaffPostingDutiesDetails;

public interface StaffPostingDutiesDetailsService {

	boolean addStaffPostingDutiesDetails(StaffPostingDutiesDetails spdd);

	List<StaffPostingDutiesDetails> getAllStaffpDetails();

}
