package com.wn.loanapp.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author mithun.mondal
 * This class will provide utility methods to check if String, List are empty or not.
 */
public class Format {

	/**
	 * Method to check if String is not empty and not null.
	 * @param arg
	 * @return Boolean
	 */
	public static Boolean isStringNotEmptyAndNotNull(String arg) {
		if (arg != null && !("").equals(arg) && !("").equals(arg.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if Double is not empty and not zero.
	 * @param arg
	 * @return Boolean
	 */
	public static Boolean isDoubleNotEmptyAndNotZero(Double arg) {
		if (arg != null && arg != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method to check if a List/Set is not null and not empty.
	 * @param c
	 * @return Boolean
	 */
	public static Boolean isCollectionNotEmtyAndNotNull(Collection<?> arg) {
		if (arg != null && !arg.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a long is not 0 and not empty.
	 * @param c
	 * @return Boolean
	 */
	public static Boolean isLongNotEmtyAndNotZero(long arg) {
		if (arg != 0 && !("").equals(arg)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a Date is not 0 and not empty.
	 * @param c
	 * @return
	 */
	public static Boolean isDateNotEmtyAndNotNull(Date arg) {
		if (arg != null && !("").equals(arg)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if String is empty or null.
	 * @param arg
	 * @return
	 */
	public static Boolean isStringEmptyOrNull(String arg) {
		if (("").equals(arg) || arg == null || ("").equals(arg.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a List/Set is null or empty.
	 * @param Collection
	 * @return true if list is null or empty
	 */
	public static Boolean isCollectionEmtyOrNull(Collection<?> arg) {
		if (arg == null || arg.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a long is not 0 and not empty.
	 * @param Long arg
	 * @return Boolean
	 */
	public static Boolean isLongNotEmtyAndNotZero(Long arg) {
		if (arg != null && arg != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a Integer is not 0 and not empty.
	 * @param Integer arg
	 * @return Boolean
	 */
	public static Boolean isIntegerNotEmtyAndNotZero(Integer arg) {
		if (arg != null && arg != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a String[] is null or empty.
	 * @param String[]
	 * @return true if String[] is null or empty
	 */
	public static Boolean isStringArrayEmptyOrNull(String[] arg) {
		if (arg == null || arg.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if a String[] is null or empty.
	 * @param String[]
	 * @return true if String[] is null or empty
	 */
	public static Boolean isStringArrayNotEmptyAndNotNull(String[] arg) {
		if (arg == null || arg.length == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Method to check if an object is not null or zero.
	 * @param arg as Object
	 * @return true if object is not null or zero
	 */
	public static Boolean isObjectNotEmptyAndNotZero(Object arg) {
		if (arg != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to format date to string  in ddMMMyyyy.
	 * @param date as Date
	 * @return string date formatted
	 */
	public static String parseDateToStringDDMMMYYYY(Date date) {
		SimpleDateFormat format = null;
		String dateStr = null;
		format = new SimpleDateFormat("ddMMMyyyy");
		dateStr = format.format(date);
		return dateStr;
	}

	/**@author mithun.mondal
	 * Method to format date to string  in dd-MMM-yyyy.
	 * @param date as Date
	 * @return string date formatted
	 */
	public static String parseDateToStringDD_MMM_YYYY(Date date) {
		SimpleDateFormat format = null;
		String dateStr = null;
		format = new SimpleDateFormat("dd-MMM-yyyy");
		dateStr = format.format(date);
		return dateStr;
	}
	
	public static String parseDateToStringYYYY_MM_DD(Date date) {
		SimpleDateFormat format = null;
		String dateStr = null;
		format = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = format.format(date);
		return dateStr;
	}
	/**
	 * Method to format date in dd MMM yyyy hh:mm a.
	 * @param dateStr as date
	 * @return formatted String
	 */
	public static String parseDateFormat(Date date) {
		SimpleDateFormat format = null;
		String datestr = null;
		try {
			format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
			datestr = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datestr;
	}
	
	/**
	 * Method to format date in MMM-yyyy.
	 * @param dateStr as date
	 * @return formatted String
	 */
	public static String parseMMMDateFormat(Date date) {
		SimpleDateFormat format = null;
		String datestr = null;
		try {
			format = new SimpleDateFormat("MMM-yyyy");
			datestr = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datestr;
	}

	/**
	 * Method to format date in yyyy-MM-dd hh:mm a.
	 * @param dateStr as String
	 * @return formatted date
	 */
	public static Date parseDateYYYYMMDDFormat(String dateStr) {
		SimpleDateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	
	
	/**
	 * Method to format date in dd-MM-yyyy.
	 * @param newDateStr as String
	 * @return formatted date
	 * @throws ParseException 
	 */
	public static Date parseDateDDMMYYYYFormat(String newDateStr) throws ParseException {
		SimpleDateFormat format = null;
		Date date = null;
			format = new SimpleDateFormat("dd-MM-yyyy");
			date = format.parse(newDateStr);
		return date;
	}

	/**
	 * Method to check if an object is not empty or null.
	 * @param arg as Object
	 * @return true if object is not empty or null
	 */
	public static Boolean isObjectNotEmptyAndNotNull(Object arg) {
		if (arg != null && !arg.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to check if an object is not null.
	 * @param arg as Object
	 * @return true if object is not null
	 */
	public static Boolean isNotNull(Object obj) {
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}
	/**@author rajnikant.patel
	 * Method to check if an BigDecimal object is not null.
	 * @param arg as BigDecimal object
	 * @return true if BigDecimal object is not null
	 */
	public static Boolean isNotNull(BigDecimal obj) {
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Method to check if an object is null.
	 * @param arg as Object
	 * @return true if object is null
	 */
	public static Boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to format date in dd-MM-yyyy
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static String formatDate(Date newDate) {
		SimpleDateFormat format = null;
		String date = null;
		format = new SimpleDateFormat("dd-MM-yyyy");
		date = format.format(newDate);
		return date;
	}
	
	/**
	 * Method to format date in yyyy-MM-dd
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static String formatyyyyMMddDate(Date newDate) {
		SimpleDateFormat format = null;
		String date = null;
		format = new SimpleDateFormat("yyyy-MM-dd");
		date = format.format(newDate);
		return date;
	}

	/**
	 * Method to format date in dd.
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static String getDDFormatStr(Date newDate) {
		SimpleDateFormat format = null;
		String date = null;
		format = new SimpleDateFormat("dd");
		date = format.format(newDate);
		return date;
	}

	/**
	 * Method to format date in MM.
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static String getMMFormatStr(Date newDate) {
		SimpleDateFormat format = null;
		String date = null;
		format = new SimpleDateFormat("MM");
		date = format.format(newDate);
		return date;
	}

	/**
	 * Method to format date in yyyy.
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static String getYYYYFormatStr(Date newDate) {
		SimpleDateFormat format = null;
		String date = null;
		format = new SimpleDateFormat("yyyy");
		date = format.format(newDate);
		return date;
	}
	/**
	 * Method to check if an object is null or value null.
	 * @param arg as Object
	 * @return String
	 */
	public static String isNullToSpace(Object object) {
		if (object == null || "null".equalsIgnoreCase(object.toString())) {
			return "";
		} else {
			return object.toString();
		}
	}
	
	/**
	 * Method to format date in yyyy-MM-dd
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static Date parseDateYYYYMMDD(String newDate) {
		SimpleDateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Method to format date in dd-MM-yyyy.
	 * @param newDateStr as String
	 * @return formatted date
	 */
	public static Date parseDateDD_MMM_YYYYFormat(String newDateStr) {
		SimpleDateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat("dd-MM-yyyy");
			date = format.parse(newDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * Method to format date in MMM.
	 * @param newDate as Date
	 * @return formatted String date
	 */
	public static Date getMMMFormatStr(String newDate) {
		SimpleDateFormat format = null;
		Date date = null;
		try {
			format = new SimpleDateFormat("MMM-yyyy");
			date = format.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date parseDateDDMMYYYYWithSlace(String newDateStr) throws ParseException {
		SimpleDateFormat format = null;
		Date date = null;
			format = new SimpleDateFormat("MM/dd/yyyy");
			date = format.parse(newDateStr);
		return date;
	}
	public static void main(String[] args) {
		System.out.println(formatDate(new Date()));
	}
}
