package DAO;

import java.sql.SQLException;
import java.util.List;

public interface OrdineDAO {
	
	List<Object[]> ricercaComplessaOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) throws SQLException;
	void IniziaConsegna(Integer idRider,boolean annulla);
	void TerminaConsegna(Integer idRider);
    void CancellaOrdine(Integer idOrdine);
    public void CancellaCodiceRider(Integer idRider) ;
	public List<Object[]> getOrdiniTabella(int idSede) throws SQLException;
	public List<Object[]> getOrdiniFiltroRider(int idRider) throws SQLException;
	public int CreaOrdine(float totale,int idRider,int idSede) throws SQLException;
	public void CreaCompOrdine(List<int[]> prodotti,int idNuovoOrdine) throws SQLException;
	public void CreaInfoOrdine(int idOrdine,int idCliente, String citta, String via, String civico, String telefono, String provincia) throws SQLException;
	
}


