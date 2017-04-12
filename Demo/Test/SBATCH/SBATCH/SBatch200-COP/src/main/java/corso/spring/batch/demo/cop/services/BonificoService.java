package corso.spring.batch.demo.cop.services;

import java.util.List;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public interface BonificoService {

	List<BonificoDto>  getBonificiLastDay();
	
	List<BonificoDto>  processBonifico(List<BonificoDto>  received );
	
	//void updateBonifici(List<? extends BonificoDto> bonifici);
	void updateBonifici(List<BonificoDto>  bonifici);
}
