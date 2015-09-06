package corso.spring.batch.demo.springbatch2.web.chunk;

import java.io.File;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import corso.spring.batch.demo.springbatch2.web.chunk.model.User;


public class UserReader implements ItemReader<User>{

	private static int contatore=0;
	
	@Override
	public User read() throws Exception, UnexpectedInputException, ParseException,
			NonTransientResourceException {
		
		
		contatore++;
		
		//supponiamo sia stato letto da un sistema qualsiasi
		//invece che creato a mano...
		User utente= new User();
		utente.setNome("nome"+contatore);
		utente.setCognome("cognome"+contatore);
		utente.setEmail("email"+contatore);
		utente.setTelefono("1234567890"+contatore);
				
		return utente;
	}

}
