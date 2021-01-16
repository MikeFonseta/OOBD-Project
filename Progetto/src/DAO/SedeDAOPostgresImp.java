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
	public List<String> CercaTutteLeSedi() throws SQLException {
		List<String> sedi = new ArrayList<String>();
		Connection conn = null;
		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_sede FROM Sede ORDER BY id_sede");
			
		while(rs.next()){
			sedi.add(rs.getString(1));
		}
		return sedi;
	}
	
	@Override
	public Sede CercaSedePerId(int id) throws SQLException {
		
		Sede sede = null;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM sede WHERE id_sede="+ id +"");	
			
		if(rs.next()){
			
			int idSede = rs.getInt(1);
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
		
		return sede;
	}

	@Override
	public List<Object[]> getSedi() throws SQLException {
		
		List<Object[]> sedi = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM sede ORDER BY id_sede ASC");
		while(rs.next()) {
				
			int idSede = rs.getInt(1);
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
		
		return sedi;
	}
	
	@Override
	public int aggiungiProdottoASede(int idSede, int idProdotto) throws SQLException {
		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("INSERT INTO men\u00F9 VALUES ("+idSede + "," + idProdotto +")");
				
		st.close();
		conn.close();
			
		return risultato;
	}

	@Override
	public int EliminaProdottoDaSede(int idSede, int idProdotto)  throws SQLException {

		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("DELETE FROM men\u00F9 WHERE id_sede="+ idSede + " AND id_prodotto=" + idProdotto +"");
				
		st.close();
		conn.close();
					
		return risultato;
	}


	@Override
	public int EliminaSede(int idSede) throws SQLException  {
		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("DELETE FROM sede WHERE id_sede="+idSede +"");
				
		st.close();
		conn.close();
		
		return risultato;
	}

	@Override
	public int CreaSede(Sede sede, String nomeUtente, String password) throws SQLException {
		
		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato= st.executeUpdate("INSERT INTO sede(id_sede,nomes,telefono,provincia,citt\u00E0,via,numcivico) VALUES (" + sede.getIdSede() + ",'" +
									sede.getNomeSede() + "','" + sede.getTelefonoSede() + "','" + sede.getProvincia() + "','" + sede.getCitta() + "','" + sede.getVia() + "','" + sede.getNumCivico() + "')");
		
		if(risultato == 1) {
			risultato += st.executeUpdate("INSERT INTO account(nomeutente,password,amministratore,id_sede) VALUES ('" + nomeUtente + "','" + password +
					"',false," + sede.getIdSede()+ ")");
		}
			
		st.close();
		conn.close();
			
		return risultato;
	}

	@Override
	public int AggiornaSede(Sede sede, Account gestoreSede, String nuovaPassword) throws SQLException {
		
		int risultato = 0;
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato += st.executeUpdate("UPDATE sede SET nomes='"+sede.getNomeSede()+"',telefono='" + sede.getTelefonoSede() + "',"+
					"provincia='"+sede.getProvincia()+"',citt\u00E0='" + sede.getCitta() + "',via='"+ sede.getVia() + "',numcivico='" + sede.getNumCivico() + "' WHERE id_sede = " + sede.getIdSede());
				
		risultato += st.executeUpdate("UPDATE account SET password='" + nuovaPassword+ "' WHERE nomeutente='" + gestoreSede.getNomeUtente() + "'");
		
		st.close();
		conn.close();
					
		return risultato;
	}

	@Override
	public int idProssimaSede() throws SQLException{
		
		Connection conn = null;
		int risultato = 0;
		
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT idProssimaSede()");	
			
		if(rs.next()){
			risultato = rs.getInt(1);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return risultato;
	}

	@Override
	public List<Object[]> getSediMancanti(int idProdotto) throws SQLException {
		List<Object[]> risultato = null;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		connection = DBConnection.getInstance().getConnection();
		st= connection.createStatement();
		rs = st.executeQuery("SELECT "
						   + "FROM Sede AS  S "
						   + "WHERE ID_Sede NOT IN (SELECT ID_Sede "
						   + "FROM Sede NATURAL JOIN Prodotto "
						   + "WHERE ID_Prodotto = "+idProdotto+" ");
		
		while(rs.next()) {
			risultato.add(new Object[]{
					rs.getInt(1)
			});		
		}
		
		
		rs.close();
		st.close();
		connection.close();
		return risultato;
	}

}
