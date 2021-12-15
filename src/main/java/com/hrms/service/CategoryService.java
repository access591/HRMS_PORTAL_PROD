package com.hrms.service;

import java.util.List;


import com.hrms.model.Category;


public interface CategoryService {
	
	public void addCategory(Category category);
	public List<Category> getAllCategory();
	public Category findCategoryByCatId(String categId);
	public void updateCategory(Category category);
	public void removeCategory(String categoryId);
	boolean chaeckCategoryExistOrNot(Category category);
	public List<Category> getActiveCategory();
	public List<Category> getActiveCategoryStatus();
	

}
