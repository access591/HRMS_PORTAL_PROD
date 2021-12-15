package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="URL_DTL")
public class UrlDetail implements Serializable {

	/**
	 * 
	 * Auth Surendra Access
	 * 
	 */
	private static final long serialVersionUID = 842108347597795726L;
	@Id
	@Column(name ="URL_ID")
	private String  urlId;
	
	@Column(name ="REQ_MAPPING")
	private String	reqMapping;
	
	@Column(name ="PAGE_NAME")
	private String pageName;
	
	
	@Column(name = "INS_BY")
	private String insertedBy;

	@Column(name = "INS_DATE")
	private Date insertedDate;

	@Column(name = "UPDATE_BY")
	private String updateBy;

	@Column(name = "UPDATE_DATE")
	private Date updatedDate;
	
	
	
	

	
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
	public String getReqMapping() {
		return reqMapping;
	}
	public void setReqMapping(String reqMapping) {
		this.reqMapping = reqMapping;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
			
	
}
