package tp1.pvz.exceptions;

public class NotEnoughSuncoinsException extends CommandExecuteException {
    
	private static final long serialVersionUID = 1L;
    
	public NotEnoughSuncoinsException() {
        super();
    }
    
	public NotEnoughSuncoinsException(String message) {
        super(message);
    }
    
	public NotEnoughSuncoinsException(Throwable cause) {
        super(cause);
    }
    
	public NotEnoughSuncoinsException(String message, Throwable cause) {
        super(message, cause);
    }
}
