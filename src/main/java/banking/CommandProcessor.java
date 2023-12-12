package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
	private Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void create(String command) {
		String[] commandArguments = command.split(" ");
		if ("savings".equals((commandArguments[1]).toLowerCase())) {
			Savings savings = new Savings(Double.parseDouble(commandArguments[3]), commandArguments[2]);
			bank.addAccount(savings);
		} else if ("checking".equals((commandArguments[1]).toLowerCase())) {
			Checkings checkings = new Checkings(Double.parseDouble(commandArguments[3]), commandArguments[2]);
			bank.addAccount(checkings);
		} else {
			CertificateOfDeposit cd = new CertificateOfDeposit(Double.parseDouble(commandArguments[3]), Double.parseDouble(commandArguments[4]), commandArguments[2]);
			bank.addAccount(cd);
		}
	}

	public void deposit(String command) {
		String[] commandArguments = command.split(" ");
		Account account = bank.getAccountById(commandArguments[1]);
		account.depositMoney(Double.parseDouble(commandArguments[2]));
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
		case "transfer":
			transfer(command);
			break;
		case "pass":
			passTime(command);
			break;
		default:
			break;
		}
	}

	public void passTime(String command) {
		String[] commandParts = command.split(" ");
		int months = Integer.parseInt(commandParts[1]);

		List<Account> accountsToClose = new ArrayList<>();

		while (months != 0) {
			for (Account account : bank.getAllAccounts().values()) {
				double balance = account.getBalance();
				if (balance == 0) {
					accountsToClose.add(account);
				} else if (balance < 100) {
					account.deductMoney();
					if (account.getAccountType() == "CD") {
						for (int i = 0; i <= 3; i++) {
							balance = account.calApr();
						}
					} else {
						balance = account.calApr();
					}
					account.changeBalance(balance);
				} else {
					balance = account.calApr();
					account.changeBalance(balance);
				}
			}

			months--;
		}

		// Close accounts with a balance of 0 outside the for loop
		for (Account accountToClose : accountsToClose) {
			bank.closeAccount(accountToClose);
		}
	}


	public void transfer(String command) {
		String[] commandParts = command.split(" ");
		bank.transfer(commandParts[1], commandParts[2], Double.parseDouble(commandParts[3]));
	}

}
