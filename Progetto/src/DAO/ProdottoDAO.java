package DAO;

import java.util.List;

public interface ProdottoDAO {
	
	public List<Object[]> getTuttiProdotti(); 
	public List<Object[]> getProdottiDellaSede(String idSede);
	public List<Object[]> getProdottiPerUnaSede(String idSede);
}
