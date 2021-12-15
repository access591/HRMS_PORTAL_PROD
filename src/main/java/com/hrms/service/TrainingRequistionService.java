package com.hrms.service;

import java.util.List;

import com.hrms.model.TrainingRequisition;

public interface TrainingRequistionService {
	
	public void addTrainingRequisition(TrainingRequisition trainingRequistion);
	public List<TrainingRequisition> getAllTrainingRequisition();
	public void trainingRequisitionApproval(String trReqCode,String  status);
	public TrainingRequisition findById(String trReqCode);
	public void updateTrainingRequisition(TrainingRequisition trainingRequisition);
	public List<TrainingRequisition> findTrainingRequisitionByStatusY();
	public List<TrainingRequisition> findTrainingRequisitionByStatusYAndC();
	public void removeTrainingRequisition(String trainingRequisitionId);

}
