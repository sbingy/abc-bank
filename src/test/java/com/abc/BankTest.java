package com.abc;

import org.junit.Test;

import com.abc.accounts.AccountFactory;
import com.abc.accounts.Accountable;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(AccountFactory.getAccountType(AccountFactory.CHECKING));
        bank.addCustomer(john);
        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);
        assertEquals(0.1/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(1500.0);
        assertEquals(2.0/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(3000.0);       
        assertEquals(3.0/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }
    
    @Test
    public void transfer() {
    	Bank bank = new Bank();
    	Customer alice = new Customer("Alice");
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.CHECKING);
        Accountable savingsAccount = AccountFactory.getAccountType(AccountFactory.SAVINGS);
    	alice.openAccount(checkingAccount);
    	alice.openAccount(savingsAccount);
     	bank.addCustomer(alice);
    	
     	checkingAccount.deposit(2000.00);
     	savingsAccount.deposit(500);
     	
     	alice.transfer(checkingAccount, savingsAccount, 1000);
     	assertEquals(1000,checkingAccount.sumTransactions(),DOUBLE_DELTA);
     	assertEquals(1500,savingsAccount.sumTransactions(),DOUBLE_DELTA);
    	
    	
    }

}
