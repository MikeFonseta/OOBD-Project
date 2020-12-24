package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Entities.Account;
import Entities.Sede;

public class SedeDAOPostgresImp implements SedeDAO{

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
				String citt‡ = rs.getString(5);
				String via = rs.getString(6);
				String numCivico = rs.getString(7);
				
				sede = new Sede(idSede,nomeSede,telefonoSede,provincia,citt‡,via,numCivico);
				
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
				String citt‡ = rs.getString(5);
				String via = rs.getString(6);
				String numCivico = rs.getString(7);
				
				Object[] object = new Object[] {idSede,nomeSede,"(" + provincia + ") " + citt‡ + " - " + via + " [" + numCivico +"]" , telefonoSede};
				
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
	
	public int aggiungiProdottoASede(String idSede,int idProdotto) {
		
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO men√π VALUES ('"+idSede + "'," + idProdotto +")");
				
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return 1;
		
	}

	@Override
	public int EliminaProdottoDaSede(String idSede, int idProdotto) {
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			st.executeUpdate("DELETE FROM men√π WHERE id_sede='"+idSede + "' AND id_prodotto='" + idProdotto +"'");
				
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return 1;
	}


	
	

}
