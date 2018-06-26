package com.abc.accounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.abc.DateProvider;
import com.abc.Transaction;
import com.abc.utils.DailyInterestRateUtil;

 
public class SavingsAccount extends Account  implements Accountable {

	public SavingsAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		
		double interest = 0.0;
		double amount = 0.0;
		Transaction last = null;
		for (Transaction t: transactions) {
			//is there a difference in days between last transaction?
			 if(last !=null) {
				 //calculate interest earned for days and add to interest
				 if(amount <= 1000)
					 interest += DailyInterestRateUtil.calculateInterest(last.getTransactionDate(), t.getTransactionDate(), amount, 0.001);		
				 else {
					 interest += DailyInterestRateUtil.calculateInterest(last.getTransactionDate(), t.getTransactionDate(), (amount-1000), 0.002,1);		
				 }
				 amount += interest; 
			 }			
			amount += t.getAmount();   
			last = t;
		}
		//calculate today's interest
		 if(amount <= 1000) {
			 interest += DailyInterestRateUtil.calculateInterest(DateProvider.getInstance().now(),amount,0.001);
		 }else {
			 interest += DailyInterestRateUtil.calculateInterest(DateProvider.getInstance().now(),amount,0.002,1);

		 }		 

		return interest;
	}
 
 

}


 