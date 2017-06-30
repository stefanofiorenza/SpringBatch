package corso.spring.batch.demo.cop.test;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;

import corso.spring.batch.demo.commons.test.AbstractDemoTest;


public class SBatch200CommonTestUtils {

	
	protected static final String DEFAULT_JDBC_JOB_PARAMS=toJobParams(false,false,false,false,false,false);
	
	protected static final  String TRANSACTION_DEMO_JOB_PARAMS_PATTERN="procExceptionRetryable=%s, procExceptionRuntime=%s, procExceptionRollback=%s ,  "+
			"writerExceptionRetryable=%s, writerExceptionRuntime=%s, writerExceptionRollback=%s";
	
	protected static String toJobParams(boolean procRetryException, boolean procRuntimeException, boolean procNoRollbackException, 
							boolean writerRetryException, boolean writerRuntimeException, boolean writerNoRollbackException){
		
		return String.format(TRANSACTION_DEMO_JOB_PARAMS_PATTERN,procRetryException,procRuntimeException,procNoRollbackException,
				writerRetryException, writerRuntimeException, writerNoRollbackException);		
	}
	
	
	
	
}
