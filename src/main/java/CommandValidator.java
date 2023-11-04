public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		return (bank.getAccountById(commandArguments[1]) == null) ? true : false;
	}

	public boolean validateId(String command) {
		String[] commandArguments = command.split(" ");
		if ((commandArguments[1]) instanceof String) {
			return false;
		} else {
			if (commandArguments[1].length() < 8) {
				return false;
			} else if (commandArguments[1].length() > 8) {
				return false;
			} else {
				return true;
			}
		}
	}

}
