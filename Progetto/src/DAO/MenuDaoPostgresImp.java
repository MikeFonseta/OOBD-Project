package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Database.DBConnection;

public class MenuDaoPostgresImp implements MenuDao {

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
}
