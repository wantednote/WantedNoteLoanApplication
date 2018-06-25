package com.wn.loanapp.dto;

import java.util.Date;

public class LoanDetailsDTO {
	
	private String orderNo;
	
	private String distributorName;
	
	private String firstName;
	
	private String tnDate;
	
	private Long amount;

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * @return the distributorName
	 */
	public String getDistributorName() {
		return distributorName;
	}

	/**
	 * @param distributorName the distributorName to set
	 */
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the tnDate
	 */
	public String getTnDate() {
		return tnDate;
	}

	/**
	 * @param tnDate the tnDate to set
	 */
	public void setTnDate(String tnDate) {
		this.tnDate = tnDate;
	}

	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
}
