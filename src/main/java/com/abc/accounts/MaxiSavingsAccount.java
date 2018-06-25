package com.abc.accounts;

import java.util.List;

import com.abc.Transaction;

public class MaxiSavingsAccount extends Account implements Accountable{



    public MaxiSavingsAccount(int accountType) {
		super(accountType);
	}
 

	@Override
	public double interestEarned() {
		//Change Maxi-Savings accounts to have an interest rate of 5% assuming no withdrawals in the past 10 days otherwise 0.1%

		double amount = sumTransactions();

		
		
        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
	}



}
