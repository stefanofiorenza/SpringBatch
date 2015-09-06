package corso.spring.batch.demo.modulo.springbatch.basic.job.io;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.modulo.springbatch.basic.job.io.model.User;




public class UserProcessor implements ItemProcessor<User, User>{

	@Override 
	public User process(User user) throws Exception {
		// TODO Auto-generated method stub
		
		user.setEmail("TrasformatoInFileProcessor");
		return user;
	}

}
