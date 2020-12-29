package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Account;
import Entities.Sede;

public interface SedeDAO {
	
	public List<String> CercaTutteLeSedi() throws SQLException;
	public Sede CercaSedePerId(int id) throws SQLException;
	public List<Object[]> getSedi() throws SQLException;
	public int aggiungiProdottoASede(int idSede,int idProdotto) throws SQLException;
	public int EliminaProdottoDaSede(int idSede, int idProdotto) throws SQLException;
	public int EliminaSede(int idSede) throws SQLException;
	public int CreaSede(Sede sede,String nomeUtente, String password) throws SQLException;
	public int AggiornaSede(Sede sede, Account gestoreSede, String nuovaPassword) throws SQLException;
	public int NextIdSede() throws SQLException;
}
