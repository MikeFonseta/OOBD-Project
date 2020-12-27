package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Sede;

public interface SedeDAO {
	
	public List<String> CercaTutteLeSedi() throws SQLException;
	public Sede CercaSedePerId(String id) throws SQLException;
	public List<Object[]> getSedi() throws SQLException;
	public int aggiungiProdottoASede(String idSede,int idProdotto) throws SQLException;
	public int EliminaProdottoDaSede(String idSede, int idProdotto) throws SQLException;
	public int EliminaSede(String idSede) throws SQLException;
}
