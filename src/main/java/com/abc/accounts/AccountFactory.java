package com.abc.accounts;

public class AccountFactory {
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    //public static final int SUPER_SAVINGS = 3;
    public static Accountable getAccountType(int accountType) {
    	switch(accountType) {
    		case AccountFactory.SAVINGS:
    			return new SavingsAccount(accountType);
//    		case AccountFactory.SUPER_SAVINGS:
//    			return new SuperSavings();
    		case AccountFactory.CHECKING:
    			return new CheckingAccount(accountType);
    		case AccountFactory.MAXI_SAVINGS:
    			return new MaxiSavingsAccount(accountType);    		
    		default:
    			throw new IllegalArgumentException("Invalid Account Type");
     
    	}
    }
}
