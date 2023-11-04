public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (bank.getAccountById(commandArguments[2]) != null) {
			return false;
		}
		if (!commandArguments[2].matches("[0-9]+")) {
			return false;
		} else if (commandArguments[2].length() != 8) {
			return false;
		}

		if (commandArguments.length <= 3 || commandArguments[3] == null) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) < 0.0) {
			return false;
		} else if (Double.parseDouble(commandArguments[3]) > 10.0) {
			return false;
		}
		return true;
	}

}
