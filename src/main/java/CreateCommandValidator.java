public class CreateCommandValidator extends CommandValidator {
	private Bank bank;

	public CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	@Override
	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments.length == 1) {
			return false;
		}
		switch (commandArguments[1]) {
		case ("checking"):
		case ("savings"):
			if (commandArguments.length != 4) {
				return false;
			} else {
				return true;
			}
		case ("CD"):
			if (commandArguments.length != 5) {
				return false;
			} else if (commandArguments[3] == null || commandArguments[4] == null) {
				return false;
			} else if (Double.parseDouble(commandArguments[4]) > 10000.0) {
				return false;
			}
		default:
			return false;
		}

	}
}
