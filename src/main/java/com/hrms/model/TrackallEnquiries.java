package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "EMPLOYEE_ENQUIRY")
public class TrackallEnquiries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3100963585510436672L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ENQ_ID")
	private long id;

	@ManyToOne
	@JoinColumn(name = "EMP_CODE",updatable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Employee empCode;

	@ManyToOne
	@JoinColumn(name = "DESIGN_CODE",updatable = false)
	private Designation desgCode;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE",updatable = false)
	private Category categoryCode;

	@Column(name = "PRESENT_POSTING")
	private String prsentPosting;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	private Date dob;

	@Column(name = "DEPUTATION_FILE_NO")
	private String depuFileNo;

	@Column(name = "DEPUTATION_PERIOD")
	private String deputationPeriod;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DEPUTATION_DATE")
	private Date deputationDate;

	@Column(name = "UNDER_RULE_7_FILE_NO")
	private String underRule7FileNo;

	@Column(name = "UNDER_RULE_8_FILE_NO")
	private String underRule8FileNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UNDER_RULE_8_PERIOD")
	private Date underRule8Period;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UNDER_RULE_8_DATE")
	private Date underRule8Date;

	@Column(name = "VIGILANCE_INQ_FILE_NO")
	private String vigilanceFileNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "VIGILANCE_INQ_PERIOD")
	private Date vigilanceInqPeriod;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "VIGILANCE_INQ_DATE")
	private Date vigilanceInqDate;

	@Column(name = "SUSPENTION_FILE_NO")
	private String suspentionFileNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SUSPENTION_FILE_PERIOD")
	private Date suspentionFilePeriod;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "SUSPENTION_FILE_DATE")
	private Date suspentionFileDate;

	@Column(name = "PROMOTION_FILE_NO")
	private String promtionFileNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PROMOTION_FILE_DATE")
	private Date promtionFileDate;

	@Column(name = "ACP_FILE_NO")
	private String acpFileNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ACP_FILE_DATE")
	private Date acpFileDate;

	@Column(name = "ARP_FILE_NO")
	private String arpFileNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ARP_FILE_DATE")
	private Date arpFileDate;

	@Column(name = "ACR_FILE_NO")
	private String acrFileNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "ACR_FILE_DATE")
	private Date acrFileDate;

	@Column(name = "TRAINING_FILE_NO")
	private String trainingFileNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "TRAINING_FILE_DATE")
	private Date trainingFileDate;

	@Column(name = "LTC_FILE_NO")
	private String ltcFileNo;

	@Column(name = "LTC_FILE_DATE")
	private Date ltcFileDate;

	@Column(name = "LEAVE_ACC_FILE_NO")
	private String leaveAccFileNo;
	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVE_ACC_FILE_DATE")
	private Date leaveAccFileDate;

	@Column(name = "AWARD")
	private String award;
	@Temporal(TemporalType.DATE)
	@Column(name = "AWARD_DATE")
	private Date awardDate;

	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();

	@Column(name = "UPD_BY", insertable = false)
	private String updBy;

	@Column(name = "UPD_DATE", insertable = false)
	private Date updDate = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getEmpCode() {
		return empCode;
	}

	public void setEmpCode(Employee empCode) {
		this.empCode = empCode;
	}



	public Designation getDesgCode() {
		return desgCode;
	}

	public void setDesgCode(Designation desgCode) {
		this.desgCode = desgCode;
	}

	public Category getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Category categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getPrsentPosting() {
		return prsentPosting;
	}

	public void setPrsentPosting(String prsentPosting) {
		this.prsentPosting = prsentPosting;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDepuFileNo() {
		return depuFileNo;
	}

	public void setDepuFileNo(String depuFileNo) {
		this.depuFileNo = depuFileNo;
	}

	public String getDeputationPeriod() {
		return deputationPeriod;
	}

	public void setDeputationPeriod(String deputationPeriod) {
		this.deputationPeriod = deputationPeriod;
	}

	public Date getDeputationDate() {
		return deputationDate;
	}

	public void setDeputationDate(Date deputationDate) {
		this.deputationDate = deputationDate;
	}

	public String getUnderRule7FileNo() {
		return underRule7FileNo;
	}

	public void setUnderRule7FileNo(String underRule7FileNo) {
		this.underRule7FileNo = underRule7FileNo;
	}

	public String getUnderRule8FileNo() {
		return underRule8FileNo;
	}

	public void setUnderRule8FileNo(String underRule8FileNo) {
		this.underRule8FileNo = underRule8FileNo;
	}

	public Date getUnderRule8Period() {
		return underRule8Period;
	}

	public void setUnderRule8Period(Date underRule8Period) {
		this.underRule8Period = underRule8Period;
	}

	public Date getUnderRule8Date() {
		return underRule8Date;
	}

	public void setUnderRule8Date(Date underRule8Date) {
		this.underRule8Date = underRule8Date;
	}

	public String getVigilanceFileNo() {
		return vigilanceFileNo;
	}

	public void setVigilanceFileNo(String vigilanceFileNo) {
		this.vigilanceFileNo = vigilanceFileNo;
	}

	public Date getVigilanceInqPeriod() {
		return vigilanceInqPeriod;
	}

	public void setVigilanceInqPeriod(Date vigilanceInqPeriod) {
		this.vigilanceInqPeriod = vigilanceInqPeriod;
	}

	public Date getVigilanceInqDate() {
		return vigilanceInqDate;
	}

	public void setVigilanceInqDate(Date vigilanceInqDate) {
		this.vigilanceInqDate = vigilanceInqDate;
	}

	public String getSuspentionFileNo() {
		return suspentionFileNo;
	}

	public void setSuspentionFileNo(String suspentionFileNo) {
		this.suspentionFileNo = suspentionFileNo;
	}

	public Date getSuspentionFilePeriod() {
		return suspentionFilePeriod;
	}

	public void setSuspentionFilePeriod(Date suspentionFilePeriod) {
		this.suspentionFilePeriod = suspentionFilePeriod;
	}

	public Date getSuspentionFileDate() {
		return suspentionFileDate;
	}

	public void setSuspentionFileDate(Date suspentionFileDate) {
		this.suspentionFileDate = suspentionFileDate;
	}

	public String getPromtionFileNo() {
		return promtionFileNo;
	}

	public void setPromtionFileNo(String promtionFileNo) {
		this.promtionFileNo = promtionFileNo;
	}

	public Date getPromtionFileDate() {
		return promtionFileDate;
	}

	public void setPromtionFileDate(Date promtionFileDate) {
		this.promtionFileDate = promtionFileDate;
	}

	public String getAcpFileNo() {
		return acpFileNo;
	}

	public void setAcpFileNo(String acpFileNo) {
		this.acpFileNo = acpFileNo;
	}

	public Date getAcpFileDate() {
		return acpFileDate;
	}

	public void setAcpFileDate(Date acpFileDate) {
		this.acpFileDate = acpFileDate;
	}

	public String getArpFileNo() {
		return arpFileNo;
	}

	public void setArpFileNo(String arpFileNo) {
		this.arpFileNo = arpFileNo;
	}

	public Date getArpFileDate() {
		return arpFileDate;
	}

	public void setArpFileDate(Date arpFileDate) {
		this.arpFileDate = arpFileDate;
	}

	public String getAcrFileNo() {
		return acrFileNo;
	}

	public void setAcrFileNo(String acrFileNo) {
		this.acrFileNo = acrFileNo;
	}

	public Date getAcrFileDate() {
		return acrFileDate;
	}

	public void setAcrFileDate(Date acrFileDate) {
		this.acrFileDate = acrFileDate;
	}

	public String getTrainingFileNo() {
		return trainingFileNo;
	}

	public void setTrainingFileNo(String trainingFileNo) {
		this.trainingFileNo = trainingFileNo;
	}

	public Date getTrainingFileDate() {
		return trainingFileDate;
	}

	public void setTrainingFileDate(Date trainingFileDate) {
		this.trainingFileDate = trainingFileDate;
	}

	public String getLtcFileNo() {
		return ltcFileNo;
	}

	public void setLtcFileNo(String ltcFileNo) {
		this.ltcFileNo = ltcFileNo;
	}

	public Date getLtcFileDate() {
		return ltcFileDate;
	}

	public void setLtcFileDate(Date ltcFileDate) {
		this.ltcFileDate = ltcFileDate;
	}

	public String getLeaveAccFileNo() {
		return leaveAccFileNo;
	}

	public void setLeaveAccFileNo(String leaveAccFileNo) {
		this.leaveAccFileNo = leaveAccFileNo;
	}

	public Date getLeaveAccFileDate() {
		return leaveAccFileDate;
	}

	public void setLeaveAccFileDate(Date leaveAccFileDate) {
		this.leaveAccFileDate = leaveAccFileDate;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public Date getAwardDate() {
		return awardDate;
	}

	public void setAwardDate(Date awardDate) {
		this.awardDate = awardDate;
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
