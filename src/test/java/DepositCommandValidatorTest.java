import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {
	Bank bank;
	DepositCommandValidator depositCommandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		depositCommandValidator = new DepositCommandValidator(bank);
	}

	@Test
	void DepositingWithTwoArgumentsAsAmountWithChecking() {
		Checkings checking = new Checkings(0.3, "12345678");
		bank.addAccount(checking);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

	@Test
	void DepositingWithTwoArgumentsAsAmountWithSavings() {
		Savings savings = new Savings(0.3, "12345678");
		bank.addAccount(savings);
		boolean actual = depositCommandValidator.validate("Deposit 12345678 500 500");
		assertFalse(actual);
	}

}
