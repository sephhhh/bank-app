package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	public static final String ID = "00000000";
	public static final double BALANCE = 2005.54;
	Bank bank;
	Checkings checkings;
	Savings savings;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		checkings = new Checkings(0.3, ID);
		savings = new Savings(0.3, ID);
	}

	@Test
	void create_checking_with_no_balance() {
		assertEquals(0, checkings.getBalance());
	}

	@Test
	void create_savings_with_no_balance() {
		assertEquals(0, savings.getBalance());
	}

	@Test
	void create_cd_with_specified_balance() {
		CertificateOfDeposit certificateOfDeposit = new CertificateOfDeposit(0.3, BALANCE, ID);
		assertEquals(2005.54, certificateOfDeposit.getBalance());
	}

	@Test
	void create_bank_account_with_apr() {
		assertEquals(0.3, savings.getApr());
	}

	@Test
	void deposit_money_by_id() {
		bank.addAccount(checkings);
		bank.depositMoneyById(ID, 100.0);
		assertEquals(100.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_by_id() {
		bank.addAccount(checkings);
		bank.withdrawMoneyById(ID, 100.0);
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	void deposit_money_twice_by_id() {
		bank.addAccount(checkings);
		bank.depositMoneyById(ID, 100.0);
		bank.depositMoneyById(ID, 100.0);
		assertEquals(200.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_twice_by_id() {
		bank.addAccount(checkings);
		bank.withdrawMoneyById(ID, 100.0);
		bank.withdrawMoneyById(ID, 100.0);
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	public void testWithdrawMoneyMutant() {
		bank.addAccount(checkings);
		bank.depositMoneyById(ID, 100.0);
		bank.addAccount(checkings);
		checkings.withdrawMoney(100.0);
		assertEquals(0.0, checkings.getBalance(), 0.001);
	}
}

