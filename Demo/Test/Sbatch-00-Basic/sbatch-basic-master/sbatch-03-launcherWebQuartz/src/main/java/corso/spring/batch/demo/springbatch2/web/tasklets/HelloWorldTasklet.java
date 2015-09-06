package corso.spring.batch.demo.springbatch2.web.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import corso.spring.batch.demo.springbatch2.web.service.ServizioPresistente;

public class HelloWorldTasklet implements Tasklet{

	private ServizioPresistente servizio;
		
	public ServizioPresistente getServizio() {
		return servizio;
	}

	public void setServizio(ServizioPresistente servizio) {
		this.servizio = servizio;
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[HelloWorldTasklet]: Ciao Mondo!!");
		servizio.unProcesso("Messaggio per servizio");
		return RepeatStatus.FINISHED;
	}

}
