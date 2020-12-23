package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;

public class ProdottoDAOPostgresImp implements ProdottoDAO{

	@Override
	public List<Object[]> getTuttiProdotti() {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM prodotto");
			while(rs.next()) {
				
				String idProdotto = rs.getString(1);
				String nomeProdotto= rs.getString(2);
				String descrizione = rs.getString(3);
				float prezzo = rs.getFloat(4);
				String categoria = rs.getString(5);
				
				Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo,categoria};
				
				prodotti.add(object);
			}
				
			rs.close();
			st.close();
			conn.close();
			
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
		return prodotti;
	}

	@Override
	public List<Object[]> getProdottiDaSede() {
		// TODO Auto-generated method stub
		return null;
	}

}
