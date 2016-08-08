package corso.spring.batch.demo.modulo.exceptions.job.chuncks;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;

import corso.spring.batch.demo.modulo.exceptions.RetryFactorException;
import corso.spring.batch.demo.modulo.exceptions.SkipThresholdException;

public abstract class AbstractChunkProcessing {
		
	//1) Probability Exc Settings
	private int retryFactor=101; 
	private int skipThresHold=1000; 
	
	//2) generator Settings
	private int min=1;
	private int max=100;
	private Random random= new Random();

	protected StepExecution stepExecution;
	
	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
	
	protected void executeDangerousOperation(int currNumber){
		log.info(this.getClass().getName()+": execute test for n: "+currNumber);
		evaluateExceptions(currNumber,generateNumber());
		log.info(this.getClass().getName()+": test passed for n: "+currNumber);
	}
	
	
	private int generateNumber(){
		
		int generated= random.nextInt(max+1);//max inclusive		
		if(generated<min){
			generated=min;
		}
		return generated;
	}
	
	
	private void evaluateExceptions(int currNumber,int probNumber){		
		
		if(probNumber%retryFactor==0){
			log.info(this.getClass().getName()+": test for n: "+currNumber+" was failing RetryEx");
			throw new RetryFactorException("Generated: "+probNumber+
					" was divisible for RETRY value: "+retryFactor);
		}
	
		if(probNumber<skipThresHold){
			log.info(this.getClass().getName()+": test for n: "+currNumber+" was failing SkipEx");
			throw new SkipThresholdException("Generated: "+probNumber+
					" was lower than SKIP threshold: "+skipThresHold);
		}
	}


	final static Logger log = Logger.getLogger(AbstractChunkProcessing.class);
}
