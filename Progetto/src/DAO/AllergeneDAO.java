package DAO;

import java.sql.SQLException;
import java.util.List;

public interface AllergeneDAO {
	
	public List<String[]> getAllergeniMancanti(int idProdotto) throws SQLException;
	
}
