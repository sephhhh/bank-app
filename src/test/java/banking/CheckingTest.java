package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {

	public static final double APR = 0.3;
	Checkings checkings;

	@BeforeEach
	void setUp() {
		checkings = new Checkings(0.3, "00000000");
	}

	@Test
	void check_initial_balance() {
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	void deposit_money() {
		checkings.depositMoney(100);
		assertEquals(100.0, checkings.getBalance());
	}

	@Test
	void withdraw_money() {
		checkings.withdrawMoney(100.0);
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	void deposit_money_twice() {
		checkings.depositMoney(100);
		checkings.depositMoney(100);
		assertEquals(200.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		checkings.withdrawMoney(100.0);
		checkings.withdrawMoney(100.0);
		assertEquals(0.0, checkings.getBalance());
	}

}
