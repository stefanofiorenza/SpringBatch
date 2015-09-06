package corso.spring.batch.demo.modulo.springbatch.basic.launcher;

import java.util.Date;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.CommandLineJobRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskletDemoLauncherMain {

	public static void main(String [] args){
		
		try{
			
		
		CommandLineJobRunner runner = new CommandLineJobRunner();
		
		String configurationFilePath="corso/spring/batch/demo/modulo/springbatch/basic/config/Basic-Tasklet-SpringBatch.xml";
		
		String nomeJob="myTaskletSampleJob";
		
				
		//modificare esecuzioneNumero=? per ripetere l'esecuzione
		//String optParameter="esecuzioneNumero=91";
		
		Date adesso = new Date();
		String optParameter="dataEsecuzione="+adesso;
		
		String [] mainArguments={
				configurationFilePath,
				nomeJob,
				optParameter
		};
		
			// codice cablato di esecuzione da riga di comando..
			runner.main(mainArguments);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
