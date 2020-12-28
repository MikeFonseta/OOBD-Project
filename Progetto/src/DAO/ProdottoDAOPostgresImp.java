package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		List<Integer> ris = new ArrayList<>();
		Connection connection = DBConnection.getInstance().getConnection();
		Statement st = null;
		ResultSet rs = null;
		while(i < Prodotti.length && isEmpty == false) {
		String query = "Select ID_Prodotto FROM Prodotto WHERE NomeP LIKE '%"+Prodotti[i]+"%'";
		st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			       ResultSet.CONCUR_READ_ONLY);
		rs = st.executeQuery(query);
		if(rs.next()==false) isEmpty = true;
		rs.beforeFirst();
		while(rs.next()) {
			ris.add(rs.getInt(1));
			
		}
		i=i+1;
		}
		
		if(isEmpty==true) ris = null ;
		rs.close();
		st.close();
		connection.close();
	return ris;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
