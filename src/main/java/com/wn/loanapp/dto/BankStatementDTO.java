package com.wn.loanapp.dto;

public class BankStatementDTO {
	
	private String settleDate;
	
	private String settleAmount;
	
	private String onlinePaymentId;

	/**
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}

	/**
	 * @param settleDate the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	/**
	 * @return the settleAmount
	 */
	public String getSettleAmount() {
		return settleAmount;
	}

	/**
	 * @param settleAmount the settleAmount to set
	 */
	public void setSettleAmount(String settleAmount) {
		this.settleAmount = settleAmount;
	}

	/**
	 * @return the onlinePaymentId
	 */
	public String getOnlinePaymentId() {
		return onlinePaymentId;
	}

	/**
	 * @param onlinePaymentId the onlinePaymentId to set
	 */
	public void setOnlinePaymentId(String onlinePaymentId) {
		this.onlinePaymentId = onlinePaymentId;
	}
	
}
