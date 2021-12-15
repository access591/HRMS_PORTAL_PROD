package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.ImageGallery;
import com.hrms.repository.ImageGalleryDao;
@Service
public class ImageGalleryServiceImpl implements ImageGalleryService {
	@Autowired
	ImageGalleryDao imageGalleryDao;

	@Override
	public List<ImageGallery> getAllImageGallery() {
		try {
			return imageGalleryDao.findAll();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addImageGallery(ImageGallery imageGallery) {
		try {
			this.imageGalleryDao.save(imageGallery);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public ImageGallery findImageGalleryById(long id) {

		try {
			return imageGalleryDao.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateImageGallery(ImageGallery i) {
		try {
			this.imageGalleryDao.saveOrUpdate(i);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void removeImageGallery(long id) {
		try {
			this.imageGalleryDao.delete(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
