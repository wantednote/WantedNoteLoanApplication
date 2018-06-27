package com.wn.loanapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import com.wn.loanapp.enums.AccountStatusEnum;

public class AppTest {

	public static void main(String[] args) throws ParseException {
		String str = " June 25, 2018".trim();
		System.out.println("Str " + str);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		Date d = sdf.parse(str);
		sdf.applyPattern("yyyy-MM-dd");
		String output = sdf.format(d);
		System.out.println("output " + output);
		
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy_MM_dd,HH_mm_ss");
	    Date now = new Date();
	    String strTime = sdfTime.format(now);
	    System.out.println("strTime " + strTime);
	}
}
