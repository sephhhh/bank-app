import org.junit.jupiter.api.BeforeEach;

public class MasterControlTest {

	MasterControl masterControl;

	@BeforeEach
	void setUp() {
		Bank bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
	}
}
