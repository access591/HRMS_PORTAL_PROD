package com.hrms.service;

import java.util.List;


import com.hrms.model.TrainingRegister;

public interface TrainingRegisterService {
	public void addTrainingRegister(TrainingRegister trainingRegister);
	List<TrainingRegister>getAllTrainingRegisters();
	TrainingRegister findTrainingRegisterById(String id);
	public void updateTrainingRegister(TrainingRegister t);
	public void removeTrainingRegister(String id);
	

}
