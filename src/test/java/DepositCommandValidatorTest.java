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
	void DepositingWithTwoArgumentsAsAmountWithChecking() {
		bank.addAccount(checking);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

	@Test
	void DepositingWithTwoArgumentsAsAmountWithSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

	@Test
	void DepositOverOneThousandInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 3000.0");
		assertFalse(actual);
	}

	@Test
	void DepositUnderZeroInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 -1.0");
		assertFalse(actual);
	}

	@Test
	void DepositWithLettersInSavings() {
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 abc");
		assertFalse(actual);
	}
}
