package corso.spring.batch.demo.modulo.springbatch.basic.job.io;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.modulo.springbatch.basic.job.io.model.User;



public class UserWriter implements ItemWriter<User>{

	@Override
	public void write(List<? extends User> utenti) throws Exception {
		
		System.out.println("Utenti da salvare in transazione:");
		
		for (User utente : utenti){
			System.out.println(utente);
		}
		
		System.out.println("commit");
		
	}

	
	
	
	

}
