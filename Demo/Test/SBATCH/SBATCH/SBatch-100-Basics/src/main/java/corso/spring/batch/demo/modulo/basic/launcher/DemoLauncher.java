package corso.spring.batch.demo.modulo.basic.launcher;

import java.util.Date;

import org.junit.Test;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;

import corso.spring.batch.demo.commons.utils.SbatchJobUtils;

public class DemoLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//demoBasicTasklet();
		//demoBasicChunkOrientedProcessing();
		demoCOPWithListeners();
	}

	
//	@Test
	public static void demoBasicTasklet(){
		SbatchJobUtils.startJobFromCommandLine(Configs.TASKLET_CFG_01_BASIC, Configs.TASKET_JOB_NAME_BASIC);
	}
	
	//@Test
	public static void demoBasicChunkOrientedProcessing(){
		SbatchJobUtils.startJobFromCommandLine(Configs.COP_CFG_01_BASIC, Configs.CHUNCK_JOB_NAME_BASIC);
	}
	
	//@Test
	public static void demoCOPWithListeners(){
		SbatchJobUtils.startJobFromCommandLine(Configs.COP_CFG_02_LISTENERS,Configs.CHUNCK_JOB_NAME_LISTENERS);
	}
}
