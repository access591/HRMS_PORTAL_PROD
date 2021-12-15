package com.hrms.service;

import java.util.List;

import com.hrms.model.Bank;


public interface BankService 
{
	public void addBank(Bank bank);
	List<Bank>getAllBanks();
	Bank findBankById(String id);
	public void updateBank(Bank d);
	public void removeBank(String id);

}
