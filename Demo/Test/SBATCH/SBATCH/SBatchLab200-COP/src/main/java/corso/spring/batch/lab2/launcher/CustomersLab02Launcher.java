package corso.spring.batch.lab2.launcher;

import corso.spring.batch.demo.commons.utils.SbatchJobUtils;

public class CustomersLab02Launcher {

	public static void main(String[] args) {
		SbatchJobUtils.startJobFromCommandLine("SBatchLab200-COP-Batch.xml", "SB-Lab200");
	}

}
