package com.abc.utils;

import java.util.List;
import java.util.Calendar;
import java.util.Date;
import com.abc.Transaction;

public class DailyInterestRateUtil {

	private static double getMaxDays(Date date) {
 		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		
	}
	
	public static double calculateInterest(Date from, Date to, double sum, double annualInterest) {
		double total = 0.0;
		if(annualInterest <= 0) {
			throw new IllegalArgumentException("annual interest has to be greater than zero");
		}
		double dailyInterest = annualInterest / getMaxDays(to);
		long dateDiff = getNumberOfDaysDiff(from,to);
		for(long i = 0; i < dateDiff; i++) {
			total += (dailyInterest * sum);
		}
		return total;
		
	}
	

	
	public  static long getNumberOfDaysDiff(Date from, Date to) {
		long dateDiff = Math.abs(to.getTime() - from.getTime());
		return dateDiff / (1000*60*60*24);	
	}
}
