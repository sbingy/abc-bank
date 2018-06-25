package com.abc.accounts;

import java.util.List;

import com.abc.Transaction;

public class MaxiSavingsAccount extends Account implements Accountable{


	protected List<Transaction> transactions;

    public MaxiSavingsAccount(int accountType) {
		super(accountType);
	}
 

	@Override
	public double interestEarned() {
		double amount = sumTransactions();

        if (amount <= 1000)
            return amount * 0.02;
        if (amount <= 2000)
            return 20 + (amount-1000) * 0.05;
        return 70 + (amount-2000) * 0.1;
	}



}
