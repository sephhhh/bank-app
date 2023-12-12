package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {

    Bank bank;
    WithdrawCommandValidator withdrawCommandValidator;
    Account savings;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawCommandValidator = new WithdrawCommandValidator(bank);
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
    }

    @Test
    void can_withdraw_money() {
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    void cannot_withdraw_with_extra_arguments() {
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 300 43");
        assertFalse(actual);
    }

}
