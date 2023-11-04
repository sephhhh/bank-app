import static org.junit.jupiter.api.Assertions.assertFalse;

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
}
