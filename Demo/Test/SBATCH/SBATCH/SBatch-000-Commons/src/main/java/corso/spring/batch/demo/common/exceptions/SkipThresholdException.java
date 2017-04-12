package corso.spring.batch.demo.common.exceptions;

public class SkipThresholdException extends RuntimeException{

	public SkipThresholdException(String message){
		super(message);
	}
}
