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
}
