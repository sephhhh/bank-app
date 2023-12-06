package banking;

public class WithdrawCommandValidator extends CommandValidator {
    private Bank bank;

    public WithdrawCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        String[] commandArguments = command.split(" ");
        if (commandArguments.length != 3) {
            return false;
        }
        if ((commandArguments[1].length() != 8) || !canConvertToDouble(commandArguments[1]) || commandArguments[0].length() != 8) {
            return false;
        }
        Account account = bank.getAccountById(commandArguments[1]);
        if ("checking".equals(account.getAccountType())) {
            if (!canConvertToDouble(commandArguments[2])) {
                return false;
            } else if (Double.parseDouble(commandArguments[2]) > 400.0) {
                return false;
            } else if (Double.parseDouble(commandArguments[2]) < 0.0) {
                return false;
            }
        } else if ("savings".equals(account.getAccountType())) {
            if (!canConvertToDouble(commandArguments[2])) {
                return false;
            } else if (Double.parseDouble(commandArguments[2]) > 1000.0) {
                return false;
            } else if (Double.parseDouble(commandArguments[2]) < 0.0) {
                return false;
            }
        } else if ("CD".equals(account.getAccountType())) {
            if (bank.getAccountById(commandArguments[1]).monthsPassed < 12) {
                return false;
            }
        }
        return true;
    }
}
