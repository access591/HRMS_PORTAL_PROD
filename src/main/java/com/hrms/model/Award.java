package com.hrms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Entity
@Table(name = "M_AWARD")
public class Award implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7852182075397663902L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 100)
	@Column(name = "AWARD_NAME")
	private String awardName;
	@Size(max = 1)
	@Column(name = "ACTIVE_YN")
	private String active;
	@Size(max = 50)
	@Column(name = "INS_BY", updatable = false)
	private String insBy;

	@Column(name = "INS_DATE", updatable = false)
	private Date insDate = new Date();
	@Size(max = 50)
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

		public String getAwardName() {
			return awardName;
		}

		public void setAwardName(String awardName) {
			this.awardName = awardName.substring(0, 1).toUpperCase()+ awardName.substring(1);
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
