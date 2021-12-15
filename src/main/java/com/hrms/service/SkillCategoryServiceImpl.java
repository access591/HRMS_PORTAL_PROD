package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrms.model.SkillCategory;
import com.hrms.repository.SkillCategoryDao;
@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {
	@Autowired
	SkillCategoryDao skillCategoryDao;

	@Override
	public void addSkillCategory(SkillCategory sc) {
		sc.setSkillLevelCode(skillCategoryDao.getMaxId("SCM"));
		this.skillCategoryDao.saveOrUpdate(sc);
	}

	@Override
	public List<SkillCategory> getAllSkillCategorys() {
		return skillCategoryDao.findAll();
		
	}

	@Override
	public SkillCategory findSkillCategoryById(String id) {
	
		return skillCategoryDao.findById(id);
	}

	@Override
	public void updateSkillCategory(SkillCategory sc) {
	this.skillCategoryDao.saveOrUpdate(sc);
		
	}

	@Override
	public void removeSkillCategory(String id) {
		this.skillCategoryDao.delete(id);
		
	}

}
