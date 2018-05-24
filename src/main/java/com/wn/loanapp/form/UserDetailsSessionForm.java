package com.wn.loanapp.form;

public class UserDetailsSessionForm {

	private String emailAddress;
	
	private String name;
	
	private String userRole;
	
	private String userRoleName;
	
	private String pageHeaderTitle;
	
	private String selectedBaseLink;
	
	private String selectedSubLink;
	
	private String loginToken;

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * @return the userRoleName
	 */
	public String getUserRoleName() {
		return userRoleName;
	}

	/**
	 * @param userRoleName the userRoleName to set
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	
	/**
	 * @return the pageHeaderTitle
	 */
	public String getPageHeaderTitle() {
		return pageHeaderTitle;
	}

	/**
	 * @param pageHeaderTitle the pageHeaderTitle to set
	 */
	public void setPageHeaderTitle(String pageHeaderTitle) {
		this.pageHeaderTitle = pageHeaderTitle;
	}

	/**
	 * @return the selectedBaseLink
	 */
	public String getSelectedBaseLink() {
		return selectedBaseLink;
	}

	/**
	 * @param selectedBaseLink the selectedBaseLink to set
	 */
	public void setSelectedBaseLink(String selectedBaseLink) {
		this.selectedBaseLink = selectedBaseLink;
	}

	/**
	 * @return the selectedSubLink
	 */
	public String getSelectedSubLink() {
		return selectedSubLink;
	}

	/**
	 * @param selectedSubLink the selectedSubLink to set
	 */
	public void setSelectedSubLink(String selectedSubLink) {
		this.selectedSubLink = selectedSubLink;
	}

	/**
	 * @return the loginToken
	 */
	public String getLoginToken() {
		return loginToken;
	}

	/**
	 * @param loginToken the loginToken to set
	 */
	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}
	
}
