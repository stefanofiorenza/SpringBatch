package corso.spring.batch.demo.springbatch2.web.chunk;

import org.springframework.batch.item.ItemProcessor;

import corso.spring.batch.demo.springbatch2.web.chunk.model.User;



public class UserProcessor implements ItemProcessor<User, User>{

	@Override 
	public User process(User user) throws Exception {
		// TODO Auto-generated method stub
		
		user.setEmail("TrasformatoInFileProcessor");
		return user;
	}

}
