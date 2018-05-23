package com.wn.loanapp.util;

import java.util.Random;
import java.util.UUID;

public class StringGeneratorUtil {

	/**
	 * ALPHA_CAPS variable.
	 */
	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * ALPHA variable.
	 */
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	/**
	 * NUM variable.
	 */
	private static final String NUM = "0123456789";
	/**
	 * SPL_CHARS variable.
	 */
	private static final String SPL_CHARS = "!@#%^&*_=+-/";

	/**
	 * This method will generate random alpha-numeric String and containing special characters.
	 * @param minLen as int
	 * @param maxLen as int
	 * @param noOfCapsAlphabets as int
	 * @param noOfDigits as int
	 * @param noOfSpecialChars as int
	 * @return String
	 */
	public static String generateString(int minLen, int maxLen, int noOfCapsAlphabets,
			int noOfDigits, int noOfSpecialChars) {
		if (minLen > maxLen) {
			throw new IllegalArgumentException("Min. Length > Max. Length!");
		}
		if ((noOfCapsAlphabets + noOfDigits + noOfSpecialChars) > minLen) {
			throw new IllegalArgumentException(
					"Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
		}
		Random random = new Random();
		int len = random.nextInt(maxLen - minLen + 1) + minLen;
		char[] password = new char[len];
		int index = 0;
		for (int i = 0; i < noOfCapsAlphabets; i++) {
			index = getNextIndex(random, len, password);
			password[index] = ALPHA_CAPS.charAt(random.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(random, len, password);
			password[index] = NUM.charAt(random.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSpecialChars; i++) {
			index = getNextIndex(random, len, password);
			password[index] = SPL_CHARS.charAt(random.nextInt(SPL_CHARS.length()));
		}
		for (int i = 0; i < len; i++) {
			if (password[i] == 0) {
				password[i] = ALPHA.charAt(random.nextInt(ALPHA.length()));
			}
		}
		return (new String(password));
	}

	/**
	 * @param rnd as Random
	 * @param len as int
	 * @param password as char[]
	 * @return int
	 */
	private static int getNextIndex(Random rnd, int len, char[] password) {
		int index = rnd.nextInt(len);
		while (password[index = rnd.nextInt(len)] != 0);
		return index;
	}

	/**
	 * This method will generate next unique id
	 * @return String
	 */
	public static String generateUniqueId() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replaceAll("-", "").toUpperCase();
		return uuidStr;
	}
	/**@author Satyajit
	 * This will generate a uniquetoken based on  mobile no.
	 * @param mobile
	 * @return
	 */
	public static String generateToken(String mobile){
		String uuidStr = UUID.randomUUID().toString().replaceAll("-", "").substring(12).toUpperCase();
		if(Format.isStringNotEmptyAndNotNull(mobile)){
			uuidStr = uuidStr.concat(mobile.substring(4));
		}
		return uuidStr;
	}
	
	/**@author Satyajit
	 * This method will generate a Password ("VB" + Name(4 letter max) + Phone(4 letter max) + UniqueString)
	 * @param form
	 * @return
	 */
	public static String generatePassword(String name , String phone ){
		StringBuffer password = new StringBuffer("VB");
		if(null != name && name.length() > 4){
			password.append(name.substring(0,4));
		}else if(null != name){
			password.append(name);
		}
		if(null != phone && phone.length() >4){
			password.append(phone.substring(0, 4));
		}else if(null != phone){
			password.append(phone);
		}
		//password.append(System.currentTimeMillis());
		return password.toString().toUpperCase();
	}
	
	/**@author Satyajit
	 * This will generate UN from emailAddr
	 * @param email
	 * @return
	 */
	public static String generateUserName(String email) {
		String userName = null;
		try {
			if(Format.isStringNotEmptyAndNotNull(email)){
				userName = email.substring(0, email.indexOf('@'));
			}
		} catch (Exception e) {
			
		}
		return userName;
	}

}
