package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.LocalConvyence;
import com.hrms.repository.ConveyanceApprovalDao;

@Service
public class ConveyanceApprovalServiceImpl  implements ConveyanceApprovalService{
@Autowired
ConveyanceApprovalDao conveyanceApprovalDao;
	@Override
	public List<LocalConvyence> getAllLocalConveyance() {
		return conveyanceApprovalDao.getAllLocalConveyance();
		
	}
	
	@Override
	public void approvedByLocalConvyenceId(String id) {
		this.conveyanceApprovalDao.approvedByLocalConvyenceId(id);
		
	}

}
