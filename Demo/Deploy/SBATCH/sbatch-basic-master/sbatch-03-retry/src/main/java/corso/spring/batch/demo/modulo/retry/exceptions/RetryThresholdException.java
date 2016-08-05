package corso.spring.batch.demo.modulo.retry.exceptions;

public class RetryThresholdException extends RuntimeException{

	public RetryThresholdException(String message){
		super(message);
	}
}
