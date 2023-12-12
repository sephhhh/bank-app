package banking;

public class PassTimeCommandValidator extends CommandValidator{

    Bank bank;

    public PassTimeCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String command) {
        String[] commandArguments = command.split(" ");
        if (commandArguments.length != 2) {
            return false;
        }
        if (!canConvertToDouble(commandArguments[1]) || commandArguments[0].length() != 4) {
            return false;
        }
        if (Double.parseDouble(commandArguments[1]) < 0 || Double.parseDouble(commandArguments[1]) > 60) {
            return false;
        }
        return true;
    }
}
