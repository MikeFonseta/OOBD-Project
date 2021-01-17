package DAO;

import java.sql.SQLException;

public interface MenuDao {
	public int aggiungiProdottoASede(int idSede,int idProdotto) throws SQLException;
	public int EliminaProdottoDaSede(int idSede, int idProdotto) throws SQLException;
}
