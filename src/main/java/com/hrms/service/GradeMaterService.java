package com.hrms.service;

import java.util.List;

import com.hrms.model.Grade;

public interface GradeMaterService {

	
	   public void addGrade(Grade grade);
	   List<Grade>getAllGrades();
	   Grade findGradeById(String id);
	   public void updateGrade(Grade d); 
	   public void removeGrade(String id);
	   boolean checkGradeExists(Grade grade);
	
	
}