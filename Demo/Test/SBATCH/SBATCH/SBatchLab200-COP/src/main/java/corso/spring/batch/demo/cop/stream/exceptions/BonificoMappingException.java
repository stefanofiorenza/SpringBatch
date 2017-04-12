package corso.spring.batch.demo.cop.stream.exceptions;

import lombok.Getter;
import lombok.Setter;

public class BonificoMappingException extends RuntimeException{

	@Getter
	@Setter
	private int record;
	
	@Getter
	@Setter
	private int page;
	
	@Getter
	@Setter
	private String orderId;
	
	public BonificoMappingException(String message){
		super(message);
	}
	
	public BonificoMappingException(Exception cause){
		super(cause);
	}
	
}
