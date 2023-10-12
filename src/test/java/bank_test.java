import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class bank_test {
	Bank bank;
	Checkings checkings;
	Savings savings;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		checkings = new Checkings(0.3, "00000000");
		savings = new Savings(0.3, "00000001");
	}

	@Test
	void bank_has_no_accounts() {
		assertTrue(bank.getAllAccounts().isEmpty());
	}

	@Test
	void add_one_account_to_bank() {
		bank.addAccount(checkings);
		assertEquals(checkings, bank.getAccountById("00000000"));
	}

	@Test
	void add_two_account_to_bank() {
		bank.addAccount(checkings);
		bank.addAccount(savings);
		assertEquals(checkings, bank.getAccountById("00000000"));
		assertEquals(savings, bank.getAccountById("00000001"));
	}

	@Test
	void retrieve_one_account() {
		bank.addAccount(checkings);
		assertEquals(checkings, bank.getAccountById("00000000"));
	}

	@Test
	void deposit_money_by_id() {
		bank.addAccount(checkings);
		bank.depositMoneyById("00000000", 100.0);
		assertEquals(100.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_by_id() {
		bank.addAccount(checkings);
		bank.withdrawMoneyById("00000000", 100.0);
		assertEquals(0.0, checkings.getBalance());
	}

	@Test
	void deposit_money_twice_by_id() {
		bank.addAccount(checkings);
		bank.depositMoneyById("00000000", 100.0);
		bank.depositMoneyById("00000000", 100.0);
		assertEquals(200.0, checkings.getBalance());
	}

	@Test
	void withdraw_money_twice_by_id() {
		bank.addAccount(checkings);
		bank.withdrawMoneyById("00000000", 100.0);
		bank.withdrawMoneyById("00000000", 100.0);
		assertEquals(0.0, checkings.getBalance());
	}

}
