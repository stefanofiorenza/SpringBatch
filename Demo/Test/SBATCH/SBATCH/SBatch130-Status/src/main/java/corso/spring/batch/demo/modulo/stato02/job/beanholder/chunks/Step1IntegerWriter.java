package corso.spring.batch.demo.modulo.stato02.job.beanholder.chunks;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.stato.job.service.BeanDataHolder;
import corso.spring.batch.demo.modulo.stato.launcher.Consts;



public class Step1IntegerWriter implements ItemWriter<Integer>{

	final static Logger log = Logger.getLogger(Step1IntegerWriter.class);
	private BeanDataHolder beanDataHolder;
	
	@Override
	public void write(List<? extends Integer> numbers) throws Exception {
		
		log.info("numbers da salvare in transazione: "+numbers.toString());
		
		Integer lastProcessed=null;
		
		for (Integer integer : numbers){
			log.debug(integer);	
			lastProcessed=new Integer(integer.intValue());
		}
		
		saveInBeanHolder(lastProcessed);		
		log.info("committed "+numbers.size()+" records");
		
	}

	private void saveInBeanHolder(Integer valueToSave){		
		beanDataHolder.getLargeDataCollection().put(Consts.VALUE_KEY, valueToSave);	
	}

	public BeanDataHolder getBeanDataHolder() {
		return beanDataHolder;
	}

	public void setBeanDataHolder(BeanDataHolder beanDataHolder) {
		this.beanDataHolder = beanDataHolder;
	}
	
	
	
	

}
