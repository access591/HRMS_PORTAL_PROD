package com.hrms.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.model.SubModule;
import com.hrms.repository.SubModuleDao;

@Service
public class SubModuleServiceImpl  implements SubModuleService{

	@Autowired
	private SubModuleDao subModuleDao;

	@Override
	public List<SubModule> getAllSubModules() {

		return subModuleDao.findAll();
	

	}

	@Override
	public void addSubModule(SubModule subModule) {
		subModule.setSubModuleCode(subModuleDao.getMaxId("SUB"));
		this.subModuleDao.saveOrUpdate(subModule);

	}

	@Override
	public SubModule findSubModuleById(String id) {
		return subModuleDao.findById(id);
	}

	@Override
	public void updateSubModule(SubModule subModule) {
	
		this.subModuleDao.saveOrUpdate(subModule);

	}

	@Override
	public void removeSubModule(String id) {
		this.subModuleDao.delete(id);
	}

	@Override
	public boolean checkSubModuleExists(SubModule subModule) {
		SubModule e = subModuleDao.checkSubModuleExists(subModule);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SubModule> getActiveSubModules() {
		return subModuleDao.findAll();
	}

	@Override
	public boolean checkSubModuleSeqExists(SubModule subModule) {
		
		SubModule e = subModuleDao.checkSubModuleSeqExists(subModule);
		if (e != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SubModule> findSubModuleByModuleCode(String id) {
	
		return subModuleDao.findSubModuleByModuleCode(id);
	}

}
