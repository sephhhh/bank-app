public class CommandProcessor {
	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void create(String command) {
		String[] commandArguments = command.split(" ");
		switch (commandArguments[1]) {
		case ("checking"):
			Checkings checking = new Checkings(Double.parseDouble(commandArguments[3]), commandArguments[2]);
			bank.addAccount(checking);

			break;
		case ("savings"):
			Savings savings = new Savings(Double.parseDouble(commandArguments[3]), commandArguments[2]);
			bank.addAccount(savings);
			break;
		case ("cd"):
			CertificateOfDeposit cd = new CertificateOfDeposit(Double.parseDouble(commandArguments[3]),
					Double.parseDouble(commandArguments[2]), commandArguments[4]);
			bank.addAccount(cd);
			break;
		}
	}

	public void deposit(String command) {
		String[] commandArguments = command.split(" ");
		Checkings checking = new Checkings(1.0, commandArguments[1]);
		bank.addAccount(checking);
		bank.getAccountById(commandArguments[1]).depositMoney(Double.parseDouble(commandArguments[2]));
	}

}
