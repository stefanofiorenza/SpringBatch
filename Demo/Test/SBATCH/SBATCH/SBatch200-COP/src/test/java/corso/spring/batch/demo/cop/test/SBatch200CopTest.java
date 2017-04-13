package corso.spring.batch.demo.cop.test;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;

import corso.spring.batch.demo.commons.test.AbstractDemoTest;


public class SBatch200CopTest extends AbstractDemoTest{

	
	@Test
	public void testCopy_FlatFiles()throws Exception{			
		String jobParameters="inputFile="+Paths.CSV_IN+",outputFile="+ Paths.CSV_OUT;
		testTemplate(Configs.CFG_01_FLATFILE, Configs.JOB_01_BASIC, jobParameters,false,1, 1, BatchStatus.COMPLETED);
	}
	
	
	@Test
	public void testCopy_Xml()throws Exception{			
		String jobParameters="inputFile="+Paths.XML_IN+",outputFile="+ Paths.XML_OUT;
		testTemplate(Configs.CFG_02_XML, Configs.JOB_01_BASIC, jobParameters,false,1, 1, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testCopy_Jdbc_Cursor()throws Exception{			
		testTemplate(Configs.CFG_03_JDBC_CURSOR, Configs.JOB_01_BASIC,1, 1, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testCopy_Jdbc_Pagination()throws Exception{				
		testTemplate(Configs.CFG_04_JDBC_PAGINATION, Configs.JOB_01_BASIC,1, 1, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testCopy_Adapters()throws Exception{			
		testTemplate(Configs.CFG_05_ADAPTERS, Configs.JOB_01_BASIC,1, 1, BatchStatus.COMPLETED);
	}
	
	@Test
	public void testCopy_ChainedProcessors()throws Exception{			
		String jobParameters="inputFile="+Paths.CSV_IN+",outputFile="+ Paths.CSV_OUT;
		testTemplate(Configs.CFG_06_CHAINED_PROCESSORS, Configs.JOB_01_BASIC,jobParameters,false,1, 1, BatchStatus.COMPLETED);
	}
	
}
