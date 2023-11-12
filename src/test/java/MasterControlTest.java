import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {

	MasterControl masterControl;
	ArrayList<String> input;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
	}

	@Test
	void two_type_commands_both_invalid() {
		input.add("creat checking 12345678 1.0");
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("depositt 12345678 100", actual.get(1));

	}

	@Test
	void invalid_to_create_accounts_with_same_ID() {
		input.add("create checking 12345678 1.0");
		input.add("create checking 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("create checking 12345678 1.0", actual);
	}

	private void assertSingleCommand(String expectedCommand, List<String> actualCommands) {
		assertEquals(1, actualCommands.size(), "Expected a single command in the result, but found: " + actualCommands);
		assertEquals(expectedCommand, actualCommands.get(0), "Mismatched command in the result");
	}

}
