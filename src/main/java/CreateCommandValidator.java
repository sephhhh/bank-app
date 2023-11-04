public class CreateCommandValidator {
	private Bank bank;

	public CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] commandArguments = command.split(" ");
		if (commandArguments.length == 1) {
			return false;
		}
		switch (commandArguments[1]) {
		case ("checking"):
			if (commandArguments.length != 4) {
				return false;
			} else if (commandArguments[5] != null) {
				return false;
			}
		case ("savings"):
			if (commandArguments.length != 4) {
				return false;
			} else if (commandArguments[5] != null) {
				return false;
			}
		case ("CD"):
			if (commandArguments.length != 5) {
				return false;
			} else if (commandArguments[3] == null || commandArguments[4] == null) {
				return false;
			}
		default:
			return false;
		}

	}
}
