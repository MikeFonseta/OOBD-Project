package DAO;

import java.util.List;

public interface OrdineDAO {
	List<Object[]> ricercaComplessaOrdini(String IDSede, String Prodotti, String Veicolo, Integer Min, Integer Max);
}
