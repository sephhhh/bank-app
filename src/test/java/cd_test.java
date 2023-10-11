import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class cd_test {

	public static final double BALANCE = 2005.54;
	public static final double APR = 0.3;

	@Test
	void deposit_money() {
		CD cd = new CD(0.3, BALANCE, "00000000");
		cd.depositMoney(100.0);
		assertEquals(2105.54, cd.getBalance());
	}

	@Test
	void withdraw_money() {
		CD cd = new CD(0.3, BALANCE, "00000000");
		cd.withdrawMoney(100.0);
		assertEquals(1905.54, cd.getBalance());
	}

	@Test
	void deposit_money_twice() {
		CD cd = new CD(0.3, BALANCE, "00000000");
		cd.depositMoney(100.0);
		cd.depositMoney(100.0);
		assertEquals(2205.54, cd.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		CD cd = new CD(0.3, BALANCE, "00000000");
		cd.withdrawMoney(100.0);
		cd.withdrawMoney(100.0);
		assertEquals(1805.54, cd.getBalance());
	}
}
