package com.hrms.service;

import java.util.List;
import com.hrms.model.ImageGallery;

public interface ImageGalleryService {

	List<ImageGallery> getAllImageGallery();

	void addImageGallery(ImageGallery imageGallery);

	ImageGallery findImageGalleryById(long id);

	public void updateImageGallery(ImageGallery i);

	public void removeImageGallery(long id);

}
