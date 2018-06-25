package com.abc.accounts;

import java.util.List;

import com.abc.Transaction;

public interface Accountable {
    public List<Transaction> getTransactions();
    public void deposit(double amount);
	public void withdraw(double amount);
	public double getBalance();
	public double interestEarned();
    public int getAccountType();
	
}
