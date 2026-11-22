package tp1.pvz.exceptions;

public class RecordException extends GameException {
	private static final long serialVersionUID = 1L;

	public RecordException(String message) {
		super(message);
	}

	public RecordException(String message, Throwable cause) {
		super(message, cause);
	}

}
