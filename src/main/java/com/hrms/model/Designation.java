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
 * @author Access surendra
 *
 */
@Entity
@Table(name = "M_DESIGNATION")

public class Designation implements Serializable{

	private static final long serialVersionUID = -5996491080389467939L;
	@Id
	@Size(max =15)
	@Column(name = "DESG_CODE")
	private String desgCode;
	@Size(max =50)
	@Column(name = "DESG_NAME")
	private String desgName;
	@ManyToOne
	@JoinColumn(name = "CATEG_CODE")
	private Category categoryCode;
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
	@Size(max =1)
	@Column(name = "ACTIVE_YN")
	private String active;
	
	public String getActive() {
		return active;
	}

	public String getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(String desgCode) {
		this.desgCode = desgCode;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName.substring(0, 1).toUpperCase()+ desgName.substring(1);
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

	public void setActive(String active) {
		this.active = active;
	}

	public Category getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Category categoryCode) {
		this.categoryCode = categoryCode;
	}

	
	
	
}
