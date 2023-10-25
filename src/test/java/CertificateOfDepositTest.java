import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CertificateOfDepositTest {

	public static final double BALANCE = 2005.54;
	public static final double APR = 0.3;
	CertificateOfDeposit certificateOfDeposit;

	@BeforeEach
	void setUp() {
		certificateOfDeposit = new CertificateOfDeposit(0.3, BALANCE, "00000000");
	}

	@Test
	void create_account_with_specified_balance() {
		assertEquals(BALANCE, certificateOfDeposit.getBalance());
	}

	@Test
	void deposit_money() {
		certificateOfDeposit.depositMoney(100.0);
		assertEquals(2105.54, certificateOfDeposit.getBalance());
	}

	@Test
	void withdraw_money() {
		certificateOfDeposit.withdrawMoney(100.0);
		assertEquals(1905.54, certificateOfDeposit.getBalance());
	}

	@Test
	void deposit_money_twice() {
		certificateOfDeposit.depositMoney(100.0);
		certificateOfDeposit.depositMoney(100.0);
		assertEquals(2205.54, certificateOfDeposit.getBalance());
	}

	@Test
	void withdraw_money_twice() {
		certificateOfDeposit.withdrawMoney(100.0);
		certificateOfDeposit.withdrawMoney(100.0);
		assertEquals(1805.54, certificateOfDeposit.getBalance());
	}
}
