package com.hrms.service;

import java.util.List;
import java.util.Map;

import com.hrms.model.BudgetProvision;

public interface BudgetProvisionService {
	
	public void saveBudgetProvision(BudgetProvision budgetProvision);
	public List<BudgetProvision> getAllBudgetProvision();
	public BudgetProvision findByBudgetProvisionId(Long budgitProvisionId);
	public void updateBudgetProvision(BudgetProvision budgetProvision);
	public void removeBudgetProvision(Long budgetId);
	public List<BudgetProvision> findBudgetProvisionByDepartment(String deptCode);
	public Map<String, Long> findBudgetTrackDepartment();

}
