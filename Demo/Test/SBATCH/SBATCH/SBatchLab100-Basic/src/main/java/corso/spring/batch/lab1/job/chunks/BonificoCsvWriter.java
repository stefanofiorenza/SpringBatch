package corso.spring.batch.lab1.job.chunks;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.item.ItemWriter;

import corso.spring.batch.demo.commons.dto.BonificoDto;
import corso.spring.batch.lab1.utils.Consts;
import corso.spring.batch.lab1.utils.LabUtils;

@Slf4j
public class BonificoCsvWriter implements ItemWriter<BonificoDto>{

	@Override
	public void write(List<? extends BonificoDto> items) throws Exception {

		for (BonificoDto bonifico : items){
			String dirPath = Consts.REPORT_FULL_PATH+"/"+bonifico.getIbanState();
			String filePath="/Order-"+bonifico.getOrderId()+".csv";
			if(LabUtils.dirExists(dirPath)){
				String writeToPath=dirPath+filePath;
				log.info("Writing to Path: "+writeToPath);
				LabUtils.writeBonifico(writeToPath, bonifico);
			}else{
				log.warn("Skipping bonifico#"+bonifico.getOrderId()+" Because "+dirPath+" is not available");
			}
						
		}
		
	}

}
