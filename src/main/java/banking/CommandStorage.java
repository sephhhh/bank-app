package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandStorage {

	private List<String> storedCommands;

	public CommandStorage() {
		storedCommands = new ArrayList<>();
	}

	public void storeInvalidCommand(String command) {
		storedCommands.add(command);
	}

	public List<String> getStoredCommands() {
		return storedCommands;
	}

}
