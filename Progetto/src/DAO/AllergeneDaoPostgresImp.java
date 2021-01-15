package DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import Database.DBConnection;

public class AllergeneDaoPostgresImp implements AllergeneDAO {

	@Override
	public List<String[]> getAllergeniMancanti(int idProdotto) throws SQLException{
		List<String[]> risultato = new ArrayList<String[]>();
		Connection connection = null;
    	Statement st = null;
		ResultSet rs = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		rs = st.executeQuery("SELECT A.NomeA "
				+ "FROM Allergene AS A "
				+ "WHERE A.NomeA NOT IN ("
				+ "SELECT NomeA "
				+ "FROM Allergene NATURAL JOIN Etichetta "
				+ "WHERE ID_Prodotto = "+idProdotto+") ");
		
		while(rs.next()) {
			risultato.add(new String[]{		
				rs.getString(1) });
		}
		
		rs.close();
		st.close();
		connection.close();
	
		return risultato;
	}


}