package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	@Test
	public void testStoreInvalidCommand() {
		CommandStorage commandStorage = new CommandStorage();
		commandStorage.storeInvalidCommand("invalidCommand1");

		List<String> storedCommands = commandStorage.getStoredCommands();

		assertEquals(1, storedCommands.size());
		assertEquals("invalidCommand1", storedCommands.get(0));
	}
}
