package com.hrms.service;

import java.util.List;

import com.hrms.model.TrainingSchedule;

public interface TrainingScheduleService {
	
	public void saveTrainingSchedule(TrainingSchedule trainingSchedule);
	public List<TrainingSchedule> getAllTrainingSchedule();
	public TrainingSchedule findTrainingScheduleById(String trReqCode);
	public void removeTrainingSchedule(String treqCode);
	
	public void updateTrainingSchedule(TrainingSchedule trainingSchedule);

}
