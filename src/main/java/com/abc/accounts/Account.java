package com.abc.accounts;

import java.util.ArrayList;
import java.util.List;

import com.abc.Transaction;

public abstract class Account implements Accountable{

    private final int accountType;
    protected List<Transaction> transactions;

    public List<Transaction> getTransactions() {
		return transactions;
	}

	public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

	public void withdraw(double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    } else {
	        transactions.add(new Transaction(-amount));
	    }
	}

 	public double getBalance() {
		return sumTransactions() + interestEarned();
	}
    
    protected double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions) {
         		amount += t.getAmount();        
        }
        return amount;
    } 

    public int getAccountType() {
        return accountType;
    }



}
