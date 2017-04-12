package corso.spring.batch.demo.cop.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class BonificoQueryByAmountPsSetter implements PreparedStatementSetter{

	@Getter
	@Setter
	private Double amount;
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setDouble(1, amount);
		
	}

}
