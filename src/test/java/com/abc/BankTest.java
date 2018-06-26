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
        //test based off of daily interest
        assertEquals(0.1/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(1500.0);
        //500
        assertEquals(1.0/365, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(3000.0);
        //should be .1%
        assertEquals(.1/365, bank.totalInterestPaid(), DOUBLE_DELTA);
        
    }

}
