import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	Bank bank;
	CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void addAccWithNonDuplicateId() {
		Savings savings = new Savings(0.3, "00000000");
		bank.addAccount(savings);
		boolean actual = commandValidator.validate("create savings 12345678 0.3");
		assertTrue(actual);
	}

	@Test
	void createAccIdWithInvalidIdLessThanEight() {
		boolean actual = commandValidator.validate("create savings 1234567 0.3");
		assertFalse(actual);
	}

	@Test
	void createAccIdWithInvalidIdMoreThanEight() {
		boolean actual = commandValidator.validate("create savings 123456789 0.3");
		assertFalse(actual);
	}

	@Test
	void createAccIdWithNoId() {
		boolean actual = commandValidator.validate("create savings 0.3");
		assertFalse(actual);
	}

	@Test
	void createAccIdWithLetters() {
		boolean actual = commandValidator.validate("create savings abcdeghj 0.3");
		assertFalse(actual);
	}

	@Test
	void createAccWithLettersAndNumbers() {
		boolean actual = commandValidator.validate("create savings abcd1234 0.3");
		assertFalse(actual);
	}

	@Test
	void createAccWithAprLessThanZero() {
		boolean actual = commandValidator.validate("create savings 12345678 -1.0");
		assertFalse(actual);
	}

	@Test
	void createAccWithAprGreaterThanTen() {
		boolean actual = commandValidator.validate("create savings 12345678 30.0");
		assertFalse(actual);
	}

	@Test
	void createAccWithNoApr() {
		boolean actual = commandValidator.validate("create savings 12345678");
		assertFalse(actual);
	}

}
