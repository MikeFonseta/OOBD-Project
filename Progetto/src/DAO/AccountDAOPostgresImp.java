package DAO;

import java.sql.*;

import Database.DBConnection;
import Entities.Account;
import Entities.Sede;

public class AccountDAOPostgresImp implements AccountDAO {
		
	@Override
	public Account ControlloCredenziali(String username, String Password) {
		Connection conn = null;
		Account account = null;
		try {
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
		}catch(SQLException e){				
			e.printStackTrace();	
		}
			return account;
	
	}

	@Override
	public Account CercaAccountPerIdSede(int idSede) {
		Connection conn = null;
		Account account = null;
		try {
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
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		return account;
	}

	@Override
	public String NomeUtentePerNuovaSede(int idSede) {
		
		Connection conn = null;
		String nomeUtente=null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT nomeutentesede(" + idSede +")");	
			
			if(rs.next()){
				nomeUtente= rs.getString(1);	
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		return nomeUtente;
	}

}