package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import Entities.Rider;
import Entities.Sede;

public class RiderDAOPostgresImp implements RiderDAO{

	@Override
	public Rider CercaRiderPerId(int idRider) {
		
		Rider rider = new Rider();
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT R.nomer,R.cognomer,R.telefonor,R.veicolo,R.disponibilità FROM rider AS R WHERE id_rider=" + idRider +"");	
			
			if(rs.next()){
				
				String nomeRider= rs.getString(1);
				String cognomeRider = rs.getString(2);
				String telefonoRider = rs.getString(3);
				String veicolo = rs.getString(4);
				Boolean disponibilita = rs.getBoolean(5);
				
				
				
				rider = new Rider(idRider,nomeRider,cognomeRider,telefonoRider,veicolo,disponibilita);
				
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return rider;
	}

	@Override
	public List<Object[]> getRiderDaSede(String idSede) {
		
		List<Object[]> rider = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT R.id_rider,R.nomer,R.cognomer,R.telefonor,R.veicolo FROM rider AS R WHERE id_sede='" + idSede +"'" );
			while(rs.next()) {
				
				String idRider = rs.getString(1);
				String nomeRider = rs.getString(2);
				String cognomeRider = rs.getString(3);
				String telefonoRider = rs.getString(4);
				String veicolo = rs.getString(5);
				
				Object[] object = new Object[] {idRider,nomeRider,cognomeRider,telefonoRider,veicolo};
				
				rider.add(object);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return rider;
	}

	@Override
	public int NextIdRider() {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT nextIdRider()");	
			
			if(rs.next()){
				result = rs.getInt(1);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return result;
	}

	@Override
	public int InserisciRider(int idRider, String nome, String cognome, String telefono, String veicolo, String idSede) throws SQLException {
		
		Connection conn = null;
		int result = 0;
		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		result = st.executeUpdate("INSERT INTO rider VALUES ('"+idRider+ "','" + nome +"','" + cognome + "','"
					+ telefono + "','" + veicolo + "',null,'" + idSede + "')");
	
		st.close();
		conn.close();
		
		return result;
	}

	@Override
	public int EliminaRider(int idRider,String idSede) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			result = st.executeUpdate("DELETE FROM rider WHERE id_rider="+idRider+" AND id_sede='" + idSede + "'");
	
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return result;
	}


}
