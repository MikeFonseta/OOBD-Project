package DAO;

import java.sql.*;

import Database.DBConnection;
import Entities.Account;
import Entities.Sede;

public class AccountDAOPostgresImp implements AccountDAO {
		
	@Override
	public Account ControlloCredenziali(String username, String Password) throws SQLException {
		Connection conn = null;
		Account account = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("Select * FROM Account WHERE NomeUtente ='"+username+"' AND Password = '"+Password+"'");	
		
		if(rs.next()) {
			String NomeUtente = rs.getString(1);
			String Pwd = rs.getString(2);
			Boolean amministratore = rs.getBoolean(3);
			int ID_Sede = rs.getInt(4);
				
			if(amministratore == false) {
				SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
				Sede s = sedeDao.CercaSedePerId(ID_Sede);
				account = new Account(NomeUtente, Pwd, amministratore, s);
			}else {
				account = new Account(NomeUtente, Pwd, amministratore, null);
			}
					
			rs.close();
			st.close();
			conn.close();
		}
		
		return account;
	
	}

	@Override
	public Account CercaAccountPerIdSede(int idSede) throws SQLException  {
		Connection conn = null;
		Account account = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Account WHERE id_sede=" + idSede + "");	
			
		if(rs.next()){
				
			String NomeUtente = rs.getString(1);
			String Pwd = rs.getString(2);
			Boolean amministratore = rs.getBoolean(3);
			int ID_Sede = rs.getInt(4);
			
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			Sede s = sedeDao.CercaSedePerId(ID_Sede);
			account = new Account(NomeUtente, Pwd, amministratore, s);
			
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return account;
	}

	@Override
	public String NomeUtentePerNuovaSede(int idSede) throws SQLException  {
		
		Connection conn = null;
		String nomeUtente=null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT nomeutentesede(" + idSede +")");	
			
		if(rs.next()){
			nomeUtente= rs.getString(1);	
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return nomeUtente;
	}

	@Override
	public String[] getDatiCliente(int idCliente) throws SQLException{
		
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT nomec AS nome, cognomec AS cognome, telefonoc AS telefono, provincia, città, via, numcivico\r\n"
				+ "FROM cliente LEFT JOIN infoordine ON cliente.id_cliente=infoordine.id_cliente\r\n"
				+ "WHERE cliente.id_cliente='"+idCliente+"' \r\n"
				+ "ORDER BY id_ordine DESC\r\n"
				+ "LIMIT 1 ");
		
		String[] dati;
		
		if(rs.next()){
			String nome= rs.getString(1);	
			String cognome= rs.getString(2);	
			String telefono= rs.getString(3);	
			String provincia= rs.getString(4);	
			String citta= rs.getString(5);	
			String via = rs.getString(6);	
			String civico= rs.getString(7);	
			dati=new String[] {nome,cognome,telefono,provincia,citta,via,civico};
		}
		else {
			dati=new String[] {null};
		}
		
		rs.close();
		st.close();
		conn.close();
	
		return dati;
	}
	
	@Override
	public int getClienteID() throws SQLException{
	
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT nextval('cliente_id_cliente_seq')");

		int id;
		if(rs.next()){
			id=rs.getInt(1);
		}
		else {
			id=0;
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return id;
	}
		
	@Override
	public int getClienteOrdine(int idOrdine) throws SQLException{
		int idCliente=0;
		
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_cliente FROM infoordine WHERE id_ordine='"+idOrdine+"' ");
		
		if(rs.next()){
			idCliente=rs.getInt(1);
		}
		
		
		rs.close();
		st.close();
		conn.close();
		return idCliente;
	}
	
	@Override
	public void CreaCliente(int idCliente,String nome,String cognome) throws SQLException{
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate("INSERT INTO cliente VALUES("+idCliente+",'"+nome+"','"+cognome+"')");
		
		st.close();
		conn.close();
	}
	
	@Override
	public boolean VerificaCliente(int idCliente) throws SQLException 
	{
		boolean esiste=false;
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		ResultSet rs=st.executeQuery("SELECT id_cliente FROM cliente WHERE id_cliente='"+idCliente+"' ");
		
		if (rs.next()) esiste=true;
		
		st.close();
		conn.close();
		
		
		return esiste;
	}
	
	@Override
	public int ModificaPassword(Account account, String NuovaPassword) throws SQLException{
		
		int risultato=0;
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("UPDATE account SET password='"+NuovaPassword+"' WHERE nomeutente='"+ account.getNomeUtente() +"'");
	
		st.close();
		conn.close();
		
		return risultato;
	}

	
}