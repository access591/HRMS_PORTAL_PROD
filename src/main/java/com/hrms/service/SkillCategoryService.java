package com.hrms.service;

import java.util.List;

import com.hrms.model.SkillCategory;

public interface SkillCategoryService {
	public void addSkillCategory(SkillCategory sc);

	List<SkillCategory> getAllSkillCategorys();

	SkillCategory findSkillCategoryById(String id);

	public void updateSkillCategory(SkillCategory sc);

	public void removeSkillCategory(String id);

}
