package com.hrms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.model.UrlDetail;
import com.hrms.repository.PageMappingDao;

@Service
public class PageMappingServiceImpl implements PageMappingService {
	@Autowired
	private PageMappingDao pageMappingDao;

	@Override
	public String PageRequestMapping(String requestMpping, int id) {

		return pageMappingDao.pageRequestMapping(requestMpping, id);

	}

	@Override
	public List<UrlDetail> getAllPages() {
		return pageMappingDao.findAll();
	
	}

	@Override
	public void addPage(UrlDetail urlDetail) {
		urlDetail.setInsertedDate(new Date());
		this.pageMappingDao.saveOrUpdate(urlDetail);

	}

	@Override
	public boolean checkUrlDetailExists(UrlDetail urlDetail) {
		UrlDetail e = pageMappingDao.existOrNot(urlDetail);
		if (e != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void removePage(String id) {
		this.pageMappingDao.delete(id);

	}

	@Override
	public UrlDetail findUrlDetailById(String id) {
		return pageMappingDao.findById(id);
	}

	@Override
	public void updatePage(UrlDetail u) {
		u.setPageName(u.getPageName());
		u.setReqMapping(u.getReqMapping());
		u.setUpdatedDate(u.getUpdatedDate());
		this.pageMappingDao.saveOrUpdate(u);

	}
}
