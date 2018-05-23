package com.wn.loanapp.util;

import com.wn.loanapp.enums.AccountStatusEnum;

public class AppTest {

	public static void main(String[] args) {
		System.out.println("Val " + AccountStatusEnum.Active.getValue());
		String SELECTED_BASE_LINK_ADMIN_DASHBOARD= "<a href="+"admin"+">Admin</a>";
		System.out.println(SELECTED_BASE_LINK_ADMIN_DASHBOARD);
	}

}
