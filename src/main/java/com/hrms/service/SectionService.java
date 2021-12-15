package com.hrms.service;

import java.util.List;

import com.hrms.model.Section;
public interface SectionService 
{
	public void addSection(Section section);

	List<Section> getAllSections();

	Section findSectionById(String id);

	public void updateSection(Section d);

	public void removeSection(String id);

	boolean checkSectionExists(Section section);

}
