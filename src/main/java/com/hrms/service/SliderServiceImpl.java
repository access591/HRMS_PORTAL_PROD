package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.Slider;
import com.hrms.repository.SliderDao;

@Service
public class SliderServiceImpl implements SliderService {
	@Autowired
	SliderDao sliderDao;

	@Override
	public void addSlider(Slider slider) {
		try {
			this.sliderDao.save(slider);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Slider> getAllSliders() {
		try {
			return sliderDao.findAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Slider findSliderById(long id) {

		try {
			return sliderDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void updateSlider(Slider s) {
		try {
			this.sliderDao.saveOrUpdate(s);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void removeSlider(long id) {
		try {
			this.sliderDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
