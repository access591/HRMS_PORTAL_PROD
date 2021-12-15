package com.hrms.service;

import java.util.List;

import com.hrms.model.Commission;

public interface Commissionservice {

	List<Commission> getAllCommissions();

	void addCommission(Commission commission);

	Commission findCommissionById(long id);

	public void updateCommission(Commission c);

	public void removeCommission(long id);


}
