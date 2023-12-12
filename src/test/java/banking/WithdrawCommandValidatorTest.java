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
    Account cd;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        withdrawCommandValidator = new WithdrawCommandValidator(bank);
    }

    @Test
    void can_withdraw_money() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 300");
        assertTrue(actual);
    }

    @Test
    void cannot_withdraw_with_extra_arguments() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 300 43");
        assertFalse(actual);
    }

    @Test
    void cannot_withdraw_invalid_account_id() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw invalidId 300");
        assertFalse(actual);
    }

    @Test
    void cannot_withdraw_negative_amount() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 -300");
        assertFalse(actual);
    }

    @Test
    void cannot_withdraw_amount_greater_than_limit_for_savings() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 1500");
        assertFalse(actual);
    }

    @Test
    void valid_command_arguments() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw 12345678 100");
        assertTrue(actual);
    }

    @Test
    void invalid_length_of_command_argument_1() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 1234 100");
        assertFalse(actual);
    }

    @Test
    void invalid_non_convertible_to_double_command_argument_1() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("InvalidCommand abcdefgh 100");
        assertFalse(actual);
    }

    @Test
    void invalid_length_of_command_argument_0() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 12345678 100");
        assertFalse(actual);
    }

    @Test
    void multiple_invalid_conditions() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("InvalidCommand 1234 abcdefgh");
        assertFalse(actual);
    }

    @Test
    void valid_checking_account_and_within_limit() {
        checking = new Checkings(0.6, "87654321");
        checking.depositMoney(1000);
        bank.addAccount(checking);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " 300");
        assertTrue(actual);
    }

    @Test
    void invalid_checking_account_and_not_convertible_to_double() {
        checking = new Checkings(0.6, "87654321");
        checking.depositMoney(1000);
        bank.addAccount(checking);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " abc");
        assertFalse(actual);
    }

    @Test
    void invalid_checking_account_and_exceeds_limit() {
        checking = new Checkings(0.6, "87654321");
        checking.depositMoney(1000);
        bank.addAccount(checking);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " 600");
        assertFalse(actual);
    }

    @Test
    void invalid_checking_account_and_negative_amount() {
        checking = new Checkings(0.6, "87654321");
        checking.depositMoney(1000);
        bank.addAccount(checking);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + checking.getId() + " -100");
        assertFalse(actual);
    }

    @Test
    void invalid_cd_withdraw_within_first_12_months() {
        cd = new CertificateOfDeposit(0.6,1000,"12345678");
        bank.addAccount(cd);
        cd.setMonthsPassed(5);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + "12345678" + " 500");
        assertFalse(actual);
    }

    @Test
    void valid_cd_withdraw_after_12_months() {
        cd = new CertificateOfDeposit(0.6,1000,"12345678");
        cd.setMonthsPassed(15);
        bank.addAccount(cd);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + cd.getId() + " 500");
        assertTrue(actual);
    }

    @Test
    void invalid_cd_withdraw_within_first_12_months_with_extra_arguments() {
        cd = new CertificateOfDeposit(0.6,1000,"12345678");
        cd.setMonthsPassed(8);
        bank.addAccount(cd);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + cd.getId() + " 500 43");
        assertFalse(actual);
    }

    @Test
    void invalid_savings_withdraw_with_non_convertible_amount() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + savings.getId() + " abc");
        assertFalse(actual);
    }

    @Test
    void invalid_savings_withdraw_exceeds_limit() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + savings.getId() + " 1500");
        assertFalse(actual);
    }

    @Test
    void invalid_savings_withdraw_negative_amount() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + savings.getId() + " -100");
        assertFalse(actual);
    }

    @Test
    void invalid_savings_withdraw_after_first_withdraw() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        savings.withdrawMoney(100);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + savings.getId() + " 300");
        assertFalse(actual);
    }

    @Test
    void valid_savings_withdraw_within_limit_and_first_withdraw() {
        savings = new Savings(0.6, "12345678");
        savings.depositMoney(1000);
        bank.addAccount(savings);
        boolean actual = withdrawCommandValidator.validate("Withdraw " + savings.getId() + " 500");
        assertTrue(actual);
    }








}
