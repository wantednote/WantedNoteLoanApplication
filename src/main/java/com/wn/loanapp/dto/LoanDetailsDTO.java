package com.wn.loanapp.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LoanDetailsDTO {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM D, YYYY");
	
	private String orderNo;
	
	private String distributorName;
	
	private String firstName;
	
	private String tnDate;
	
	private Long amount;
	
	/*public Date getDateConverted(String timeZone)throws ParseException{
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		return dateFormat.parse(this.tnDate);
	}
	
	public void setDateConverted(Date date, String timeZone){
		dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
		this.tnDate= dateFormat.format(date);
	}*/
	
	
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
