package com.wn.loanapp.dto;

public class BankStatementDTO {
	
	private String settleDate;
	
	private String settleAmount;
	
	private String onlinePaymentId;
	
	private String repayDate;
	
	private String repayAmount;

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

	/**
	 * @return the repayDate
	 */
	public String getRepayDate() {
		return repayDate;
	}

	/**
	 * @param repayDate the repayDate to set
	 */
	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	/**
	 * @return the repayAmount
	 */
	public String getRepayAmount() {
		return repayAmount;
	}

	/**
	 * @param repayAmount the repayAmount to set
	 */
	public void setRepayAmount(String repayAmount) {
		this.repayAmount = repayAmount;
	}
	
}
