package com.abc.accounts;


import com.abc.DateProvider;
import com.abc.Transaction;
import com.abc.utils.DailyInterestRateUtil;

public class CheckingAccount extends Account implements Accountable{
	
	
    public CheckingAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double interest = 0.0;
		double amount = 0.0;
		Transaction last = null;
		for (Transaction t: transactions) {
			//is there a difference in days between last transaction
			 if(last !=null) {
				 //calculate interest earned for days and add to interest
				 interest += DailyInterestRateUtil.calculateInterest(last.getTransactionDate(), t.getTransactionDate(), amount, 0.001);			
				 amount += interest;
			 }			
			amount += t.getAmount();   
			last = t;
		}
		interest += DailyInterestRateUtil.calculateInterest(DateProvider.getInstance().now(),amount,0.001);
		return interest;
	}


}
