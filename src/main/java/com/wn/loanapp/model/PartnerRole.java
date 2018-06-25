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
	private long partnerId;
	
	@Column(name="role_id")
	private long roleId;
	
	/**
	 * @return the partnerId
	 */
	public long getPartnerId() {
		return partnerId;
	}

	/**
	 * @param partnerId the partnerId to set
	 */
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * @return the roleId
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
