package corso.spring.batch.demo.modulo.exceptions.job;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;

import corso.spring.batch.demo.common.exceptions.ExceptionDemoUtils;
import corso.spring.batch.demo.common.exceptions.RetryFactorException;
import corso.spring.batch.demo.common.exceptions.SkipThresholdException;

@Slf4j
public abstract class AbstractChunkOrientedDemo {
		
	

	protected StepExecution stepExecution;
	
	@BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
	
	protected void executeDangerousOperation(int currNumber){
		log.info(this.getClass().getName()+": execute test for n: "+currNumber);
		ExceptionDemoUtils.evaluateExceptions(currNumber,this.getClass());
		log.info(this.getClass().getName()+": test passed for n: "+currNumber);
	}
	
	
	


	//final static Logger log = Logger.getLogger(AbstractDemoException.class);
}
