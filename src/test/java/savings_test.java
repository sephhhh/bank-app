import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class savings_test {

	public static final double APR = 0.3;
	// void deposit_money_twice
	Savings savings;

	@BeforeEach
	void setUp() {
		savings = new Savings(0.3, "00000000");
	}

	@Test
	void check_initial_balance() {
		assertEquals(0.0, savings.getBalance());
	}

	@Test
	void deposit_money() {
		savings.depositMoney(100.0);
		assertEquals(100.0, savings.getBalance());
	}

	@Test
	void withdraw_money() {
		savings.withdrawMoney(100.0);
		assertEquals(0.0, savings.getBalance());
	}

	@Test
	void deposit_money_twice() {
		savings.depositMoney(100.0);
		savings.depositMoney(100.0);
		assertEquals(200.0, savings.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		savings.withdrawMoney(100.0);
		savings.withdrawMoney(100.0);
		assertEquals(0.0, savings.getBalance());
	}

}
