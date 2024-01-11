package ar.edu.unju.fi.Biblioteca.exceptions;

public class ManagerException extends RuntimeException {
    private static final long serialVersionUID = 0;
    private Throwable cause;
    
    /**
     * Constructs with an explanatory message.
     *
     * @param message
     *            Detail about the reason for the exception.
     */
    public ManagerException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new Exception with the specified cause.
     * @param cause The cause.
     */
	public ManagerException	(Throwable cause) {
		super();
		this.cause = cause;
	}    
	
	
    /**
     * Returns the cause of this exception or null if the cause is nonexistent
     * or unknown.
     *
     * @return the cause of this exception or null if the cause is nonexistent
     *          or unknown.
     */
    @Override
    public Throwable getCause() {
        return this.cause;
    }    
    

}
