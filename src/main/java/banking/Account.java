package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	List<String> transactionHistory;
	double balance;
	double apr;
	String id;
	String accountType;
	int monthsPassed;
	int numTimesWithdrew;


	public Account(double apr, double balance, String id, String accountType) {
		this.apr = apr;
		this.balance = balance;
		this.id = id;
		this.accountType = accountType;
		this.monthsPassed = 0;
		this.numTimesWithdrew = 0;
		this.transactionHistory = new ArrayList<>();
	}


	public Double getBalance() {
		return balance;
	}

	public Double getApr() {
		return apr;
	}

	public String getId() {
		return id;
	}

	public int getNumTimesWithdrew() {
		return numTimesWithdrew;
	}

	public double calApr() {
		double interest;
		apr /= 100;
		apr /= 12;
		interest = balance * apr;
		balance += interest;
		return balance;
	}

	public void depositMoney(double amount) {
		balance += amount;
		String formattedOutput = String.format("Deposit %s %.2f", this.id, amount);
		transactionHistory.add(formattedOutput);
	}

	public void withdrawMoney(double amount) {
		if (amount >= balance) {
			balance = 0.0;
		} else {
			balance -= amount;
		}
		String formattedOutput = String.format("Withdraw %s %.2f", this.id, amount);
		transactionHistory.add(formattedOutput);
		numTimesWithdrew++;
	}

	public String getAccountType() {
		return accountType;
	}

	public void deductMoney() {
		balance -= 25;
	}

	public void changeBalance(double amount) {
		balance = amount;
	}

}
