public class DepositCommandValidator extends CommandValidator {
	private Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	@Override
	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		Account account = bank.getAccountById("12345678");
		if ("checking".equals(account.getAccountType())) {
			if (commandArguments.length != 3) {
				return false;
			} else if (!canConvertToDouble(commandArguments[2])) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) > 2500.0) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) < 0.0) {
				return false;
			} else if (commandArguments[0].length() != 7) {
				return false;
			}
		} else if ("savings".equals(account.getAccountType())) {
			if (commandArguments.length != 3) {
				return false;
			} else if (!canConvertToDouble(commandArguments[2])) {
				return false;
			} else if (Double.parseDouble(commandArguments[2]) > 1000.0) {
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
