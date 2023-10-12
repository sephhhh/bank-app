import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class cd_test {

	public static final double BALANCE = 2005.54;
	public static final double APR = 0.3;
	CD cd;

	@BeforeEach
	void setUp() {
		cd = new CD(0.3, BALANCE, "00000000");
	}

	@Test
	void create_account_with_specified_balance() {
		assertEquals(BALANCE, cd.getBalance());
	}

	@Test
	void deposit_money() {
		cd.depositMoney(100.0);
		assertEquals(2105.54, cd.getBalance());
	}

	@Test
	void withdraw_money() {
		cd.withdrawMoney(100.0);
		assertEquals(1905.54, cd.getBalance());
	}

	@Test
	void deposit_money_twice() {
		cd.depositMoney(100.0);
		cd.depositMoney(100.0);
		assertEquals(2205.54, cd.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		cd.withdrawMoney(100.0);
		cd.withdrawMoney(100.0);
		assertEquals(1805.54, cd.getBalance());
	}
}
