package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.accounts.AccountFactory;
import com.abc.accounts.Accountable;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.CHECKING);
        Accountable savingsAccount = AccountFactory.getAccountType(AccountFactory.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
     }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(AccountFactory.getAccountType(AccountFactory.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountFactory.getAccountType(AccountFactory.SAVINGS));
        oscar.openAccount(AccountFactory.getAccountType(AccountFactory.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(AccountFactory.getAccountType(AccountFactory.SAVINGS));
        oscar.openAccount(AccountFactory.getAccountType(AccountFactory.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
