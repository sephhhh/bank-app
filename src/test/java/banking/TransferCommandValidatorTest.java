package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferCommandValidatorTest {

    Bank bank;
    TransferCommandValidator transferCommandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        transferCommandValidator = new TransferCommandValidator(bank);
    }

    @Test
    void invalid_transfer_missing_arguments() {
        boolean actual = transferCommandValidator.validate("Transfer");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_invalid_length_of_account1() {
        boolean actual = transferCommandValidator.validate("Transfer 1234 87654321 500.0");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_non_convertible_amount_in_account1() {
        boolean actual = transferCommandValidator.validate("Transfer abcdefgh 87654321 500.0");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_invalid_length_of_account2() {
        boolean actual = transferCommandValidator.validate("Transfer 12345678 4321 500.0");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_non_convertible_amount_in_account2() {
        boolean actual = transferCommandValidator.validate("Transfer 12345678 abcdefgh 500.0");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_non_convertible_amount_in_amount() {
        boolean actual = transferCommandValidator.validate("Transfer 12345678 87654321 abc");
        assertFalse(actual);
    }

    @Test
    void valid_transfer_within_limit() {
        Account account1 = new Savings(0.6, "12345678");
        Account account2 = new Checkings(0.6, "87654321");
        bank.addAccount(account1);
        bank.addAccount(account2);

        boolean actual = transferCommandValidator.validate("Transfer 12345678 87654321 500.0");
        assertTrue(actual);
    }

    @Test
    void invalid_transfer_from_cd_account1() {
        Account account1 = new CertificateOfDeposit(0.6, 1000,"12345678");
        Account account2 = new Checkings(0.6, "87654321");
        bank.addAccount(account1);
        bank.addAccount(account2);

        boolean actual = transferCommandValidator.validate("Transfer 12345678 87654321 500.0");
        assertFalse(actual);
    }

    @Test
    void invalid_transfer_to_cd_account2() {
        Account account1 = new Savings(0.6, "12345678");
        Account account2 = new CertificateOfDeposit(0.6, 1000,"12345678");
        bank.addAccount(account1);
        bank.addAccount(account2);

        boolean actual = transferCommandValidator.validate("Transfer 12345678 87654321 500.0");
        assertFalse(actual);
    }
}
