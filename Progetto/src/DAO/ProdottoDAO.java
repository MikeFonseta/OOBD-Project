package DAO;

import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO {
	
	public List<Object[]> getTuttiProdotti() throws SQLException; 
	public List<Object[]> getProdottiDellaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiPerUnaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiSedeCategoria(int idSede,String categoria) throws SQLException;
}
