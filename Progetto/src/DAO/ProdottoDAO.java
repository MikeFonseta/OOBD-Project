package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Prodotto;

public interface ProdottoDAO {
	public Prodotto getProdottoPerId(int idProdotto) throws SQLException;
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
	public String[] getDatiSingoloProdotto(int idProdotto) throws SQLException;
	public List<Object[]> ricercaComplessaProdotti(String Categoria, Integer Min, Integer Max, List<Integer> idProdottiConAllergeni) throws SQLException;
	public int idProssimoProdotto() throws SQLException;
	public int CreaProdotto(int idProssimoProdotto, String nome, String descrizione, float prezzo, String categoria) throws SQLException;
	public int AggiornaProdotto(Prodotto prodotto) throws SQLException;
}

