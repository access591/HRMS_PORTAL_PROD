package com.hrms.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "M_URIGHTS")
public class UserRights implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3115353075342584614L;

	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_CODE")
	private UserEntity userCode;
	
	@ManyToOne
	@JoinColumn(name ="MODULE_CODE",updatable = false)
	private Module moduleCode;
	@ManyToOne
	@JoinColumn(name = "SUB_MODULE_CODE",updatable = false)
	private SubModule subModuleCode;

	@ManyToOne
	@JoinColumn(name = "PRG_CODE",updatable = false)
	private Program prgCode;

	@Column(name = "ACTIVE_YN")
	private String active;
	
	
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public UserEntity getUserCode() {
		return userCode;
	}
	public void setUserCode(UserEntity userCode) {
		this.userCode = userCode;
	}

	public Module getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(Module moduleCode) {
		this.moduleCode = moduleCode;
	}

	public SubModule getSubModuleCode() {
		return subModuleCode;
	}
	public void setSubModuleCode(SubModule subModuleCode) {
		this.subModuleCode = subModuleCode;
	}
	
	public Program getPrgCode() {
		return prgCode;
	}
	public void setPrgCode(Program prgCode) {
		this.prgCode = prgCode;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getInsBy() {
		return insBy;
	}
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	
	
	
	
}
