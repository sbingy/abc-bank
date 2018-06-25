package com.abc.accounts;

import java.util.List;

import com.abc.Transaction;

public class CheckingAccount extends Account implements Accountable{
	protected List<Transaction> transactions;
	
	
    public CheckingAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double amount = sumTransactions();

        return amount * 0.001;
	}


}
