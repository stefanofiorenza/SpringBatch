package corso.spring.batch.demo.modulo.exceptions;

public class RetryFactorException extends RuntimeException{
//public class RetryFactorException extends SkipThresholdException{
	
	public RetryFactorException(String message){
		super(message);
	}
}
