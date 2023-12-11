package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
	private Map<String, Account> bank;
	private int transferToken;
	private List<String> transferHistory;

	Bank() {
		bank = new HashMap<>();
		transferHistory = new ArrayList<>();
		transferToken = 0;
	}

	public int getTransferToken() {
		return transferToken;
	}

	public List<String> getTransferHistory() {
		return transferHistory;
	}

	public Map<String, Account> getAllAccounts() {
		return bank;
	}

	public void addAccount(Account account) {
		bank.put(account.getId(), account);
	}

	public Account getAccountById(String id) {
		return bank.get(id);
	}

	public void depositMoneyById(String id, double amount) {
		Account account = bank.get(id);
		account.depositMoney(amount);
	}

	public void withdrawMoneyById(String id, double amount) {
		Account account = bank.get(id);
		account.withdrawMoney(amount);
	}

	public String accountExistsById(String id) {
		Account account = bank.get(id);
		return account.getId();
	}

	public void closeAccount(Account account) {
		account.numTimesWithdrew = 0;
		bank.remove(account.getId());
	}

	public void transfer(String id1, String id2, double amount) {
		Account account1 = bank.get(id1);
		Account account2 = bank.get(id2);
		if (account1.getBalance() < amount) {
			amount = account1.getBalance();
		}
		account1.withdrawMoney(amount);
		account2.depositMoney(amount);
		String formattedOutput = String.format("Transfer %s %s %.2f", id1, id2, amount);
		transferHistory.add(formattedOutput);
		transferToken++;
	}
}
