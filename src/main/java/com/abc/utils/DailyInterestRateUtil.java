package com.abc.utils;

import java.util.Calendar;
import java.util.Date;
 
public class DailyInterestRateUtil {

	private static double getMaxDays(Date date) {
 		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		
	}
	
	public static double calculateInterest(Date to, double sum, double annualInterest) {
		if(annualInterest <= 0) {
			throw new IllegalArgumentException("annual interest has to be greater than zero");
		}
		return annualInterest * sum / getMaxDays(to);
	}
	
	public static double calculateInterest(Date to, double sum, double annualInterest, double offset) {
		if(annualInterest <= 0) {
			throw new IllegalArgumentException("annual interest has to be greater than zero");
		}
 		return (offset + annualInterest * sum) / getMaxDays(to);
	}
	
	public static double calculateInterest(Date from, Date to, double sum, double annualInterest) {
		double total = 0.0;
		if(annualInterest <= 0) {
			throw new IllegalArgumentException("annual interest has to be greater than zero");
		}
 		long dateDiff = getNumberOfDaysDiff(from,to);
		for(long i = 0; i < dateDiff; i++) {
			total += (annualInterest * sum / getMaxDays(to));
		}
		return total;
		
	}
	
	public static double calculateInterest(Date from, Date to, double sum, double annualInterest, double offset) {
		double total = 0.0;
		if(annualInterest <= 0) {
			throw new IllegalArgumentException("annual interest has to be greater than zero");
		}
 		long dateDiff = getNumberOfDaysDiff(from,to);
		for(long i = 0; i < dateDiff; i++) {
			total += ((offset  + annualInterest * sum) / getMaxDays(to));
		}
		return total;
		
	}
	
	public  static long getNumberOfDaysDiff(Date from, Date to) {
		long dateDiff = Math.abs(to.getTime() - from.getTime());
		return dateDiff / (1000*60*60*24);	
	}
}
