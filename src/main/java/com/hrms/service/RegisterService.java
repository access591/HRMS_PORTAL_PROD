package com.hrms.service;

import java.util.List;

import com.hrms.model.Register;

public interface RegisterService {
	public void addRegister(Register res);

	List<Register> getAllRegisters();

	boolean checkRegisterExists(Register register);

	Register findRegisterById(String id);

	public void updateRegister(Register res);

	public void removeRegister(String id);

}
