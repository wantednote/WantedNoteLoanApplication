package com.wn.loanapp.util;

import com.wn.loanapp.constants.Constants;

public class ActorRoleFormater {

	public static String getFormatedActorRole(String role) {
		String roleName = null;
		switch (role) {
		case Constants.ADMIN:
			roleName = "Administrator";
			break;
		case Constants.PARTNER:
			roleName = "Business Partner";
			break;
		default:
			roleName = "Guest User";
			break;
		}
		return roleName;
	}
}
