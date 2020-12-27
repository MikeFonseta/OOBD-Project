package DAO;

import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO {
	
	public List<Object[]> getTuttiProdotti() throws SQLException; 
	public List<Object[]> getProdottiDellaSede(String idSede) throws SQLException;
	public List<Object[]> getProdottiPerUnaSede(String idSede) throws SQLException;
	public List<Object[]> getProdottiSedeCategoria(String idSede,String categoria) throws SQLException;
}
