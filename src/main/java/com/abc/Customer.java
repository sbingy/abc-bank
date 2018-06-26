package com.abc;

import java.util.ArrayList;
import java.util.List;

 
import com.abc.accounts.AccountFactory;
import com.abc.accounts.Accountable;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Accountable> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Accountable>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Accountable account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Accountable a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Accountable a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }
    
    //Implement account transfer
    public void transfer(Accountable from, Accountable to, double amount) {
	    if (amount <= 0) {
	        throw new IllegalArgumentException("amount must be greater than zero");
	    }
	    else {
    		from.withdraw(amount);
    		to.deposit(amount);
    	}
    }

    private String statementForAccount(Accountable a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case AccountFactory.CHECKING:
                s += "Checking Account\n";
                break;
            case AccountFactory.SAVINGS:
                s += "Savings Account\n";
                break;
            case AccountFactory.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    public static void main(String ...strings ) {

        Accountable checkingAccount = AccountFactory.getAccountType(AccountFactory.CHECKING);
        Accountable savingsAccount = AccountFactory.getAccountType(AccountFactory.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        System.out.println(henry.getStatement());
    }
    
    
}
