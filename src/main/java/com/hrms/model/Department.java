/**
 * 
 */
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

/**
 * @author Access
 *
 */
@Entity
@Table(name = "M_DEPARTMENT")

public class Department implements Serializable
{

	/**
	 * Surendra Access 
	 */
	private static final long serialVersionUID = -4001846236713344340L;

	@Id
	@Size(max =15)
	@Column(name = "DEPARTMENT_CODE")
	private String departmentCode;
	@Size(max =60)
	@Column(name = "DEP_NAME")
	private String deptName;
	@ManyToOne
	@JoinColumn(name ="DEPH_Code",updatable = false)
	private Employee empCode;
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	@Size(max =50)
    @Column(name = "INS_BY",updatable = false)
	private String insBy;
	
	@Column(name = "INS_DATE",updatable = false)
	private Date insDate =new Date();
	@Size(max =50)
	@Column(name = "UPD_BY",insertable = false)
	private String updBy;
	
	@Column(name = "UPD_DATE",insertable = false)
	private Date  updDate = new Date();
	

	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName.substring(0, 1).toUpperCase()+ deptName.substring(1);
	}
	
	public Employee getEmpCode() {
		return empCode;
	}
	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
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
	
	