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
			rs.next();
			String NomeUtente = rs.getString(1);
			String Pwd = rs.getString(2);
			Boolean amministratore = rs.getBoolean(3);
			String ID_Sede = rs.getString(4);
				
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
			
		}catch(SQLException e){				
			// TODO Auto-generated catch block
			//e.printStackTrace();	
		}
		return account;
	
	}

}