package com.hrms.util;

import java.util.List;

import com.hrms.model.LeaveGrant;

public class LeaveUtil {
	
	private List<LeaveGrant> leaveGrants;
	private LeaveGrant lv;
	
	
	public List<LeaveGrant> getLeaveGrants() {
		return leaveGrants;
	}
	public void setLeaveGrants(List<LeaveGrant> leaveGrants) {
		this.leaveGrants = leaveGrants;
	}
	public LeaveGrant getLv() {
		return lv;
	}
	public void setLv(LeaveGrant lv) {
		this.lv = lv;
	}
	
	

}
