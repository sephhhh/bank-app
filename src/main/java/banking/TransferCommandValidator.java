package banking;

public class TransferCommandValidator extends CommandValidator{
    private Bank bank;

    public TransferCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        String[] commandArguments = command.split(" ");
        if (commandArguments.length != 4) {
            return false;
        }
        if ((commandArguments[1].length() != 8) || !canConvertToDouble(commandArguments[1]) || commandArguments[2].length() != 8 || !canConvertToDouble(commandArguments[2])) {
            return false;
        } else if (!canConvertToDouble(commandArguments[3])) {
            return false;
        }
        Account account1 = bank.getAccountById(commandArguments[1]);
        Account account2 = bank.getAccountById(commandArguments[2]);
        if ("cd".equals(account1.getAccountType()) || "cd".equals(account2.getAccountType())) {
            return false;
        }
        return true;
    }
}
