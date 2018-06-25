package com.abc.accounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.abc.Transaction;
import com.abc.utils.DailyInterestRateUtil;

 
public class SavingsAccount extends Account  implements Accountable {

	public SavingsAccount(int accountType) {
		super(accountType);
	}

	public double interestEarned() {
		double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount-1000) * 0.002;
	}

}


/*

public double interestEarned() {
double amount = sumTransactions();
switch(accountType){
 
    case MAXI_SAVINGS:

    default:

}
}

*/