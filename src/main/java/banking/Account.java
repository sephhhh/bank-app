package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	double balance;
	double apr;
	String id;
	String accountType;
	int monthsPassed;
	int numTimesWithdrew;
	int depositToken;
	int withdrawToken;

	public Account(double apr, double balance, String id, String accountType) {
		this.apr = apr;
		this.balance = balance;
		this.id = id;
		this.accountType = accountType;
		this.monthsPassed = 0;
		this.numTimesWithdrew = 0;
		this.depositToken = 0;
		this.withdrawToken = 0;
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
		depositToken = 0;
		return balance;
	}

	public void depositMoney(double amount) {
		List<String> depositHistory = new ArrayList<>();
		balance += amount;
		String formattedOutput = String.format("Deposit %s %.2f", this.id, amount);
		depositHistory.add(formattedOutput);
		depositToken++;
	}

	public void withdrawMoney(double amount) {
		List<String> withdrawHistory = new ArrayList<>();
		if (amount >= balance) {
			balance = 0.0;
		} else {
			balance -= amount;
		}
		String formattedOutput = String.format("Withdraw %s %.2f", this.id, amount);
		withdrawHistory.add(formattedOutput);
		numTimesWithdrew++;
		withdrawToken++;
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
