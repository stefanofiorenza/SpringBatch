package corso.spring.batch.demo.modulo.springbatch.basic.job.service;

public class ServizioPresistente {

	public void unProcesso(String message) {
		System.out.println("[ServizioPresistente]: esecuzione processo Spring BATCH: "+message);
	}
}
