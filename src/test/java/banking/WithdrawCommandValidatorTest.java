package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithdrawCommandValidatorTest {

    Bank bank;
    WithdrawCommandValidator withdrawCommandValidator;
    Account savings;
    Account checking;


    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawCommandValidator = new WithdrawCommandValidator(bank);
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        checking = new Checkings(0.6, "87654321");
        checking.depositMoney(1000);
        bank.addAccount(checking);
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

    @Test
    void cannot_withdraw_invalid_account_id() {
        boolean actual = withdrawCommandValidator.validate("Withdraw invalidId 300");
        assertFalse(actual);
    }

    @Test
    void cannot_withdraw_negative_amount() {
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 -300");
        assertFalse(actual);
    }

    @Test
    void cannot_withdraw_amount_greater_than_limit_for_savings() {
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 1500");
        assertFalse(actual);
    }

    @Test
    void valid_command_arguments() {
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void invalid_length_of_command_argument_1() {
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 1234 100");
        assertFalse(actual);
    }

    @Test
    void invalid_non_convertible_to_double_command_argument_1() {
        boolean actual = withdrawCommandValidator.validate("InvalidCommand abcdefgh 100");
        assertFalse(actual);
    }

    @Test
    void invalid_length_of_command_argument_0() {
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 12345678 100");
        assertFalse(actual);
    }

    @Test
    void multiple_invalid_conditions() {
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 1234 abcdefgh");
        assertFalse(actual);
    }

    @Test
    void valid_checking_account_and_within_limit() {
        checking.depositMoney(500);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " 300");
        assertTrue(actual);
    }

    @Test
    void invalid_checking_account_and_not_convertible_to_double() {
        checking.depositMoney(500);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " abc");
        assertFalse(actual);
    }

    @Test
    void invalid_checking_account_and_exceeds_limit() {
        checking.depositMoney(500);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " 600");
        assertFalse(actual);
    }

    @Test
    void invalid_checking_account_and_negative_amount() {
        checking.depositMoney(500);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " -100");
        assertFalse(actual);
    }

    @Test
    void invalid_cd_withdraw_within_first_12_months() {
        Account cd = new CertificateOfDeposit(0.6,1000,"12345678");
        bank.addAccount(cd);
        cd.setMonthsPassed(5);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + "12345678" + " 500");
        assertFalse(actual);
    }

    @Test
    void valid_cd_withdraw_after_12_months() {
        Account cd = new CertificateOfDeposit(0.6,1000,"12345678");
        cd.setMonthsPassed(15);
        bank.addAccount(cd);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + cd.getId() + " 500");
        assertTrue(actual);
    }

    @Test
    void invalid_cd_withdraw_within_first_12_months_with_extra_arguments() {
        Account cd = new CertificateOfDeposit(0.6,1000,"12345678");
        cd.setMonthsPassed(8);
        bank.addAccount(cd);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + cd.getId() + " 500 43");
        assertFalse(actual);
    }








}
