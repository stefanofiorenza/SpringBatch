package corso.spring.batch.demo.modulo.stato.job.service;

import java.util.HashMap;
import java.util.Map;

public class BeanDataHolderImpl implements BeanDataHolder{

	private Map<String,Integer> largeDataCollection=new HashMap<>();

	public Map<String, Integer> getLargeDataCollection() {
		return largeDataCollection;
	}

}
