package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "M_SUB_MODULE")
public class SubModule implements Serializable {

	private static final long serialVersionUID = -6851090654306622861L;

	@Id
	@Size(max = 15)
	@Column(name = "SUB_MODULE_CODE")
	private String subModuleCode;
	@Size(max =50)
	@Column(name = "SUB_MODULE_NAME")
	private String subModuleName;
	
	@ManyToOne
	@JoinColumn(name = "MODULE_CODE", nullable = false)
	private Module moduleCode;
	@Size(max =50)
	@Column(name = "INS_BY",updatable = false)
	private String insertedBySubModule;

	@Column(name = "INS_DATE")
	private Date insertedDateSubModule =  new Date();
	@Size(max = 50)
	@Column(name = "UPDATE_BY",insertable = false)
	private String updateBySubModule;

	@Column(name = "UPDATE_DATE")
	private Date updatedDateSubModule = new Date();
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String acitveSubModule;
	@Column(name = "SEQ_NO")
	private int seqNoSubModule;
	@OneToMany(mappedBy = "subModuleCode")
	private List<Program> subModulePrograms;
	
	public List<Program> getSubModulePrograms() {
		return subModulePrograms;
	}

	public void setSubModulePrograms(List<Program> subModulePrograms) {
		this.subModulePrograms = subModulePrograms;
	}


	public String getSubModuleCode() {
		return subModuleCode;
	}

	public void setSubModuleCode(String subModuleCode) {
		this.subModuleCode = subModuleCode;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	

	public Module getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(Module moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getInsertedBySubModule() {
		return insertedBySubModule;
	}

	public void setInsertedBySubModule(String insertedBySubModule) {
		this.insertedBySubModule = insertedBySubModule;
	}

	public Date getInsertedDateSubModule() {
		return insertedDateSubModule;
	}

	public void setInsertedDateSubModule(Date insertedDateSubModule) {
		this.insertedDateSubModule = insertedDateSubModule;
	}

	public String getUpdateBySubModule() {
		return updateBySubModule;
	}

	public void setUpdateBySubModule(String updateBySubModule) {
		this.updateBySubModule = updateBySubModule;
	}

	public Date getUpdatedDateSubModule() {
		return updatedDateSubModule;
	}

	public void setUpdatedDateSubModule(Date updatedDateSubModule) {
		this.updatedDateSubModule = updatedDateSubModule;
	}

	public String getAcitveSubModule() {
		return acitveSubModule;
	}

	public void setAcitveSubModule(String acitveSubModule) {
		this.acitveSubModule = acitveSubModule;
	}

	public int getSeqNoSubModule() {
		return seqNoSubModule;
	}

	public void setSeqNoSubModule(int seqNoSubModule) {
		this.seqNoSubModule = seqNoSubModule;
	}

	

}
