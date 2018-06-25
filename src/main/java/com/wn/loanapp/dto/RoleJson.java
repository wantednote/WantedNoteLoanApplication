package com.wn.loanapp.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opencsv.bean.CsvBindByName;

//@Entity
//@JsonInclude(Include.NON_EMPTY)
public class RoleJson {

	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;*/

	@CsvBindByName
	private String role;
	
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
