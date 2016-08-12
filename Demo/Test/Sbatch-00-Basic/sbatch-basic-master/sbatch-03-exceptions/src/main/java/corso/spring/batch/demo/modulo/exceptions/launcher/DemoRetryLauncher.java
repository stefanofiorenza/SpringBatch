package corso.spring.batch.demo.modulo.exceptions.launcher;

import java.util.Date;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class DemoRetryLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			
			//
			// Configs.CHUNCK_PROCESSING_01_BASIC;
			
			//1) Chunk Processing
		//	String nomeJob=Configs.CHUNCK_JOB_NAME;
		//	String configurationFilePath=Configs.CHUNCK_PROCESSING_01_BASIC;
			
			//2) Tasklet
			String nomeJob=Configs.TASKET_JOB_NAME;
			String configurationFilePath=Configs.TASKLET_01_BASIC;			
			
			
			
			Date adesso = new Date();
			String optParameter="dataEsecuzione="+adesso;
			
			String [] mainArguments={
					configurationFilePath,
					nomeJob,
					optParameter
			};
			
			// codice cablato di esecuzione da riga di comando..
			CommandLineJobRunner.main(mainArguments);
			
		}catch (Exception e){
			e.printStackTrace();
		}


	}

}
