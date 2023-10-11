import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class checkings_test {

	public static final double APR = 0.3;

	@Test
	void deposit_money() {
		Checkings checkings = new Checkings(0.3, "00000000");
		checkings.depositMoney(100);
		assertEquals(100.0, checkings.getBalance());
	}

	@Test
	void withdraw_money() {
		Checkings checkings = new Checkings(0.3, "00000000");
		checkings.withdrawMoney(100.0);
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	void deposit_money_twice() {
		Checkings checkings = new Checkings(0.3, "00000000");
		checkings.depositMoney(100);
		checkings.depositMoney(100);
		assertEquals(200.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		Checkings checkings = new Checkings(0.3, "00000000");
		checkings.withdrawMoney(100.0);
		checkings.withdrawMoney(100.0);
		assertEquals(0.0, checkings.getBalance());
	}

}
