package corso.spring.batch.demo.modulo.stato.job.service;


public class NumberServiceImpl implements NumberService{

	
	
	@Override
	public Integer generateNumber() {
		Integer generated= new Integer(contatore);
		contatore++;
		return generated;
	}
	
	
	private int min=1;
	private int max=100;
	private int contatore=1;
	
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}


}
