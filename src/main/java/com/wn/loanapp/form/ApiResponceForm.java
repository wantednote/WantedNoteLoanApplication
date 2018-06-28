package com.wn.loanapp.form;

public class ApiResponceForm {

	private String messageType;
	
	private String successOrErrorMessage;

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the successOrErrorMessage
	 */
	public String getSuccessOrErrorMessage() {
		return successOrErrorMessage;
	}

	/**
	 * @param successOrErrorMessage the successOrErrorMessage to set
	 */
	public void setSuccessOrErrorMessage(String successOrErrorMessage) {
		this.successOrErrorMessage = successOrErrorMessage;
	}
	
	
}
