package corso.spring.batch.demo.modulo.exceptions.test;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import corso.spring.batch.demo.commons.test.AbstractDemoTest;


public class SBatch170ExceptionsTest extends AbstractDemoTest{


	
	@Test
	public void testCOP_Exceptions()throws Exception{		
		testTemplate(Configs.CHUNCK_PROCESSING_01_BASIC, Configs.CHUNCK_JOB_NAME, 1, 1, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testTasklet_Exceptions() throws Exception{
		testTemplate(Configs.TASKLET_01_BASIC,Configs.TASKET_JOB_NAME, 1, 1, BatchStatus.COMPLETED);
	}
	
}
