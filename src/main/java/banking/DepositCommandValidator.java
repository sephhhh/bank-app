package banking;

import java.util.Locale;

public class DepositCommandValidator extends CommandValidator {
	private Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	@Override
	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments.length != 3) {
			return false;
		}
		if ((commandArguments[1].length() != 8) || !canConvertToDouble(commandArguments[1])) {
			return false;
		}
		Account account = bank.getAccountById(commandArguments[1]);
		if ("checking".equals(account.getAccountType())) {
			if (!canConvertToDouble(commandArguments[2])) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) > 1000.0) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) < 0.0) {
				return false;
			} else if (commandArguments[0].length() != 7) {
				return false;
			}
		} else if ("savings".equals(account.getAccountType())) {
			if (!canConvertToDouble(commandArguments[2])) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) > 2500.0) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) < 0.0) {
				return false;
			}
		} else if ("CD".equals(account.getAccountType())) {
			if (commandArguments.length > 0) {
				return false;
			}
		}
		return true;
	}
}
