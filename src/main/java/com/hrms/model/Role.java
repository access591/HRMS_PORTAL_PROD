package com.hrms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="M_ROLE")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private long roleId;
	
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "ROlE_PROGRAM_CODE")
	private String programCode;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	Set<UserRole> userRole = new HashSet<>();

	public Role() {
		super();
		System.out.println("roleee");
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	
	

}
