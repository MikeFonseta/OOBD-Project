package DAO;

import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO {
	public List<Integer> getTuttiProdottiPerNome(String[] Prodotti)throws SQLException;
	public List<Object[]> getTuttiProdotti() throws SQLException; 
	public List<Object[]> getProdottiDellaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiPerUnaSede(int idSede) throws SQLException;
	public List<Object[]> getProdottiSedeCategoria(int idSede,String categoria) throws SQLException;
	public List<String> getCategorieProdotto() throws SQLException;
	public List<Object[]> getProdottiTabella(String categoria) throws SQLException;
	public List<Object[]> getProdottiPerId_Ordine(int idOrdine) throws SQLException;
	public int eliminaProdottoDaTutteLeSedi(int idProdotto) throws SQLException;
	public List<Integer> getProdottiPerAllergeni(String[] NomiAllergeni) throws SQLException;	
	List<Object[]> ricercaComplessaProdotti(String Categoria, Integer Min, Integer Max, List<Integer> idProdottiConAllergeni) throws SQLException;
}

