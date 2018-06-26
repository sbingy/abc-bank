package com.abc.accounts;

import java.util.Date;
 
import com.abc.DateProvider;
import com.abc.Transaction;
import com.abc.utils.DailyInterestRateUtil;

public class MaxiSavingsAccount extends Account implements Accountable{



    public MaxiSavingsAccount(int accountType) {
		super(accountType);
	}
 

	@Override
	public double interestEarned() {
		//Change Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%
		double interest = 0.0;
		double amount = 0.0;
		Transaction last = null;
		Date lastWithdrawal =null;
		//calculate previous interest
		for (Transaction t: transactions) {
			//is there a difference in days between last transaction?
			 if(last != null) {
				 //calculate interest earned for days and add to interest
				 if(lastWithdrawal !=null && DailyInterestRateUtil.getNumberOfDaysDiff(lastWithdrawal, t.getTransactionDate()) >= 10){
					 interest += DailyInterestRateUtil.calculateInterest(last.getTransactionDate(), t.getTransactionDate(), amount, 0.05);	
				 }
				 else {
					 interest += DailyInterestRateUtil.calculateInterest(last.getTransactionDate(), t.getTransactionDate(), amount, 0.001); 
				 }
				 amount += interest;
			 }			
			amount += t.getAmount();   
			last = t;
			if(lastWithdrawal == null) {
				//set first transaction as initial date
				lastWithdrawal = t.getTransactionDate();
			}
			if(t.getAmount() < 0) {
				lastWithdrawal = t.getTransactionDate();
			}
		}
		//calculate today's interest
		 if(lastWithdrawal !=null && DailyInterestRateUtil.getNumberOfDaysDiff(lastWithdrawal,DateProvider.getInstance().now() ) >= 10){
				interest += DailyInterestRateUtil.calculateInterest(DateProvider.getInstance().now(),amount,0.05);
		 }else {
				interest += DailyInterestRateUtil.calculateInterest(DateProvider.getInstance().now(),amount,0.001);
		 }
		
		return interest;
	}
}
