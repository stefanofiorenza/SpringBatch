package corso.spring.batch.demo.modulo.exceptions;

public class RetryThresholdException extends RuntimeException{

	public RetryThresholdException(String message){
		super(message);
	}
}
