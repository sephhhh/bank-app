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
	void duplicateAcc() {
		boolean actual = commandValidator.validate("create 12345678 savings");
		assertTrue(actual);
	}

	@Test
	void createAccIdWithInvalidIdLessThanEight() {
		boolean actual = commandValidator.validateId("create 1234567 savings");
		assertFalse(actual);
	}

	@Test
	void createAccIdWithInvalidIdMoreThanEight() {
		boolean actual = commandValidator.validateId("create 123456789 savings");
		assertFalse(actual);
	}

	@Test
	void createAccIdWithStrings() {
		boolean actual = commandValidator.validateId("create abcdegh savings");
		assertFalse(actual);
	}

}
