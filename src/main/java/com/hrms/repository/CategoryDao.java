package com.hrms.repository;

import java.util.List;

import com.hrms.dao.GenericDao;
import com.hrms.model.Category;

public interface CategoryDao extends GenericDao<Category>{

	Category chaeckCategoryExistOrNot(Category category);

	List<Category> getActiveCategory();

}
