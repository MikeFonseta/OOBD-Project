package DAO;

import java.sql.SQLException;
import java.util.List;

public interface EtichettaDAO {
	
	public List<String[]> getAllergeniProdotto(int idProdotto) throws SQLException;
	public int AggiungiAllergeneAProdotto(int idProdotto, String Allergene) throws SQLException;
	public int EliminaAllergeneDaProdotto(int idProdotto, String Allergene) throws SQLException;
	
}