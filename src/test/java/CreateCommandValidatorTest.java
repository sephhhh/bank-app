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
	void createValidCDWithDoubleAsBalance() {
		boolean actual = createCommandValidator.validate("create CD 13245678 0.3 1000.0");
		assertTrue(actual);
	}

	@Test
	void createValidCDWithIntAsBalance() {
		boolean actual = createCommandValidator.validate("create CD 13245678 0.3 1000");
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

	@Test
	void createCDWithBalanceOverTenThousand() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 100000.0");
		assertFalse(actual);
	}

	@Test
	void createCDWithBalanceUnderOneThousand() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 100.0");
		assertFalse(actual);
	}

	@Test
	void createCDWithBalanceAsLetter() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 abc");
		assertFalse(actual);
	}

	@Test
	void createCDWithoutBalance() {
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCheckingWithExistingIdAsAnotherChecking() {
		Checkings checking = new Checkings(0.3, "12345678");
		bank.addAccount(checking);
		boolean actual = createCommandValidator.validate("create checking 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCheckingWithExistingIdAnotherCD() {
		CertificateOfDeposit cd = new CertificateOfDeposit(0.3, 1000.0, "12345678");
		bank.addAccount(cd);
		boolean actual = createCommandValidator.validate("create checking 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCheckingWithExistingIdAnotherSavings() {
		Savings savings = new Savings(0.3, "12345678");
		bank.addAccount(savings);
		boolean actual = createCommandValidator.validate("create checking 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createSavingsWithExistingIdWithAnotherSavings() {
		Savings savings = new Savings(0.3, "12345678");
		bank.addAccount(savings);
		boolean actual = createCommandValidator.validate("create savings 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createSavingsWithExistingIdAnotherChecking() {
		Checkings checking = new Checkings(0.3, "12345678");
		bank.addAccount(checking);
		boolean actual = createCommandValidator.validate("create savings 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createSavingWithExistingIdAnotherCD() {
		CertificateOfDeposit cd = new CertificateOfDeposit(0.3, 1000.0, "12345678");
		bank.addAccount(cd);
		boolean actual = createCommandValidator.validate("create savings 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void createCDWithExistingIdAnotherCD() {
		CertificateOfDeposit cd = new CertificateOfDeposit(0.3, 1000.0, "12345678");
		bank.addAccount(cd);
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 1000.0");
		assertFalse(actual);
	}

	@Test
	void createCDWithExistingIdAnotherChecking() {
		Checkings checking = new Checkings(0.3, "12345678");
		bank.addAccount(checking);
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 1000.0");
		assertFalse(actual);
	}

	@Test
	void createCDWithExistingIdAnotherSavings() {
		Savings savings = new Savings(0.3, "12345678");
		bank.addAccount(savings);
		boolean actual = createCommandValidator.validate("create CD 12345678 0.3 1000.0");
		assertFalse(actual);
	}
}
