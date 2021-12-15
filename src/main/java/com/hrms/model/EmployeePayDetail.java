package com.hrms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity(name = "Emp_Pay_detail")
public class EmployeePayDetail implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4646106332927433733L;

	@Id
	@Column(length=25,name="EMP_CODE")
	private String empCode;
	


	@Column(length=25,name="EMP_NAME")  
	private String empName;
	
	@Column(length=25,name="DEPT_CODE")
	private String deptCode;
	
	@Column(length=25,name="DEPT_NAME")
	private String deptName;
	
	@Column(length=25,name="DEPT_CODE1")
	private String deptCode1;
	

	@Column(length=25,name="WEF_DATE")
	private String wefdate;
	
	@Column(length=25,name="DESIG_CODE")
	private String desigCode;

	@Column(length=25,name="EMP_INSURANCE_NO")
	private String empInsuranceNo;
	
	@Column(length=25,name="PF_NO")
	private String pfNo;
	
	@Column(length=25,name="PF_EFFECTIVE_DATE")
	private String pfEffectiveDate;
	
	@Column(length=25,name="GROUP_INSURANCE_NO")
	private String groupInsuranceNo;
	
	@Column(length=25,name="EMP_ESI_NO")
	private String empEsiNo;
	

	@Column(length=25,name="ESI_EFFICTIVE_DATE")
	private String esiEffectiveDate;
	
	@Column(length=25,name="ARREAR_DUE")
	private String arrearDue;
	
	@Column(length=25,name="ARREAR_PAID")
	private String arrearPaid;
	
	@Column(length=25,name="REIMBURSE_CODE")
	private String reimburseAcode;
	
	@Column(length=25,name="A_NAME")
	private String aName;
	
	@Column(length=25,name="GROSS1")
	private String gross1;
	
	@Column(length=25,name="D_GROSS")
	private String dGross;
	
	@Column(length=25,name="GROSS1_Y")
	private String gross1_y;
	
	@Column(length=25,name="BASIC_PAY")
	private String basicPay;
	
	@Column(length=25,name="HRA_RATE")
	private String hraRate;
	
	@Column(length=25,name="HRA_VALUE")
	private String hraValue;
	

	@Column(length=25,name="COMPANY_LEASED_ACC")
	private String companyLeasedAcc;
	
	@Column(length=25,name="DA")
	private String da;
	
	@Column(length=25,name="MEDICAL_ALLOW")
	private String medicalAllow;
	
	@Column(length=25,name="CCA")
	private String cca;
	
	@Column(length=25,name="CONVEYANCE_ALLOW")
	private String conveyanceAllow;

	
	@Column(length=25,name="BONUS")
	private String bonus;
	

	@Column(length=25,name="EPF")
	private String epf;
	
	@Column(length=25,name="ESI")
	private String esi;
	
	@Column(length=25,name="PENSION_FUND")
	private String pensionFund;
	
	@Column(length=25,name="FPF")
	private String fpf;
	
	@Column(length=25,name="BASIC_PAY_Y")
	private String basicPayY;
	

	@Column(length=25,name="HRA_RATE_Y")
	private String hraRateY;
	
	@Column(length=25,name="HRA_VALUE_Y")
	private String hravalueY;
	

	@Column(length=25,name="DAY")
	private String daY;
	
	@Column(length=25,name="MEDICAL_ALLOW_Y")
	private String medicalAllowY;
	
	@Column(length=25,name="CCA_Y")
	private String ccaY;
	
	@Column(length=25,name="CONVEYANCE_ALLOW_Y")
	private String conveyanceAllowY;
	
	@Column(length=25,name="BONUS_Y")
	private String bonusY;
	

	@Column(length=25,name="OVERTIME_YN")
	private String overtimeYN;
	
	@Column(length=25,name="OVERTIME_RATE")
	private String overtimerate;
	
	@Column(length=25,name="PF_TYPE")
	private String pfType;
	
	@Column(length=25,name="FPF_PERC")
	private String fpfPerc;
	
	@Column(length=25,name="PF_PERC")
	private String pfPerc;

	@Column(length=25,name="EPF_PERC")
	private String epfPerc;
	
	@Column(length=25,name="TOTAL_ALLOW")
	private String totalAllow;
	
	@Column(length=25,name="TOTAL_DEDUCTION")
	private String totalDeduction;
	
	@Column(length=25,name="TOTAL_ALLOW_Y")
	private String totalAllowY;
	
	@Column(length=25,name="TOTAL_DEDUCTION_Y")
	private String totalDedductionY;
	

	@Column(length=25,name="AMOUNT_PAYABLE_Y")
	private String amountPayableY;
	
	@Column(length=25,name="FPF1_Y")
	private String fpf1Y;
	
	@Column(length=25,name="EPF1_Y")
	private String epf1Y;
	
	@Column(length=25,name="ESI1_Y")
	private String esi1Y;
	
	@Column(length=25,name="PENSION_FUND1_Y")
	private String pensionFund1Y;
	

	@Column(length=25,name="ADMINISTRATIVE_CHARGES")
	private String administrativeCharges;
	
	@Column(length=25,name="C_CODE")
	private String cCode;
	

	@Column(length=25,name="ADMINSTRATIVE_CHARGE_Y")
	private String administrativeChargesY;
	
	@Column(length=25,name="CALCULATE_EPF")
	private String calculateEpf;
	
	@Column(length=25,name="CALCULATE_ESI_2")
	private String calculateEsi2;
	
	@Column(length=25,name="TDS")
	private String tds;
	
	@Column(length=25,name="SURCHARGE")
	private String surCharge;
	
	@Column(length=25,name="ED_CHESS")
	private String edChess;
	
	@Column(length=25,name="ED_CODE")
	private String edCode;
	
	@Column(length=25,name="SECTION_LOV")
	private String sectionLov;
	

	@Column(length=25,name="CTC_1")
	private String ctc1;
	
	@Column(length=25,name="CTC")
	private String ctc;
	
	@Column(length=25,name="GROSS")
	private String gross;
	
	@Column(length=25,name="GROSS_Y")
	private String grossY;
	

	@Column(length=25,name="EPF_Y")
	private String epfY;


	@Column(length=25,name="FPF_Y")
	private String fpfY;

	@Column(length=25,name="ESI_Y")
	private String esiY;
	
	@Column(length=25,name="SECTION_DESC")
	private String sectionDesc;

	@Column(length=25,name="PENSION_FUND_Y")
	private String pensionFundY;

	@Column(length=25,name="INSURANCE")
	private String insurance;
	
	@Column(length=25,name="INSURANCE_Y")
	private String insuranceY;
	
	@Column(length=25,name="ADDTION_ALLOW")
	private String addtionAllow;
	
	@Column(length=25,name="PF_ALLOW")
	private String pfAllow;
	
	@Column(length=25,name="ESIC")
	private String esiC;
	

	@Column(length=25,name="ESICY")

	private String esiCY;
	
	@Column(length=25,name="NET_PAY")
	private String netPay;

	public EmployeePayDetail() {
		super();
		
	}

	public EmployeePayDetail(String empCode, String empName, String deptCode, String deptName, String deptCode1,
			String wefdate, String desigCode, String empInsuranceNo, String pfNo, String pfEffectiveDate,
			String groupInsuranceNo, String empEsiNo, String esiEffectiveDate, String arrearDue, String arrearPaid,
			String reimburseAcode, String aName, String gross1, String dGross, String gross1_y, String basicPay,
			String hraRate, String hraValue, String companyLeasedAcc, String da, String medicalAllow, String cca,
			String conveyanceAllow, String bonus, String epf, String esi, String pensionFund, String fpf,
			String basicPayY, String hraRateY, String hravalueY, String daY, String medicalAllowY, String ccaY,
			String conveyanceAllowY, String bonusY, String overtimeYN, String overtimerate, String pfType,
			String fpfPerc, String pfPerc, String epfPerc, String totalAllow, String totalDeduction, String totalAllowY,
			String totalDedductionY, String amountPayableY, String fpf1y, String epf1y, String esi1y,
			String pensionFund1Y, String administrativeCharges, String cCode, String administrativeChargesY,
			String calculateEpf, String calculateEsi2, String tds, String surCharge, String edChess, String edCode,
			String sectionLov, String ctc1, String ctc, String gross, String grossY, String epfY, String fpfY,
			String esiY, String sectionDesc, String pensionFundY, String insurance, String insuranceY,
			String addtionAllow, String pfAllow, String esiC, String esiCY, String netPay) {
		super();
		this.empCode = empCode;
		this.empName = empName;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptCode1 = deptCode1;
		this.wefdate = wefdate;
		this.desigCode = desigCode;
		this.empInsuranceNo = empInsuranceNo;
		this.pfNo = pfNo;
		this.pfEffectiveDate = pfEffectiveDate;
		this.groupInsuranceNo = groupInsuranceNo;
		this.empEsiNo = empEsiNo;
		this.esiEffectiveDate = esiEffectiveDate;
		this.arrearDue = arrearDue;
		this.arrearPaid = arrearPaid;
		this.reimburseAcode = reimburseAcode;
		this.aName = aName;
		this.gross1 = gross1;
		this.dGross = dGross;
		this.gross1_y = gross1_y;
		this.basicPay = basicPay;
		this.hraRate = hraRate;
		this.hraValue = hraValue;
		this.companyLeasedAcc = companyLeasedAcc;
		this.da = da;
		this.medicalAllow = medicalAllow;
		this.cca = cca;
		this.conveyanceAllow = conveyanceAllow;
		this.bonus = bonus;
		this.epf = epf;
		this.esi = esi;
		this.pensionFund = pensionFund;
		this.fpf = fpf;
		this.basicPayY = basicPayY;
		this.hraRateY = hraRateY;
		this.hravalueY = hravalueY;
		this.daY = daY;
		this.medicalAllowY = medicalAllowY;
		this.ccaY = ccaY;
		this.conveyanceAllowY = conveyanceAllowY;
		this.bonusY = bonusY;
		this.overtimeYN = overtimeYN;
		this.overtimerate = overtimerate;
		this.pfType = pfType;
		this.fpfPerc = fpfPerc;
		this.pfPerc = pfPerc;
		this.epfPerc = epfPerc;
		this.totalAllow = totalAllow;
		this.totalDeduction = totalDeduction;
		this.totalAllowY = totalAllowY;
		this.totalDedductionY = totalDedductionY;
		this.amountPayableY = amountPayableY;
		fpf1Y = fpf1y;
		epf1Y = epf1y;
		esi1Y = esi1y;
		this.pensionFund1Y = pensionFund1Y;
		this.administrativeCharges = administrativeCharges;
		this.cCode = cCode;
		this.administrativeChargesY = administrativeChargesY;
		this.calculateEpf = calculateEpf;
		this.calculateEsi2 = calculateEsi2;
		this.tds = tds;
		this.surCharge = surCharge;
		this.edChess = edChess;
		this.edCode = edCode;
		this.sectionLov = sectionLov;
		this.ctc1 = ctc1;
		this.ctc = ctc;
		this.gross = gross;
		this.grossY = grossY;
		this.epfY = epfY;
		this.fpfY = fpfY;
		this.esiY = esiY;
		this.sectionDesc = sectionDesc;
		this.pensionFundY = pensionFundY;
		this.insurance = insurance;
		this.insuranceY = insuranceY;
		this.addtionAllow = addtionAllow;
		this.pfAllow = pfAllow;
		this.esiC = esiC;
		this.esiCY = esiCY;
		this.netPay = netPay;
	}

	

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode1() {
		return deptCode1;
	}

	public void setDeptCode1(String deptCode1) {
		this.deptCode1 = deptCode1;
	}

	public String getWefdate() {
		return wefdate;
	}

	public void setWefdate(String wefdate) {
		this.wefdate = wefdate;
	}

	public String getDesigCode() {
		return desigCode;
	}

	public void setDesigCode(String desigCode) {
		this.desigCode = desigCode;
	}

	public String getEmpInsuranceNo() {
		return empInsuranceNo;
	}

	public void setEmpInsuranceNo(String empInsuranceNo) {
		this.empInsuranceNo = empInsuranceNo;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getPfEffectiveDate() {
		return pfEffectiveDate;
	}

	public void setPfEffectiveDate(String pfEffectiveDate) {
		this.pfEffectiveDate = pfEffectiveDate;
	}

	public String getGroupInsuranceNo() {
		return groupInsuranceNo;
	}

	public void setGroupInsuranceNo(String groupInsuranceNo) {
		this.groupInsuranceNo = groupInsuranceNo;
	}

	public String getEmpEsiNo() {
		return empEsiNo;
	}

	public void setEmpEsiNo(String empEsiNo) {
		this.empEsiNo = empEsiNo;
	}

	public String getEsiEffectiveDate() {
		return esiEffectiveDate;
	}

	public void setEsiEffectiveDate(String esiEffectiveDate) {
		this.esiEffectiveDate = esiEffectiveDate;
	}

	public String getArrearDue() {
		return arrearDue;
	}

	public void setArrearDue(String arrearDue) {
		this.arrearDue = arrearDue;
	}

	public String getArrearPaid() {
		return arrearPaid;
	}

	public void setArrearPaid(String arrearPaid) {
		this.arrearPaid = arrearPaid;
	}

	public String getReimburseAcode() {
		return reimburseAcode;
	}

	public void setReimburseAcode(String reimburseAcode) {
		this.reimburseAcode = reimburseAcode;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getGross1() {
		return gross1;
	}

	public void setGross1(String gross1) {
		this.gross1 = gross1;
	}

	public String getdGross() {
		return dGross;
	}

	public void setdGross(String dGross) {
		this.dGross = dGross;
	}

	public String getGross1_y() {
		return gross1_y;
	}

	public void setGross1_y(String gross1_y) {
		this.gross1_y = gross1_y;
	}

	public String getBasicPay() {
		return basicPay;
	}

	public void setBasicPay(String basicPay) {
		this.basicPay = basicPay;
	}

	public String getHraRate() {
		return hraRate;
	}

	public void setHraRate(String hraRate) {
		this.hraRate = hraRate;
	}

	public String getHraValue() {
		return hraValue;
	}

	public void setHraValue(String hraValue) {
		this.hraValue = hraValue;
	}

	public String getCompanyLeasedAcc() {
		return companyLeasedAcc;
	}

	public void setCompanyLeasedAcc(String companyLeasedAcc) {
		this.companyLeasedAcc = companyLeasedAcc;
	}

	public String getDa() {
		return da;
	}

	public void setDa(String da) {
		this.da = da;
	}

	public String getMedicalAllow() {
		return medicalAllow;
	}

	public void setMedicalAllow(String medicalAllow) {
		this.medicalAllow = medicalAllow;
	}

	public String getCca() {
		return cca;
	}

	public void setCca(String cca) {
		this.cca = cca;
	}

	public String getConveyanceAllow() {
		return conveyanceAllow;
	}

	public void setConveyanceAllow(String conveyanceAllow) {
		this.conveyanceAllow = conveyanceAllow;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getEpf() {
		return epf;
	}

	public void setEpf(String epf) {
		this.epf = epf;
	}

	public String getEsi() {
		return esi;
	}

	public void setEsi(String esi) {
		this.esi = esi;
	}

	public String getPensionFund() {
		return pensionFund;
	}

	public void setPensionFund(String pensionFund) {
		this.pensionFund = pensionFund;
	}

	public String getFpf() {
		return fpf;
	}

	public void setFpf(String fpf) {
		this.fpf = fpf;
	}

	public String getBasicPayY() {
		return basicPayY;
	}

	public void setBasicPayY(String basicPayY) {
		this.basicPayY = basicPayY;
	}

	public String getHraRateY() {
		return hraRateY;
	}

	public void setHraRateY(String hraRateY) {
		this.hraRateY = hraRateY;
	}

	public String getHravalueY() {
		return hravalueY;
	}

	public void setHravalueY(String hravalueY) {
		this.hravalueY = hravalueY;
	}

	public String getDaY() {
		return daY;
	}

	public void setDaY(String daY) {
		this.daY = daY;
	}

	public String getMedicalAllowY() {
		return medicalAllowY;
	}

	public void setMedicalAllowY(String medicalAllowY) {
		this.medicalAllowY = medicalAllowY;
	}

	public String getCcaY() {
		return ccaY;
	}

	public void setCcaY(String ccaY) {
		this.ccaY = ccaY;
	}

	public String getConveyanceAllowY() {
		return conveyanceAllowY;
	}

	public void setConveyanceAllowY(String conveyanceAllowY) {
		this.conveyanceAllowY = conveyanceAllowY;
	}

	public String getBonusY() {
		return bonusY;
	}

	public void setBonusY(String bonusY) {
		this.bonusY = bonusY;
	}

	public String getOvertimeYN() {
		return overtimeYN;
	}

	public void setOvertimeYN(String overtimeYN) {
		this.overtimeYN = overtimeYN;
	}

	public String getOvertimerate() {
		return overtimerate;
	}

	public void setOvertimerate(String overtimerate) {
		this.overtimerate = overtimerate;
	}

	public String getPfType() {
		return pfType;
	}

	public void setPfType(String pfType) {
		this.pfType = pfType;
	}

	public String getFpfPerc() {
		return fpfPerc;
	}

	public void setFpfPerc(String fpfPerc) {
		this.fpfPerc = fpfPerc;
	}

	public String getPfPerc() {
		return pfPerc;
	}

	public void setPfPerc(String pfPerc) {
		this.pfPerc = pfPerc;
	}

	public String getEpfPerc() {
		return epfPerc;
	}

	public void setEpfPerc(String epfPerc) {
		this.epfPerc = epfPerc;
	}

	public String getTotalAllow() {
		return totalAllow;
	}

	public void setTotalAllow(String totalAllow) {
		this.totalAllow = totalAllow;
	}

	public String getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(String totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public String getTotalAllowY() {
		return totalAllowY;
	}

	public void setTotalAllowY(String totalAllowY) {
		this.totalAllowY = totalAllowY;
	}

	public String getTotalDedductionY() {
		return totalDedductionY;
	}

	public void setTotalDedductionY(String totalDedductionY) {
		this.totalDedductionY = totalDedductionY;
	}

	public String getAmountPayableY() {
		return amountPayableY;
	}

	public void setAmountPayableY(String amountPayableY) {
		this.amountPayableY = amountPayableY;
	}

	public String getFpf1Y() {
		return fpf1Y;
	}

	public void setFpf1Y(String fpf1y) {
		fpf1Y = fpf1y;
	}

	public String getEpf1Y() {
		return epf1Y;
	}

	public void setEpf1Y(String epf1y) {
		epf1Y = epf1y;
	}

	public String getEsi1Y() {
		return esi1Y;
	}

	public void setEsi1Y(String esi1y) {
		esi1Y = esi1y;
	}

	public String getPensionFund1Y() {
		return pensionFund1Y;
	}

	public void setPensionFund1Y(String pensionFund1Y) {
		this.pensionFund1Y = pensionFund1Y;
	}

	public String getAdministrativeCharges() {
		return administrativeCharges;
	}

	public void setAdministrativeCharges(String administrativeCharges) {
		this.administrativeCharges = administrativeCharges;
	}

	public String getcCode() {
		return cCode;
	}

	public void setcCode(String cCode) {
		this.cCode = cCode;
	}

	public String getAdministrativeChargesY() {
		return administrativeChargesY;
	}

	public void setAdministrativeChargesY(String administrativeChargesY) {
		this.administrativeChargesY = administrativeChargesY;
	}

	public String getCalculateEpf() {
		return calculateEpf;
	}

	public void setCalculateEpf(String calculateEpf) {
		this.calculateEpf = calculateEpf;
	}

	public String getCalculateEsi2() {
		return calculateEsi2;
	}

	public void setCalculateEsi2(String calculateEsi2) {
		this.calculateEsi2 = calculateEsi2;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getSurCharge() {
		return surCharge;
	}

	public void setSurCharge(String surCharge) {
		this.surCharge = surCharge;
	}

	public String getEdChess() {
		return edChess;
	}

	public void setEdChess(String edChess) {
		this.edChess = edChess;
	}

	public String getEdCode() {
		return edCode;
	}

	public void setEdCode(String edCode) {
		this.edCode = edCode;
	}

	public String getSectionLov() {
		return sectionLov;
	}

	public void setSectionLov(String sectionLov) {
		this.sectionLov = sectionLov;
	}

	public String getCtc1() {
		return ctc1;
	}

	public void setCtc1(String ctc1) {
		this.ctc1 = ctc1;
	}

	public String getCtc() {
		return ctc;
	}

	public void setCtc(String ctc) {
		this.ctc = ctc;
	}

	public String getGross() {
		return gross;
	}

	public void setGross(String gross) {
		this.gross = gross;
	}

	public String getGrossY() {
		return grossY;
	}

	public void setGrossY(String grossY) {
		this.grossY = grossY;
	}

	public String getEpfY() {
		return epfY;
	}

	public void setEpfY(String epfY) {
		this.epfY = epfY;
	}

	public String getFpfY() {
		return fpfY;
	}

	public void setFpfY(String fpfY) {
		this.fpfY = fpfY;
	}

	public String getEsiY() {
		return esiY;
	}

	public void setEsiY(String esiY) {
		this.esiY = esiY;
	}

	public String getSectionDesc() {
		return sectionDesc;
	}

	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	public String getPensionFundY() {
		return pensionFundY;
	}

	public void setPensionFundY(String pensionFundY) {
		this.pensionFundY = pensionFundY;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getInsuranceY() {
		return insuranceY;
	}

	public void setInsuranceY(String insuranceY) {
		this.insuranceY = insuranceY;
	}

	public String getAddtionAllow() {
		return addtionAllow;
	}

	public void setAddtionAllow(String addtionAllow) {
		this.addtionAllow = addtionAllow;
	}

	public String getPfAllow() {
		return pfAllow;
	}

	public void setPfAllow(String pfAllow) {
		this.pfAllow = pfAllow;
	}

	public String getEsiC() {
		return esiC;
	}

	public void setEsiC(String esiC) {
		this.esiC = esiC;
	}

	public String getEsiCY() {
		return esiCY;
	}

	public void setEsiCY(String esiCY) {
		this.esiCY = esiCY;
	}

	public String getNetPay() {
		return netPay;
	}

	public void setNetPay(String netPay) {
		this.netPay = netPay;
	}
	
	
	

}
