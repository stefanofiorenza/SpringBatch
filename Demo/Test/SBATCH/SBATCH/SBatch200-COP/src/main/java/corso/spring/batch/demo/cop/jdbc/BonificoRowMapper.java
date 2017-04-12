package corso.spring.batch.demo.cop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import corso.spring.batch.demo.commons.dto.BonificoDto;

public class BonificoRowMapper implements RowMapper<BonificoDto>{

	
	
	@Override
	public BonificoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BonificoDto bonifico= new BonificoDto();
		bonifico.setAmount(rs.getBigDecimal("amount"));
		bonifico.setBeneficiary(rs.getString("beneficiary"));	
		bonifico.setDate(rs.getString("date"));
		bonifico.setDescription(rs.getString("description"));
		bonifico.setIbanBeneficiary(rs.getString("ibanBeneficiary"));
		bonifico.setIbanPayer(rs.getString("ibanPayer"));
		bonifico.setOrderId(rs.getString("orderId"));
		bonifico.setPayer(rs.getString("payer"));
		bonifico.setReferenceNumber(rs.getString("referenceNumber"));
		return bonifico;
	}

}
