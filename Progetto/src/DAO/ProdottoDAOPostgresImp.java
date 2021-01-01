package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Database.DBConnection;

public class ProdottoDAOPostgresImp implements ProdottoDAO{

	@Override
	public List<Object[]> getTuttiProdotti() throws SQLException  {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_prodotto,nomep,descrizione,prezzo FROM prodotto ORDER BY id_prodotto ASC");
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
			
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
			
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiPerUnaSede(int idSede)  throws SQLException {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
		
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
			
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}

	@Override
	public List<Object[]> getProdottiDellaSede(int idSede)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
			
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			float prezzo = rs.getFloat(3);
			String categoria = rs.getString(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,categoria,prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiSedeCategoria(int idSede, String categoria)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menù WHERE id_sede=" + idSede + ") AND P.categoria='" + categoria + "' ORDER BY P.id_prodotto ASC");
		
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
				
			prodotti.add(object);
			}
				
		rs.close();
		st.close();
		conn.close();
			
		return prodotti;
	}

	
	
	
	
	@Override
	public List<Integer> getTuttiProdottiPerNome(String[] Prodotti) throws SQLException {
		int i =0; boolean isEmpty = false;
		List<Integer> result = new ArrayList<>();
		Connection connection = DBConnection.getInstance().getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		for(int j = 0;j<Prodotti.length;j++)
			Prodotti[j] = Prodotti[j].toLowerCase(Locale.ROOT);
		
		while(i < Prodotti.length && isEmpty == false) {
			isEmpty = true;																						//no case-sensitive
			String query = "SELECT ID_Prodotto FROM  Ordine NATURAL JOIN CompOrdine NATURAL JOIN Prodotto WHERE LOWER(NomeP) LIKE '"+Prodotti[i]+"%'";
			st = connection.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				isEmpty = false;
				Integer temp = rs.getInt(1);
				if(!result.contains(temp)) result.add(temp);									//elimina duplicati
			}
			i=i+1;
		}
		
		//se uno dei parametri non ha nessun riscontro la ricerca salta
		if(isEmpty==true) {
		result.clear();
		result.add(0,-1);
		}		

		
		rs.close();
		st.close();
		connection.close();
	return result;
	
	}
	
	
	@Override
	public List<String> getCategorie() throws SQLException{
		List<String> categorie = new ArrayList<String>();
		categorie.add("Tutte");
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT categoria FROM prodotto GROUP BY categoria");
		
		while(rs.next()) {
			categorie.add(rs.getString(1));
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return categorie;
	}
	
	@Override
	public List<Object[]> getProdottiTabella(String categoria) throws SQLException {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		String filtro = "";
		if(!categoria.equals("Tutte")) {
			filtro = " WHERE categoria = '"+categoria+"' ";
		}
		
		ResultSet rs = st.executeQuery("SELECT nomep AS Nome, prezzo AS Prezzo "
				+ "FROM prodotto "
				+ filtro
				+ "ORDER BY Nome ASC");
		
		while(rs.next()) {
				
			String Nome= rs.getString(1);
			
			float Prezzo = rs.getFloat(2);
			String PrezzoEuro= "�"+String.valueOf(Prezzo);
			
			Object[] object = new Object[] {Nome,PrezzoEuro};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}
	
	
	
	
	
	
	
	
	
	
	
}
