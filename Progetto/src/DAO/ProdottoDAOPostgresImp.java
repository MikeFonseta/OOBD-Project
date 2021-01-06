package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Database.DBConnection;

public class ProdottoDAOPostgresImp implements ProdottoDAO{

	@Override
	public List<Object[]> getTuttiProdotti() throws SQLException  {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_prodotto,nomep,descrizione,prezzo FROM prodotto ORDER BY id_prodotto ASC");
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
			
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
			
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiPerUnaSede(int idSede)  throws SQLException {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM men\u00F9 WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
		
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
			
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}

	@Override
	public List<Object[]> getProdottiDellaSede(int idSede)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto IN (SELECT id_prodotto FROM men\u00F9 WHERE id_sede=" + idSede + ") ORDER BY P.id_prodotto ASC");
			
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			float prezzo = rs.getFloat(3);
			String categoria = rs.getString(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,categoria,prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		
		return prodotti;
	}
	
	@Override
	public List<Object[]> getProdottiSedeCategoria(int idSede, String categoria)  throws SQLException {
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT P.id_prodotto,P.nomep,P.descrizione,P.prezzo,P.categoria FROM prodotto AS P "
					+ "WHERE P.id_prodotto NOT IN (SELECT id_prodotto FROM men\u00F9 WHERE id_sede=" + idSede + ") AND P.categoria='" + categoria + "' ORDER BY P.id_prodotto ASC");
		
		while(rs.next()) {
				
			int idProdotto = rs.getInt(1);
			String nomeProdotto= rs.getString(2);
			String descrizione = rs.getString(3);
			float prezzo = rs.getFloat(4);
				
			Object[] object = new Object[] {idProdotto,nomeProdotto,descrizione,prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
			
		return prodotti;
	}

	
	
	
	
	@Override
	public List<Integer> getTuttiProdottiPerNome(String[] Prodotti) throws SQLException {
		int i =0; boolean isEmpty = false;
		List<Integer> risultato = new ArrayList<>();
		Connection connection = DBConnection.getInstance().getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		for(int j = 0;j<Prodotti.length;j++)
			Prodotti[j] = Prodotti[j].toLowerCase(Locale.ROOT);
		
		while(i < Prodotti.length && isEmpty == false) {
			isEmpty = true;																						//no case-sensitive
			String query = "SELECT ID_Prodotto FROM  Ordine NATURAL JOIN CompOrdine NATURAL JOIN Prodotto WHERE LOWER(NomeP) LIKE '"+Prodotti[i]+"%'";
			st = connection.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				isEmpty = false;
				Integer temp = rs.getInt(1);
				if(!risultato.contains(temp)) risultato.add(temp);									//elimina duplicati
			}
			i=i+1;
		}
		
		//se uno dei parametri non ha nessun riscontro la ricerca salta
		if(isEmpty==true) {
		risultato.clear();
		risultato.add(0,-1);
		}		

		
		rs.close();
		st.close();
		connection.close();
	return risultato;
	
	}
	
	
	@Override
	public List<String> getCategorieProdotto() throws SQLException{
		List<String> categorie = new ArrayList<String>();
		categorie.add("Tutte");
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT categoria FROM prodotto GROUP BY categoria");
		
		while(rs.next()) {
			categorie.add(rs.getString(1));
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return categorie;
	}
	
	
	@Override
	public List<Object[]> getProdottiTabella(String categoria) throws SQLException {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		String filtro = "";
		if(!categoria.equals("Tutte")) {
			filtro = " WHERE categoria = '"+categoria+"' ";
		}
		
		ResultSet rs = st.executeQuery("SELECT nomep AS Nome, prezzo AS Prezzo "
				+ "FROM prodotto "
				+ filtro
				+ "ORDER BY Nome ASC");
		
		while(rs.next()) {
				
			String Nome= rs.getString(1);
			String Prezzo= "\u20AC "+String.valueOf(rs.getFloat(2));
			
			Object[] object = new Object[] {Nome,Prezzo};
				
			prodotti.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}

	
	@Override
	public List<Object[]> getProdottiPerId_Ordine(int idOrdine) throws SQLException {
		List<Object[]> risultato = new ArrayList<Object[]>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		
			connection = DBConnection.getInstance().getConnection();
			st = connection.createStatement(); 
			rs = st.executeQuery("SELECT ID_Prodotto, NomeP, NumPezzi, Prezzo "
							   + "FROM CompOrdine NATURAL JOIN Prodotto "
							   + "WHERE ID_Ordine = "+idOrdine+" ");
			
			while(rs.next()) {
				risultato.add(new Object[] {
				rs.getInt(1),
				rs.getString(2),
			    rs.getInt(3),
			    "\u20ac "+ rs.getString(4)} );	
			}	
		
		rs.close();
		st.close();
		connection.close();
		
		return risultato;
	}

	@Override
	public int eliminaProdottoDaTutteLeSedi(int idProdotto) throws SQLException {
		int risultato = 0;
		Connection conn = null;
			
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		risultato = st.executeUpdate("DELETE FROM Prodotto WHERE ID_Prodotto="+idProdotto+"");
					
		st.close();
		conn.close();
			
		return risultato;
	
	}

	public List<Integer> getProdottiPerAllergeni(String[] nomiAllergeni) throws SQLException {
		int i =0; boolean nessunRiscontro = false;
		List<Integer> risultato = new ArrayList<>();
		Connection connection = DBConnection.getInstance().getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		for(int j = 0;j<nomiAllergeni.length;j++)
			nomiAllergeni[j] = nomiAllergeni[j].toLowerCase(Locale.ROOT);
		
		while(i < nomiAllergeni.length && nessunRiscontro == false) {
			nessunRiscontro = true;																						
			String query = "SELECT ID_Prodotto FROM Etichetta WHERE LOWER(NomeA) LIKE '"+nomiAllergeni[i]+"%'";
			st = connection.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				nessunRiscontro = false;
				Integer temp = rs.getInt(1);
				if(!risultato.contains(temp)) risultato.add(temp);				//elimina duplicati					
			}
			i=i+1;
		}
		
		//se uno dei parametri non ha nessun riscontro ritorna -1 al primo elemento
		if(nessunRiscontro==true) {
		risultato.clear();
		risultato.add(0,-1);
		}		

		
		rs.close();
		st.close();
		connection.close();
		return risultato;
	}
	
	
	
	public List<Object[]> ricercaComplessaProdotti(String Categoria, Integer Min, Integer Max, List<Integer> idProdottiConAllergeni) throws SQLException{
		List<Object[]> risultato = new ArrayList<Object[]>();
		Connection connection = null;
		Statement st = null;
		PreparedStatement query = null;
		ResultSet rs = null;

		
		StringBuilder sql = new  StringBuilder(1024); 
		sql.append("SELECT DISTINCT ID_Prodotto, NomeP, Categoria, Descrizione, Prezzo " 
		+ "FROM Prodotto AS P " );
	
		if(idProdottiConAllergeni != null )
			sql.append("NATURAL JOIN Etichetta AS E ");
		
		
		
		String ClausolaWhere = "";
		if(Categoria!= null) {
			ClausolaWhere = "Categoria = ?";
		}
		if(Min!= null) {
			if(ClausolaWhere.length()>0) 
				ClausolaWhere += " AND ";
			else 
				ClausolaWhere += "";
		ClausolaWhere += "Prezzo >= ?";
		}
		if(Max!= null) {
			if(ClausolaWhere.length()>0) 
				ClausolaWhere += " AND ";
			else 
				ClausolaWhere += "";
		ClausolaWhere += "Prezzo <= ?";
		}
		if(idProdottiConAllergeni!= null) {
			if(ClausolaWhere.length()>0) 
				ClausolaWhere += " AND " ;
			else ClausolaWhere += ""; 
		ClausolaWhere += "( ID_Prodotto = ? ";
		int i=1;
		while(i<idProdottiConAllergeni.size()) {
			ClausolaWhere += " OR ";
			ClausolaWhere += "  ID_Prodotto = ?";
		i++;
		}
		
		ClausolaWhere += " )";
	}
		
		
		if(ClausolaWhere.length()>0)
			sql.append( " WHERE " ).append( ClausolaWhere );	
		
		connection = DBConnection.getInstance().getConnection();
		query = connection.prepareStatement(sql.toString());
		
		int indice = 1;
		if(Categoria != null)
			query.setString(indice++, Categoria);
		
		if(Min != null)
			query.setInt(indice++, Min);
		
		if(Max != null) 
			query.setInt(indice++, Max);
			
		if(idProdottiConAllergeni != null) {
			for(int s = 0;s<idProdottiConAllergeni.size(); s++) {
				query.setInt(indice++, idProdottiConAllergeni.get(s));
			}
		}
		
		
		rs = query.executeQuery();
			while(rs.next()) {
				risultato.add(new Object[] {
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						"",														
						"\u20ac "+ rs.getString(5) });
			}
			
		if(risultato != null) {	
			st= connection.createStatement();
			for(Object[] object : risultato) {	
				String allergeni = "";
				rs = st.executeQuery("SELECT NomeA FROM Etichetta WHERE ID_Prodotto = "+object[0]+" ");
				while(rs.next())
					if(allergeni.length()>0)
						allergeni += ", " + rs.getString(1) ;
					else
						allergeni += rs.getString(1);
			
					object[4] = allergeni;
			
				}		
		}		
		
		rs.close();
		st.close();
		query.close();
		connection.close();
		
		return risultato;
		
	}
	
	
	
	
	
	
	
}
