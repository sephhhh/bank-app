public abstract class Account {
	double balance;
	double apr;
	String id;

	public Account(double apr, double balance, String id) {
		this.apr = apr;
		this.balance = balance;
		this.id = id;
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

	public void depositMoney(double amount) {
		balance += amount;
	}

	public void withdrawMoney(double amount) {
		if (amount >= balance) {
			balance = 0.0;
		} else {
			balance -= amount;
		}
	}
}
