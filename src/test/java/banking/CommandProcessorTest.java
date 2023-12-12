package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	Bank bank;
	CommandProcessor commandProcessor;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void createChecking() {
		commandProcessor.create("create checking 12345678 1.0");
		assertEquals("12345678", bank.accountExistsById("12345678"));
		assertEquals(1.0, bank.getAccountById("12345678").getApr());
	}

	@Test
	void createSavings() {
		commandProcessor.create("create savings 12345678 1.0");
		assertEquals("12345678", bank.accountExistsById("12345678"));
		assertEquals(1.0, bank.getAccountById("12345678").getApr());
	}

	@Test
	void createCd() {
		commandProcessor.create("create cd 12345678 1.0 1000");
		assertEquals("12345678", bank.accountExistsById("12345678"));
		assertEquals(1.0, bank.getAccountById("12345678").getApr());
		assertEquals(1000.0, bank.getAccountById("12345678").getBalance());
	}

	@Test
	void depositMoney() {
		commandProcessor.create("create savings 12345678 1.0");
		commandProcessor.deposit("Deposit 12345678 500");
		assertEquals(500, bank.getAccountById("12345678").getBalance());
	}


	@Test
	void deposit_into_account() {
		Savings savings = new Savings(0.6,"12345678");
		bank.addAccount(savings);
		commandProcessor.deposit("Deposit 12345678 500.0");

		assertEquals(500.0, savings.getBalance());
	}

	@Test
	void withdraw_from_account() {
		Checkings checking = new Checkings(0.6, "87654321");
		checking.depositMoney(1000.0);
		bank.addAccount(checking);

		commandProcessor.withdraw("Withdraw 87654321 300.0");

		assertEquals(700.0, checking.getBalance());
	}

	@Test
	void pass_time() {
		Savings savings = new Savings(0.6, "12345678");
		savings.depositMoney(1000.0);
		bank.addAccount(savings);

		commandProcessor.passTime("Pass 1");

		assertEquals(1000.5, savings.getBalance());
	}

	@Test
	void transfer_between_accounts() {
		Savings account1 = new Savings(0.6, "12345678");
		account1.depositMoney(1000.0);
		bank.addAccount(account1);

		Checkings account2 = new Checkings(0.6,"87654321");
		bank.addAccount(account2);

		commandProcessor.transfer("Transfer 12345678 87654321 500.0");

		assertEquals(500.0, account1.getBalance());
		assertEquals(500.0, account2.getBalance());
	}

	@Test
	void passTime_no_accounts() {
		commandProcessor.passTime("Pass 5");
	}

	@Test
	void passTime_close_zero_balance_accounts() {
		Savings zeroBalanceSavings = new Savings(0.6, "12345678");
		zeroBalanceSavings.depositMoney(0.0);
		bank.addAccount(zeroBalanceSavings);

		commandProcessor.passTime("Pass 5");

		assertEquals(0, bank.getAllAccounts().size());
	}

	@Test
	void passTime_deduct_money_and_close_accounts() {
		Savings lowBalanceSavings = new Savings(0.6, "12345678");
		lowBalanceSavings.depositMoney(50.0);
		lowBalanceSavings.withdrawMoney(50.0);
		bank.addAccount(lowBalanceSavings);

		commandProcessor.passTime("Pass 5");

		assertEquals(0, bank.getAllAccounts().size());
	}

	@Test
	void passTime_cd_accounts_calculate_apr() {
		CertificateOfDeposit cdAccount = new CertificateOfDeposit(0.6, 1000, "98765432");
		bank.addAccount(cdAccount);

		commandProcessor.passTime("Pass 4");
		assertEquals(1000.50, cdAccount.getBalance());
	}

	@Test
	void passTime_if_balance_is_exactly_less_than_100() {
		Savings savings = new Savings(0.6, "12345678");
		bank.addAccount(savings);
		savings.depositMoney(100);
		commandProcessor.passTime("Pass 1");

		assertEquals(100.05, savings.getBalance());
	}

}
