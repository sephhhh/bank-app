package banking;

public class WithdrawCommandValidator extends CommandValidator{
    private Bank bank;

    public WithdrawCommandValidator(Bank bank) {
         this.bank = bank;
    }

}
