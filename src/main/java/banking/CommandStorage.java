package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandStorage {

	private List<String> storedValidCommands;
	private List<String> storedInvalidCommands;

	public CommandStorage() {
		storedValidCommands = new ArrayList<>();
		storedInvalidCommands = new ArrayList<>();
	}

	public void storeValidCommands(String command) {
		storedValidCommands.add(command);
	}

	public void storeInvalidCommand(String command) {
		storedInvalidCommands.add(command);
	}

	public List<String> getValidCommands() {
		return storedValidCommands;
	}

	public List<String> getInvalidCommands() {
		return storedInvalidCommands;
	}

}
