package corso.spring.batch.demo.common.exceptions;

public class NoRollbackException extends RuntimeException{
	
	public NoRollbackException(String message){
		super(message);
	}
}
