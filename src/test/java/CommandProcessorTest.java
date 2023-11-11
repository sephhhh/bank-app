import static org.junit.jupiter.api.Assertions.assertEquals;

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
		commandProcessor.create("create cd 1000.0 1.0 12345678");
		assertEquals("12345678", bank.accountExistsById("12345678"));
		assertEquals(1.0, bank.getAccountById("12345678").getApr());
		assertEquals(1000.0, bank.getAccountById("12345678").getBalance());
	}

	@Test
	void depositChecking() {
		commandProcessor.deposit("deposit 12345678 500");
		assertEquals(500, bank.getAccountById("12345678").getBalance());
	}

}
