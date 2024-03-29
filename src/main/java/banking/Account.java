package banking;

import java.text.DecimalFormat;
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
	double interest;


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
		apr /= 12;
		apr /= 100;
		interest = balance * apr;
		balance += interest;
		numTimesWithdrew = 0;
		return Double.parseDouble(formatBalance(balance));
	}

	private static String formatBalance(double balance) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(balance);
	}

	public void depositMoney(double amount) {
		balance += amount;
		String formattedOutput = String.format("Deposit %s %d", this.id, (int) amount);
		transactionHistory.add(formattedOutput);
	}

	public void depositMoneyTransfer(double amount) {
		balance += amount;
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

	public void setMonthsPassed(int amount) {
		monthsPassed = amount;
	}

	public int getMonthsPassed() {
		return monthsPassed;
	}

}
