package corso.spring.batch.demo.modulo.exceptions;

public class SkipThresholdException extends RuntimeException{

	public SkipThresholdException(String message){
		super(message);
	}
}
