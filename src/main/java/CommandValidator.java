public class CommandValidator {

	private Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (bank.getAccountById(commandArguments[1]) != null) {
			return false;
		}
		if (!commandArguments[1].matches("[0-9]+")) {
			return false;
		} else {
			if (commandArguments[1].length() < 8) {
				return false;
			} else if (commandArguments[1].length() > 8) {
				return false;
			}
		}
		return true;
	}

}
