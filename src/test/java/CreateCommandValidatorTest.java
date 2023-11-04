import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandValidatorTest {
	Bank bank;
	CreateCommandValidator createCommandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		createCommandValidator = new CreateCommandValidator(bank);
	}

	@Test
	void createValidChecking() {
		boolean actual = createCommandValidator.validate("create checking 13245678 0.3");
		assertTrue(actual);
	}

	@Test
	void createValidCD() {
		boolean actual = createCommandValidator.validate("create CD 13245678 0.3 1000.0");
		assertTrue(actual);
	}

	@Test
	void createValidSavings() {
		boolean actual = createCommandValidator.validate("create savings 13245678 0.3");
		assertTrue(actual);
	}

	@Test
	void createCheckingWithNoArguments() {
		boolean actual = createCommandValidator.validate("create checking");
		assertFalse(actual);
	}

	@Test
	void createSavingsWithNoArguments() {
		boolean actual = createCommandValidator.validate("create savings");
		assertFalse(actual);
	}

	@Test
	void createCDWithNoArguments() {
		boolean actual = createCommandValidator.validate("create CD");
		assertFalse(actual);
	}

	@Test
	void createWithNoArguments() {
		boolean actual = createCommandValidator.validate("create");
		assertFalse(actual);
	}

	@Test
	void createCheckingWithBalance() {
		boolean actual = createCommandValidator.validate("create checking 12345678 0.3 1000.0");
		assertFalse(actual);
	}

	@Test
	void createSavingsWithBalance() {
		boolean actual = createCommandValidator.validate("create savings 12345678 0.3 1000.0");
		assertFalse(actual);
	}

	@Test
	void createMistypedChecking() {
		boolean actual = createCommandValidator.validate("create check 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createMistypedSavings() {
		boolean actual = createCommandValidator.validate("create sav 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createMistypedCD() {
		boolean actual = createCommandValidator.validate("create c 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCheckingWithoutCreateCommand() {
		boolean actual = createCommandValidator.validate("checking 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createSavingsWithoutCreateCommand() {
		boolean actual = createCommandValidator.validate("savings 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCDWithoutCreateCommand() {
		boolean actual = createCommandValidator.validate("CD 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCDWithTooManyArguments() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 1000.0 1234");
		assertFalse(actual);
	}

	@Test
	void createCDWithoutAprAndBalance() {
		boolean actual = createCommandValidator.validate("create CD 12345678");
		assertFalse(actual);
	}

	//
	@Test
	void createCDWithBalanceOverTenThousand() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 100000.0");
		assertFalse(actual);
	}

}
