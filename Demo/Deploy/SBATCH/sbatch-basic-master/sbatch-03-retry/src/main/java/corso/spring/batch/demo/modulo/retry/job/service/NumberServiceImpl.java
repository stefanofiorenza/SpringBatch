package corso.spring.batch.demo.modulo.retry.job.service;

import java.util.Random;

import corso.spring.batch.demo.modulo.retry.exceptions.RetryThresholdException;
import corso.spring.batch.demo.modulo.retry.exceptions.SkipThresholdException;

public class NumberServiceImpl implements NumberService{

	@Override
	public Integer generateNumber() {
	int generated= random.nextInt(max+1);//max inclusive
	
		if(generated<min){
			generated=min;
		}
		return new Integer(generated);
	}
	
	
	private int min=1;
	private int max=100;
	private Random random= new Random();
	
	
	
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
