package DAO;

import java.sql.SQLException;
import java.util.List;

public interface MenuDAO {
	public int aggiungiProdottoASede(int idSede,int idProdotto) throws SQLException;
	public int EliminaProdottoDaSede(int idSede, int idProdotto) throws SQLException;
	public List<Object[]> getProdottiDellaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiPerUnaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiSedeCategoria(int idSede,String categoria) throws SQLException;
}
