package com.hrms.service;

import java.util.List;

import com.hrms.model.MiscAllowance;



public interface MiscAllowanceDeductionService
{
public void addMiscAllowanceDeduction(MiscAllowance miscAllowance);
List<MiscAllowance>getAllMiscAllowanceDeduction();
MiscAllowance findMiscAllowanceDeductionById(String id);
public void updateMiscAllowanceDeduction(MiscAllowance miscAllowance);
public void removeMiscAllowanceDeduction(String id);
}
