package au.com.bluej.api.exception;

@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long id) {
	    super("Could not find account " + id);
	}
}