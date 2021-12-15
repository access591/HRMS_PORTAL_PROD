package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_PROGRAM")
public class Program implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4543664601105931061L;

	@Id
	@Size(max =15)
	@Column(name = "PRG_CODE")
	private String programCode;
	@Size(max = 50)
	@Column(name = "PRG_NAME")
	private String programName;
	@Size(max = 1)
	@Column(name = "PRG_TYPE")
	private String programType;
	@Size(max = 100)
	@Column(name = "PRG_HREF_NAME")
	private String programHrefName;
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String activeYn;
	
	
	@Size(max =50)
	@Column(name = "INS_BY",updatable = false)
	private String insertedBy;
	@Column(name = "INS_DATE",updatable = false)
	private Date insertedDate =new Date();
	@Size(max =50)
	@Column(name = "UPDATE_BY",insertable = false)
	private String updatedBy;

	@Column(name = "UPDATE_DATE",insertable = false)
	private Date updatedDate = new Date();

	@ManyToOne
	@JoinColumn(name = "SUB_MODULE_CODE", nullable = false)
	private SubModule subModuleCode;

	@ManyToOne
	@JoinColumn(name = "MODULE_CODE", nullable = false)
	private Module pModuleCode;
	@Column(name = "SEQ_NO")
	private int seqProgram;
	
	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}



	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getProgramHrefName() {
		return programHrefName;
	}

	public void setProgramHrefName(String programHrefName) {
		this.programHrefName = programHrefName;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getSeqProgram() {
		return seqProgram;
	}

	public void setSeqProgram(int seqProgram) {
		this.seqProgram = seqProgram;
	}

	public SubModule getSubModuleCode() {
		return subModuleCode;
	}

	public void setSubModuleCode(SubModule subModuleCode) {
		this.subModuleCode = subModuleCode;
	}

	public Module getpModuleCode() {
		return pModuleCode;
	}

	public void setpModuleCode(Module pModuleCode) {
		this.pModuleCode = pModuleCode;
	}

}
