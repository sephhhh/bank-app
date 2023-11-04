import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {
	Bank bank;
	Checkings checking;
	Savings savings;
	CertificateOfDeposit cd;
	DepositCommandValidator depositCommandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		depositCommandValidator = new DepositCommandValidator(bank);
		checking = new Checkings(0.3, "12345678");
		savings = new Savings(0.3, "12345678");
		cd = new CertificateOfDeposit(0.3, 1000.0, "12345678");
	}

	@Test
	void DepositingWithTwoArgumentsAsBalanceInChecking() {
		bank.addAccount(checking);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

	@Test
	void DepositingWithTwoArgumentsAsBalanceInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

	@Test
	void DepositBalanceOverOneThousandInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 3000.0");
		assertFalse(actual);
	}

	@Test
	void DepositBalanceUnderZeroInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 -1.0");
		assertFalse(actual);
	}

	@Test
	void DepositWithBalanceAsLettersInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 abc");
		assertFalse(actual);
	}

	@Test
	void DepositWithoutBalanceInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678");
		assertFalse(actual);
	}

	@Test
	void DepositBalanceAsLetterAndIntsInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 $50.0");
		assertFalse(actual);
	}

	@Test
	void DepositWithoutIdInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 50.0");
		assertFalse(actual);
	}

	@Test
	void DepositWithoutFunctionInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("12345678 50.0");
		assertFalse(actual);
	}

	@Test
	void DepositWithoutArgumentsInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit");
		assertFalse(actual);
	}

	@Test
	void DepositOver2500AsBalanceInChecking() {
		bank.addAccount(checking);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 3000.0");
		assertFalse(actual);
	}

}
