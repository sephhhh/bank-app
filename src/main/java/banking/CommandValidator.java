package banking;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CommandValidator {
	private Bank bank;
	private CreateCommandValidator createCommandValidator;
	private DepositCommandValidator depositCommandValidator;
	private TransferCommandValidator transferCommandValidator;
	private PassTimeCommandValidator passTimeCommandValidator;

	public CommandValidator(Bank bank) {
		this.bank = bank;
		this.createCommandValidator = new CreateCommandValidator(bank);
		this.depositCommandValidator = new DepositCommandValidator(bank);
		this.transferCommandValidator = new TransferCommandValidator(bank);
		this.passTimeCommandValidator = new PassTimeCommandValidator(bank);
	}

	public CommandValidator() {
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (!commandType((commandArguments[0]).toLowerCase())) {
			return false;
		}
		switch(commandArguments[0].toLowerCase()) {
			case("deposit") :
				return depositCommandValidator.validate(command);
			case("transfer") :
				return transferCommandValidator.validate(command);
			case("pass") :
				return passTimeCommandValidator.validate(command);
		}


		if (!commandArguments[2].matches("[0-9]+")) {
			return false;
		} else if (commandArguments[2].length() != 8) {
			return false;
		} else if (!hasValueAtIndex(commandArguments, 3)) {
			return false;
		} else if (!canConvertToDouble(commandArguments[3])) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) < 0.0) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) > 10.0) {
			return false;
		}
		if ("create".equalsIgnoreCase(commandArguments[0])) {
			return createCommandValidator.validate(command);
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

	public boolean hasValueAtIndex(String[] array, int index) {
		return array != null && index >= 0 && index < array.length;
	}
}
