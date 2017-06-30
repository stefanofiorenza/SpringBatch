package corso.spring.batch.demo.cop.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.batch.core.BatchStatus;

import corso.spring.batch.demo.commons.test.AbstractDemoTest;


//@Ignore
public class SBatch200CopExceptionTest extends AbstractDemoTest{

	/**Da eseguire manualmente */
		
	//@Test
	public void testCopy_Transactions_Exc_Retry_Processor()throws Exception{			
		testTemplateException(true, false, false, false, false, false,BatchStatus.FAILED);		
	}
		
	//@Test
	public void testCopy_Transactions_Exc_Retry_Writer()throws Exception{			
		testTemplateException(false, false, false, true, false, false,BatchStatus.FAILED);		
	}
		
	@Test
	public void testCopy_Transactions_Exc_Runtime_Processor()throws Exception{			
		testTemplateException(false, true, false, false, false, false, BatchStatus.FAILED);		
	}
	
	@Test
	public void testCopy_Transactions_Exc_Runtime_Writer()throws Exception{			
		testTemplateException(false, false, false, false, true, false,BatchStatus.FAILED);			
	}
	
	/*Devono essere skippable oltre che No Rollback. Problema skip limit */ 
	@Test
	public void testCopy_Transactions_Exc_NoRollback_Processor()throws Exception{			
		testTemplateException(false, false, true, false, false, false,BatchStatus.FAILED);		
	}
	
	/*Se No Rollback && skippable. Problema skip limit */ 
	@Test
	public void testCopy_Transactions_Exc_NoRollback_Writer()throws Exception{			
		testTemplateException(false, false, false, false, false, true,BatchStatus.COMPLETED);		
	}
	
	private void testTemplateException(boolean procRetryException, boolean procRuntimeException, boolean procNoRollbackException, 
			boolean writerRetryException, boolean writerRuntimeException, boolean writerNoRollbackException,BatchStatus batchStatus) throws Exception{
		
		String jobParams=SBatch200CommonTestUtils.toJobParams(procRetryException, procRuntimeException, procNoRollbackException, 
				writerRetryException, writerRuntimeException, writerNoRollbackException);
		
		testTemplate(Configs.CFG_04_JDBC_PAGINATION, Configs.JOB_02_EXC,jobParams,false,1, 1, batchStatus);
	}
	

	
	
	
	
}
