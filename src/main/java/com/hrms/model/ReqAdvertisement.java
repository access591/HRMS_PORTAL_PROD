package com.hrms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name="M_EMPREQ_ADVERTISEMENT")
public class ReqAdvertisement {
	
	@Id
	@Column(name="ADVT_CODE")
	private String advtCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ADVT_DATE")
	private Date advtDate;
	
	@Size(max=250)
	@Column(name="REMARKS")
	private String remarks;
	
	@Size(max=50)
	@Column(name="INS_BY")
	private String insBy;
	
	@Column(name="INS_DATE")
	private Date insDate;
	
	@OneToMany(mappedBy = "reqAdvertisement",cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<ReqAdvertisementDetail> listReqAdvertisementDetail;

	public String getAdvtCode() {
		return advtCode;
	}

	public void setAdvtCode(String advtCode) {
		this.advtCode = advtCode;
	}

	public Date getAdvtDate() {
		return advtDate;
	}

	public void setAdvtDate(Date advtDate) {
		this.advtDate = advtDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<ReqAdvertisementDetail> getListReqAdvertisementDetail() {
		return listReqAdvertisementDetail;
	}

	public void setListReqAdvertisementDetail(List<ReqAdvertisementDetail> listReqAdvertisementDetail) {
		this.listReqAdvertisementDetail = listReqAdvertisementDetail;
	}
	
	

	
	

}
