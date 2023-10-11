import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class savings_test {

	public static final double APR = 0.3;
	// void deposit_money_twice

	@Test
	void deposit_money() {
		Savings savings = new Savings(0.3, "00000000");
		savings.depositMoney(100.0);
		assertEquals(100.0, savings.getBalance());
	}

	@Test
	void withdraw_money() {
		Savings savings = new Savings(0.3, "00000000");
		savings.withdrawMoney(100.0);
		assertEquals(0.0, savings.getBalance());
	}

	@Test
	void deposit_money_twice() {
		Savings savings = new Savings(0.3, "00000000");
		savings.depositMoney(100.0);
		savings.depositMoney(100.0);
		assertEquals(200.0, savings.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		Savings savings = new Savings(0.3, "00000000");
		savings.withdrawMoney(100.0);
		savings.withdrawMoney(100.0);
		assertEquals(0.0, savings.getBalance());
	}

}
