package com.wn.loanapp.util;

import java.time.LocalDate;
import java.time.Period;

import com.wn.loanapp.enums.AccountStatusEnum;

public class AppTest {

	public static void main(String[] args) {
		System.out.println("Val " + AccountStatusEnum.Active.getValue());
		String SELECTED_BASE_LINK_ADMIN_DASHBOARD= "<a href="+"admin"+">Admin</a>";
		System.out.println(SELECTED_BASE_LINK_ADMIN_DASHBOARD);
	
        LocalDate pdate = LocalDate.of(2011, 05, 24);
        LocalDate now = LocalDate.now();
 
        Period diff = Period.between(pdate, now);
	 
	     System.out.printf("\nDifference is %d years, %d months and %d days old\n\n", 
	                    diff.getYears(), diff.getMonths(), diff.getDays());
	}
}
