package com.wn.loanapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partner_role")
public class PartnerRole {

	@Id
    @Column(name="partner_id")
	private int partnerId;
	
	@Column(name="role_id")
	private String roleId;

	/**
	 * @return the partnerId
	 */
	public int getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
