package corso.spring.batch.demo.modulo.stato.test;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;

import corso.spring.batch.demo.commons.test.AbstractDemoTest;

public class SBatch130StatusTest extends AbstractDemoTest{

	
	@Test
	public void testTaskletExecutionContext()throws Exception{		
		testTemplate(Configs.TASKLET_01_CONTEXTS, Configs.TASKET_JOB_01, 1, 2, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testTaskletBeanHolder() throws Exception{
		testTemplate(Configs.TASKLET_02_BEAN_HOLDER, Configs.TASKET_JOB_02, 1, 2, BatchStatus.COMPLETED);
	}
		
	@Test
	public void testCopExecutionContext() throws Exception{	
		testTemplate(Configs.COP_CFG_01_CONTEXTS, Configs.COP_JOB_01, 1, 2, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testCopBeanHolder() throws Exception{
		testTemplate(Configs.COP_CFG_02_BEAN_HOLDER, Configs.COP_JOB_02, 1, 2, BatchStatus.COMPLETED);
	}
	

	
}
