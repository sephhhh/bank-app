import org.junit.jupiter.api.BeforeEach;

public class CreateCommandValidatorTest {
	Bank bank;
	CommandValidator commandValidator;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

}
