package banking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MasterControl {
	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;
	private Bank bank;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage, Bank bank) {
		this.commandValidator = commandValidator;
		this.commandStorage = commandStorage;
		this.commandProcessor = commandProcessor;
		this.bank = bank;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.validate(command)) {
				commandStorage.storeValidCommands(command);
				commandProcessor.processCommand(command);
			} else {
				commandStorage.storeInvalidCommand(command);
			}
		}
		System.out.println(commandStorage.getValidCommands());
		return output(commandStorage.getValidCommands());
	}


	public List<String> output(List<String> input) {
		List<String> outputList = new ArrayList<>();

		for (String command : input) {
			String[] commandParts = command.split(" ");
			if (commandParts.length >= 4 && "create".equals(commandParts[0].toLowerCase())) {
				Account account = bank.getAccountById(commandParts[2]);

				if (account != null) {
					String accountType = Character.toUpperCase(commandParts[1].charAt(0)) + commandParts[1].substring(1);
					String formattedOutput = String.format("%s %s %.2f %.2f", accountType, commandParts[2], account.getBalance(), Double.parseDouble(commandParts[3]));
					outputList.add(formattedOutput);

					if (account.transactionHistory != null) {
						outputList.addAll(account.transactionHistory);
					}
				}
			}
		}

		List<String> invalidCommands = commandStorage.getInvalidCommands();

		if (invalidCommands != null) {
			outputList.addAll(invalidCommands);
		}

		return outputList;
	}




}
