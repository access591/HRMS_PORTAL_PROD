package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="URL_MASTER")

public class UrlMaster implements Serializable{

	/**
	 * author Surendra Access
	 */
	private static final long serialVersionUID = 842108347597795726L;
	@Id
	@Column(name ="URL_ID")
	private int urlId;
	@Column(name ="SR_NO")
	private int srNo;
	@Column(name ="SCREEN_TYPE")
	private String screenType;
	public int getUrlId() {
		return urlId;
	}
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
}
