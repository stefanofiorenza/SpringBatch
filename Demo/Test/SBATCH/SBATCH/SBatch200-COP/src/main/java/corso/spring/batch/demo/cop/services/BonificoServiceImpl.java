package corso.spring.batch.demo.cop.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.log4j.Log4j;

import org.springframework.stereotype.Service;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.demo.cop.common.BonificoDtoUtils;

@Service
@Log4j
public class BonificoServiceImpl implements BonificoService {

	private static int counter=0;
	
	
	public List<BonificoDto> getBonificiLastDay() {

		List<BonificoDto> receivedFromMockDb = new ArrayList<BonificoDto>();
		
		if(counter==500){
			return null;
		}
		
		for (int i=0; i<50; i++){
			counter++;			
			BonificoDto bonifico= BonificoDtoUtils.createMockBonifico(counter);
			receivedFromMockDb.add(bonifico);
		}
		log.info("Returned "+receivedFromMockDb.size()+" results "+
				" from OrderID: "+receivedFromMockDb.get(0).getOrderId()
				+" to orderId: "+receivedFromMockDb.get(receivedFromMockDb.size()-1).getOrderId());
		
		return receivedFromMockDb;
	}

	@Override
	public void updateBonifici(List<BonificoDto> bonifici) {
		log.info("Write to DB:  "+bonifici.size()+" processed bonifici"+
				" from OrderID: "+bonifici.get(0).getOrderId()
				+" to orderId: "+bonifici.get(bonifici.size()-1).getOrderId());		
	}

	@Override
	public List<BonificoDto> processBonifico(List<BonificoDto> received) {
		log.info("Processed:  "+received.size()+" bonifici"+
				" from OrderID: "+received.get(0).getOrderId()
				+" to orderId: "+received.get(received.size()-1).getOrderId());	
		return received;
	}



}
