package DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtichettaDaoPostgresImp implements EtichettaDAO {

	@Override
	public int AggiungiAllergeneAProdotto(int idProdotto, String Allergene) throws SQLException {
		int risultato = 0;
		Connection connection = null;
		Statement st = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("INSERT INTO Etichetta VALUES ("+idProdotto+", '"+Allergene+"') ");
		
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public int EliminaAllergeneDaProdotto(int idProdotto, String Allergene) throws SQLException {
		int risultato = 0;
		Connection connection = null;
		Statement st = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("DELETE FROM Etichetta WHERE ID_Prodotto = "+idProdotto+" AND NomeA = '"+Allergene+"' ");

		st.close();
		connection.close();
		return risultato;
	}

	public List<String[]> getAllergeniProdotto(int idProdotto) throws SQLException{
		List<String[]> risultato = new ArrayList<String[]>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
	
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		rs = st.executeQuery("SELECT NomeA "
				+ "FROM Etichetta  "
				+ "WHERE ID_Prodotto = '"+idProdotto+"' ");
	
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