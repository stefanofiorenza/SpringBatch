package corso.spring.batch.demo.modulo.basic.job.io;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.modulo.basic.job.io.model.User;




public class UserProcessor implements ItemProcessor<User, User>{

	@Override 
	public User process(User user) throws Exception {
		// TODO Auto-generated method stub
		
		//ExceptionDemoUtils.throwSkippableException("Eccezione nel processor");
		
		user.setEmail("TrasformatoInFileProcessor");
		return user;
	}

}
