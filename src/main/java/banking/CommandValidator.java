package banking;

import java.util.Set;

public class CommandValidator {
	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public CommandValidator() {
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (bank.getAccountById(commandArguments[2]) != null) {
			return false;
		}
		if (!commandType(commandArguments[0])) {
			return false;
		}

		if (!commandArguments[2].matches("[0-9]+")) {
			return false;
		} else if (commandArguments[2].length() != 8) {
			return false;
		}

		if (commandArguments.length <= 3 || commandArguments[3] == null) {
			return false;
		} else if (!canConvertToDouble(commandArguments[3])) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) < 0.0) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) > 10.0) {
			return false;
		}

		return true;
	}

	public boolean canConvertToDouble(String command) {
		try {
			Double.parseDouble(command);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean commandType(String accountType) {
		return Set.of("create", "deposit", "withdraw", "pass", "transfer").contains(accountType);
	}
}
