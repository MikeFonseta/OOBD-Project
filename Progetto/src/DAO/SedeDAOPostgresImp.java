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
			ResultSet rs = st.executeQuery("SELECT * FROM sedi WHERE id_sede=" + id);	
			
			if(rs.next()){
				
				String idSede = rs.getString(1);
				String nomeSede= rs.getString(2);
				String indirizzoSede = rs.getString(3);
				String telefonoSede = rs.getString(4);
				String provincia = rs.getString(4);
				String città = rs.getString(6);
				String via = rs.getString(7);
				String numCivico = rs.getString(8);
				
				sede = new Sede(idSede,nomeSede,indirizzoSede,telefonoSede,provincia,città,via,numCivico);
				
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			// TODO Auto-generated catch block
			//e.printStackTrace();	
		}
		
		return sede;
	}

	@Override
	public List<Sede> getSedi() {
		
		List<Sede> sedi = new ArrayList<Sede>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM sedi");	
			
			while(rs.next()) {
				
				String idSede = rs.getString(1);
				String nomeSede= rs.getString(2);
				String indirizzoSede = rs.getString(3);
				String telefonoSede = rs.getString(4);
				String provincia = rs.getString(4);
				String città = rs.getString(6);
				String via = rs.getString(7);
				String numCivico = rs.getString(8);
				
				Sede s = new Sede(idSede,nomeSede,indirizzoSede,telefonoSede,provincia,città,via,numCivico);
				sedi.add(s);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			// TODO Auto-generated catch block
			//e.printStackTrace();	
		}
		
		return sedi;
	}
	
	

}
