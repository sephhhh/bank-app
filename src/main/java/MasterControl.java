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
		commandStorage.storeInvalidCommand("creat checking 12345678 1.0");
		return commandStorage.getStoredCommands();
	}
}
