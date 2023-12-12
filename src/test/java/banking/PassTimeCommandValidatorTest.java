package banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassTimeCommandValidatorTest {
    Bank bank;
    PassTimeCommandValidator passTimeCommandValidator;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        passTimeCommandValidator = new PassTimeCommandValidator(bank);
    }

    @Test
    void invalid_pass_missing_arguments() {
        boolean actual = passTimeCommandValidator.validate("Pass");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_non_convertible_amount() {
        boolean actual = passTimeCommandValidator.validate("Pass abc");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_negative_months() {
        boolean actual = passTimeCommandValidator.validate("Pass -1");
        assertFalse(actual);
    }

    @Test
    void invalid_pass_months_over_60() {
        boolean actual = passTimeCommandValidator.validate("Pass 61");
        assertFalse(actual);
    }

    @Test
    void valid_pass_within_limit() {
        boolean actual = passTimeCommandValidator.validate("Pass 12");
        assertTrue(actual);
    }

    @Test
    void valid_pass_zero_months() {
        boolean actual = passTimeCommandValidator.validate("Pass 0");
        assertTrue(actual);
    }

    @Test
    void valid_pass_upper_limit_months() {
        boolean actual = passTimeCommandValidator.validate("Pass 60");
        assertTrue(actual);
    }
}
