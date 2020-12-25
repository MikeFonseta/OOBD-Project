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
	public List<Object[]> getTuttiProdotti() {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT id_prodotto,nomep,descrizione,prezzo FROM prodotto");
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
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return prodotti;
	}
	
	public List<Object[]> getProdottiPerUnaSede(String idSede) {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menù WHERE id_sede='" + idSede + "')");
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
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return prodotti;
	}

	@Override
	public List<Object[]> getProdottiDellaSede(String idSede) {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto IN (SELECT id_prodotto FROM menù WHERE id_sede='" + idSede + "')");
			
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
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiSedeCategoria(String idSede, String categoria) {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM menÃ¹ WHERE id_sede='" + idSede + "') AND P.categoria='" + categoria + "'");
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
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return prodotti;
	}

}
