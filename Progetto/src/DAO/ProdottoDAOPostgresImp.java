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
import Entities.Prodotto;

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
	public String[] getDatiSingoloProdotto(int idProdotto) throws SQLException{
		
		Connection conn=DBConnection.getInstance().getConnection();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT nomep AS nome,descrizione,STRING_AGG(nomea,',') AS allergeni,prezzo FROM prodotto \r\n"
				+ "LEFT JOIN etichetta ON prodotto.id_prodotto=etichetta.id_prodotto\r\n"
				+ "Where prodotto.id_prodotto='"+idProdotto+"'\r\n"
				+ "GROUP BY nome,descrizione,prezzo");
		
		String descrizione="<html>Nessuna descrizione</html>";
		String allergeni="<html>Nessun allergene presente</html>";
		
		rs.next();
		String nome ="<html>"+rs.getString(1)+"</html>";
		if(rs.getString(2)!=null) descrizione ="<html>"+ rs.getString(2)+"</html>";
		if(rs.getString(3)!=null) allergeni ="<html>"+ rs.getString(3)+"</html>";
		String prezzo ="<html> \u20AC "+ rs.getString(4)+"</html>";
		
		
		String dati[]=new String[] {nome,descrizione,allergeni,prezzo};
		
		rs.close();
		st.close();
		conn.close();
		
		return dati;
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
		
		ResultSet rs = st.executeQuery("SELECT nomep AS nome, prezzo AS prezzo, prodotto.id_prodotto ,STRING_AGG(nomea,',') AS allergeni\r\n"
				+ "FROM prodotto LEFT JOIN etichetta ON prodotto.id_prodotto=etichetta.id_prodotto\r\n"
				+ filtro
				+ "GROUP BY nome,prezzo,prodotto.id_prodotto\r\n"
				+ "ORDER BY nome ASC");
		
		while(rs.next()) {
				
			String Nome= rs.getString(1);
			String Prezzo= "\u20AC "+String.valueOf(rs.getFloat(2));
			String ID= rs.getString(3);
			String allergeni= rs.getString(4);
			
			Object[] object = new Object[] {Nome,Prezzo,ID,allergeni};
				
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
//		st.executeUpdate("DELETE )
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
			sql.append(" ORDER BY id_prodotto ASC ");
		
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

	@Override
	public int idProssimoProdotto() throws SQLException {
		int risultato = 0;
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		rs = st.executeQuery("SELECT IdProssimoProdotto()");
		if(rs.next())
			risultato = rs.getInt(1);
		
		rs.close();
		st.close();
		connection.close();
		
		return risultato;
	}

	@Override
	public int CreaProdotto(int idProssimoProdotto, String nome, String descrizione, float prezzo, String categoria) throws SQLException {
		Connection connection = null;
		Statement st = null;
		int risultato = 0;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("INSERT INTO Prodotto VALUES ("+idProssimoProdotto+", '"+nome+"', '"+descrizione+"', "+prezzo+", '"+categoria+"') ");
		
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public Prodotto getProdottoPerId(int idProdotto) throws SQLException {
		Prodotto risultato = null;
		Connection connection  =null;
		Statement st = null;
		ResultSet rs = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		rs = st.executeQuery("SELECT * FROM Prodotto "
							+ "WHERE ID_Prodotto = "+idProdotto+" ");
		
		while(rs.next()) {
			int id = rs.getInt(1);  
			String nome = rs.getString(2);
			String descrizione = rs.getString(3);
 			float prezzo = rs.getFloat(4);
			String categoria = rs.getString(5);
			risultato = new Prodotto(id, nome, descrizione, prezzo, categoria);
		}
		
		rs.close();
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public int AggiornaProdotto(Prodotto prodotto) throws SQLException {
		int risultato = 0;
		Connection connection = null;
		Statement st = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("UPDATE Prodotto "
									+ "SET nomep= '"+prodotto.getNomeProdotto()+"', descrizione= '"+prodotto.getDescrizione()+"', prezzo= "+prodotto.getPrezzo()+", categoria= '"+prodotto.getCategoria()+"' "
									+ "WHERE ID_Prodotto = "+prodotto.getIdProdotto()+"; ");
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public int CreaCategoria(String NomeCategoria) throws SQLException {
		Connection connection = null;
		Statement st = null;
		int risultato = 0;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("INSERT INTO categorie VALUES('" + NomeCategoria+ "')");
		
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public int EliminaCategoria(String NomeCategoria) throws SQLException {
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		int risultato = 0;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		rs = st.executeQuery("SELECT EliminaCategoria('"+NomeCategoria+"')");
		
		if(rs.next()) {
			risultato = rs.getInt(1);
		}
		
		rs.close();
		st.close();
		connection.close();
		return risultato;
	}

	@Override
	public List<String> getCategorieTotali() throws SQLException {
		List<String> categorie = new ArrayList<String>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT nomecategoria FROM categorie");
		
		while(rs.next()) {
			categorie.add(rs.getString(1));
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return categorie;
	}
	
	
	@Override
	public int AggiungiProdottoACategoria(int idProdotto, String Categoria) throws SQLException{
		int risultato = 0;
		Connection connection = null;
		Statement st = null;
		
		connection = DBConnection.getInstance().getConnection();
		st = connection.createStatement();
		risultato = st.executeUpdate("UPDATE Prodotto SET Categoria = '"+Categoria+"' WHERE ID_Prodotto = "+idProdotto+" ");
		
		st.close();
		connection.close();
		return risultato;
	}
	
	
	
	
}
