package corso.spring.batch.lab2.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public class BonificoInsertPsSetter  implements ItemPreparedStatementSetter<BonificoDto> {
	
	@Override
	public void setValues(BonificoDto item, PreparedStatement ps)
			throws SQLException {
		
		ps.setString(1,item.getOrderId());
		ps.setString(2,item.getPayer());
		ps.setString(3,item.getIbanPayer());
		ps.setString(4,item.getBeneficiary());
		ps.setString(5,item.getIbanBeneficiary());
		ps.setDouble(6, item.getAmount().doubleValue());
		ps.setString(7,item.getDate());
		ps.setString(8,item.getDescription());
		ps.setString(9,item.getReferenceNumber());																				
	}	
	
}


