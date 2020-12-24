package DAO;

import java.util.List;

import Entities.Sede;

public interface SedeDAO {
	
	public Sede CercaSedePerId(String id);
	public List<Object[]> getSedi();
	public int aggiungiProdottoASede(String idSede,int idProdotto);
	public int EliminaProdottoDaSede(String idSede, int idProdotto);
	public int EliminaSede(String idSede);
}
