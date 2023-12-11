package banking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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


	public List<String> output(List<String> input) {
		List<String> outputList = new ArrayList<>();
		for (String command : input) {
			String[] commandParts = command.split(" ");
			if ("create".equals(commandParts[0])) {
				Bank bank = new Bank();
				Account account = bank.getAccountById(commandParts[2]);
				String formattedOutput = String.format("%s %s %.2f %s", commandParts[1], commandParts[2], account.getBalance(), commandParts[3]);
				outputList.add(formattedOutput);


				List<String> originalList = account.transactionHistory;
				List<String> reversedList = new ArrayList<>(originalList);
				Collections.reverse(reversedList);

				for (String transaction : account.transactionHistory) {
					outputList.add(transaction);
				}
			}
		}

		List<String> originalInvalidCommands = commandStorage.getStoredCommands();
		List<String> reversedInvalidCommands = new ArrayList<>(originalInvalidCommands);
		Collections.reverse(reversedInvalidCommands);

		for (String command : reversedInvalidCommands) {
			outputList.add(command);
		}

		return outputList;
	}


}
