package corso.spring.batch.demo.modulo.exceptions.listeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListenerLogImpl implements ChunkListener{

	final static Logger log = Logger.getLogger(ChunkListenerLogImpl.class);

	@Override
	public void beforeChunk(ChunkContext context) {
		log.info("beforeChunk");
	}

	@Override
	public void afterChunk(ChunkContext context) {
		log.info("afterChunk");
		
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		log.info("afterChunkError");		
	}

}
