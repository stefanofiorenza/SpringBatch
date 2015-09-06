package corso.spring.batch.demo.springbatch2.web.service;

public class ServizioPresistente {

	public void unProcesso(String message) {
		System.out.println("[ServizioPresistente]: esecuzione processo Spring BATCH: "+message);
	}
}
