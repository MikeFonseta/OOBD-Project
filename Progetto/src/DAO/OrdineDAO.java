package DAO;

import java.sql.SQLException;
import java.util.List;

public interface OrdineDAO {
	List<Object[]> ricercaComplessaOrdini(String IDSede, String Prodotti, String Veicolo, Integer Min, Integer Max);

	void IniziaConsegna(Integer idOrdine);
	void TerminaConsegna(Integer idOrdine);
	void CancellaOrdine(Integer idOrdine);
	public List<Object[]> getOrdiniTabella() throws SQLException;

	
	

}


