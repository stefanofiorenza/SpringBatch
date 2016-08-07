package corso.spring.batch.demo.modulo.exceptions.job.service;


public class NumberServiceImpl implements NumberService{


	private int contatore=1;


	@Override
	public Integer produceNumber() {
		if (contatore==101){
			return null;
		}
		return new Integer(contatore);			
	}


	@Override
	public void ackNumber() {
		contatore++;		
	}
	
}
