package com.hrms.util;

import java.util.List;

public class PaginationDto {
	
    int currentPage;
    double totalPages;
    int totalItems;
    List<?> object ;
	
    public PaginationDto() {
		super();
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public double getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(double totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<?> getObject() {
		return object;
	}

	public void setObject(List<?> object) {
		this.object = object;
	}
    
    
    
    

}
