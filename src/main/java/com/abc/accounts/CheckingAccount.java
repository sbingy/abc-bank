package com.abc.accounts;

import java.util.List;

import com.abc.Transaction;

public class CheckingAccount extends Account implements Accountable{
	
	
    public CheckingAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double amount = sumTransactions();

        return amount * 0.001;
	}


}
