package com.wn.loanapp.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateDifference {

	public static String getDaysDiff(Date date){
        String dateString = null;
		//LocalDate pdate = LocalDate.of(2018, 05, 23);
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
		LocalDate pdate = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
 
        Period diff = Period.between(pdate, now);
	 
	    //System.out.printf("\nDifference is %d years, %d months and %d days old\n\n", 
	    //                diff.getYears(), diff.getMonths(), diff.getDays());
	    
	    if(diff.getYears() < 1 && diff.getMonths() < 1 && diff.getDays() >= 0) {
	    	if(diff.getDays() == 0) {
	    		dateString = "Today";
	    	}else if(diff.getDays() == 1) {
	    		dateString = "Yesterday";
	    	}else {
	    		dateString = diff.getDays() + " Days ago";
	    	}
	    }else if(diff.getYears() < 1 && diff.getMonths() > 0) {
	    	if(diff.getMonths() == 0) {
	    		dateString = "This Month";
	    	}else if(diff.getMonths() == 1) {
	    		dateString = "Last Month";
	    	}else {
	    		dateString = diff.getMonths() + " Months ago";
	    	}
	    }else if(diff.getYears() > 0) {
	    	if(diff.getYears() == 0) {
	    		dateString = "This Yesr";
	    	}else if(diff.getYears() == 1) {
	    		dateString = "Last Year";
	    	}else {
	    		dateString = diff.getYears() + " Years ago";
	    	}
	    }
	    return dateString;
    }
	public static void main(String[] args){
		String date = getDaysDiff(new Date());
		System.out.println(date);
	}
}
