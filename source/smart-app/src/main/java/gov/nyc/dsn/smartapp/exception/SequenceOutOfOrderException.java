package gov.nyc.dsn.smartapp.exception;

public class SequenceOutOfOrderException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SequenceOutOfOrderException(String message){
		super(message);
	}

}
