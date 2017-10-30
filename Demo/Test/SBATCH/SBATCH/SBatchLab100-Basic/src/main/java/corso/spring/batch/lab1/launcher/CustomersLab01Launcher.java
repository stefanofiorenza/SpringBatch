package corso.spring.batch.lab1.launcher;

import corso.spring.batch.demo.commons.utils.SbatchJobUtils;

public class CustomersLab01Launcher {

	public static void main(String[] args) {
		SbatchJobUtils.startJobFromCommandLine("SBatchLab100-Basic-Batch.xml", "SB-Lab100");
	}

}
