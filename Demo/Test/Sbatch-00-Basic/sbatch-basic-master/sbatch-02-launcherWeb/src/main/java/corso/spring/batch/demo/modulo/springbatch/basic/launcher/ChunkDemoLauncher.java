package corso.spring.batch.demo.modulo.springbatch.basic.launcher;

import java.util.Date;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class ChunkDemoLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			
		//	CommandLineJobRunner runner = new CommandLineJobRunner();
			
			//String configurationFilePath="corso/spring/batch/demo/modulo/springbatch/basic/config/Basic-IO-SpringBatch.xml";
			String configurationFilePath="Basic-IO-SpringBatch.xml";			
			String nomeJob="myChunkProcessingSampleJob";
					
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
			CommandLineJobRunner.main(mainArguments);
			
		}catch (Exception e){
			e.printStackTrace();
		}


	}

}
