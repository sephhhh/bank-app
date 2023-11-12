import java.util.List;

public class MasterControl {
	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage) {
		this.commandValidator = commandValidator;
		this.commandStorage = commandStorage;
		this.commandProcessor = commandProcessor;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.validate(command)) {
				commandProcessor.processCommand(command);
			} else {
				commandStorage.storeInvalidCommand(command);
			}
		}
		return commandStorage.getStoredCommands();
	}

}
