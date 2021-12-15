package com.hrms.service;

import java.util.List;


import com.hrms.model.PathDirectory;



public interface PathDirectoryServices {

	List<PathDirectory> getAllPathDirectory();
	public void addPathDirectory(PathDirectory pathDirectory);
	PathDirectory findPathDirectoryById(long id);
	public void updatePathDirectory(PathDirectory a);
	public void removePathDirectory(long id);
	PathDirectory findPathByName(String pathType);


}
