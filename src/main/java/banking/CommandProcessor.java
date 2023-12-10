package banking;

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

	public void withdraw(String command) {
		String[] commandArguments = command.split(" ");
		Account account = bank.getAccountById(commandArguments[1]);
		account.withdrawMoney(Double.parseDouble(commandArguments[2]));
	}

	public void processCommand(String command) {
		String[] commandParts = command.split(" ");
		String action = commandParts[0].toLowerCase();

		switch (action) {
		case "create":
			create(command);
			break;
		case "deposit":
			deposit(command);
			break;
		default:
			System.out.println("Invalid command: " + command);
			break;
		}
	}

	public void passTime(String command) {
		String[] commandParts = command.split(" ");
		while (Double.parseDouble(commandParts[1]) != 0) {
			for (Account account : bank.getAllAccounts().values()) {
				double balance = account.getBalance();
				if (balance == 0) {
					bank.closeAccount(account);
				} else if (balance < 100) {
					account.deductMoney();
					if (account.getAccountType() == "CD") {
						for (int i = 0; i<=3; i++) {
							balance = account.calApr();
						}
					} else {
						balance = account.calApr();
					}
					account.changeBalance(balance);
				}
			}
		}
	}

	public void transfer(String command) {
		String[] commandParts = command.split(" ");
		bank.transfer(commandParts[1], commandParts[2], Double.parseDouble(commandParts[3]));
	}

}
