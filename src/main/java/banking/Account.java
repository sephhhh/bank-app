package banking;

public abstract class Account {
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
		numTimesWithdrew = 0;
		return balance;
	}

	public void depositMoney(double amount) {
		balance += amount;
	}

	public void withdrawMoney(double amount) {
		if (amount >= balance) {
			balance = 0.0;
		} else {
			balance -= amount;
		}
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
