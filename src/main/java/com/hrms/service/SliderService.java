package com.hrms.service;

import java.util.List;

import com.hrms.model.Slider;

public interface SliderService {

	void addSlider(Slider slider);

	List<Slider> getAllSliders();

	Slider findSliderById(long id);

	public void updateSlider(Slider a);

	public void removeSlider(long id);
}
