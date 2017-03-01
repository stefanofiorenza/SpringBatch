package corso.spring.batch.demo.cop.test;

import java.util.Date;

import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class DemoFlatFileLauncher {

	public static void main(String[] args) {
	try{
	
			String nomeJob="copDemoSampleJob";
			String configurationFilePath="COP-Demo.xml";
			String workingDir="C:/Temp/sbatch/cop/";
					
			Date adesso = new Date();
			String optParameter="dataEsecuzione="+adesso;
			
			//String fileIn="inputFile="+workingDir+"payments.csv";
			String fileOut="outputFile="+workingDir+"copied-payments.csv";
			
			String [] mainArguments={
					configurationFilePath,
					nomeJob,
					optParameter,
	//				fileIn,
					fileOut
			};
			
			// codice cablato di esecuzione da riga di comando..
			CommandLineJobRunner.main(mainArguments);
			
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
