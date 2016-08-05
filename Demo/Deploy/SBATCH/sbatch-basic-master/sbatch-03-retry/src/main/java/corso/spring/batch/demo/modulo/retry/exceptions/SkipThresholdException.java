package corso.spring.batch.demo.modulo.retry.exceptions;

public class SkipThresholdException extends RuntimeException{

	public SkipThresholdException(String message){
		super(message);
	}
}
