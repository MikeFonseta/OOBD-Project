package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import Database.DBConnection;
import Entities.Sede;

public class SedeDAOPostgresImp implements SedeDAO{
	
	
	@Override
	public List<String> CercaTutteLeSedi() {
		List<String> sedi = new ArrayList<String>();
		Connection conn = null;
		
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT ID_Sede FROM Sede");
			
			while(rs.next()) 
				sedi.add(rs.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		  }
		return sedi;
	}

	
	@Override
	public Sede CercaSedePerId(String id) {
		
		Sede sede = null;
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM sede WHERE id_sede='" + id +"'");	
			
			if(rs.next()){
				
				String idSede = rs.getString(1);
				String nomeSede= rs.getString(2);
				String telefonoSede = rs.getString(3);
				String provincia = rs.getString(4);
				String citta = rs.getString(5);
				String via = rs.getString(6);
				String numCivico = rs.getString(7);
				
				sede = new Sede(idSede,nomeSede,telefonoSede,provincia,citta,via,numCivico);
				
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return sede;
	}

	@Override
	public List<Object[]> getSedi() {
		
		List<Object[]> sedi = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM sede");
			while(rs.next()) {
				
				String idSede = rs.getString(1);
				String nomeSede= rs.getString(2);
				String telefonoSede = rs.getString(3);
				String provincia = rs.getString(4);
				String citta = rs.getString(5);
				String via = rs.getString(6);
				String numCivico = rs.getString(7);
				
				Object[] object = new Object[] {idSede,nomeSede,"(" + provincia + ") " + citta + " - " + via + " [" + numCivico +"]" , telefonoSede};
				
				sedi.add(object);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return sedi;
	}
	
	@Override
	public int aggiungiProdottoASede(String idSede, int idProdotto) {
		int ris = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ris = st.executeUpdate("INSERT INTO menù VALUES ('"+idSede + "'," + idProdotto +")");
				
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return ris;
	}

	@Override
	public int EliminaProdottoDaSede(String idSede, int idProdotto) {

		int ris = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ris = st.executeUpdate("DELETE FROM menù WHERE id_sede='"+idSede + "' AND id_prodotto='" + idProdotto +"'");
				
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return ris;
	}


	@Override
	public int EliminaSede(String idSede) {
		int ris = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ris = st.executeUpdate("DELETE FROM sede WHERE id_sede='"+idSede + "'");
				
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return ris;
	}


}
