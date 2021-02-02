package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Database.DBConnection;

public class OrdineDAOPostgresImp implements OrdineDAO {

	
	@Override
	public void IniziaConsegna(Integer idRider,boolean annulla) {
		Connection conn = null;
		String data=null;
		
		if(annulla) {
			data="NULL";
		}
		else {
			data="CURRENT_TIMESTAMP";
		}
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
		
			String inizio="UPDATE ordine SET inizioconsegna = "+data+" WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL";	
			st.executeUpdate(inizio);
		
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}
		
	}
	
	@Override
	public void TerminaConsegna(Integer idRider) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
		
			
				
			st.executeUpdate("UPDATE ordine SET fineconsegna = CURRENT_TIMESTAMP WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL; "
					+ " UPDATE rider SET disponibilità = true , numeroordini=0  WHERE id_rider='"+idRider+"'");
			
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}

	
	@Override
	public void CancellaOrdine(Integer idOrdine) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();

			
			st.executeUpdate("DELETE FROM ordine WHERE id_ordine='"+idOrdine+ "'; "
							+"DELETE FROM infoordine WHERE id_ordine='"+idOrdine+ "'; "
							+"DELETE FROM compordine WHERE id_ordine='"+idOrdine+ "'; ");		

			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	@Override
	public void CancellaCodiceRider(Integer idRider) {
		Connection conn = null;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			
			
			st.executeUpdate("UPDATE ordine SET id_rider=NULL WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL; "
					+ "UPDATE rider SET numeroordini=0  WHERE id_rider='"+idRider+"'");
			
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	public int CreaOrdine(float totale,int idRider,int idSede) throws SQLException{
		int idOrdine=0;
		
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("INSERT INTO ordine (totale,id_rider,id_sede) "
				+ "VALUES('"+totale+"','"+idRider+"','"+idSede+"') RETURNING id_ordine");
		
		if(rs.next()){
			 idOrdine = rs.getInt(1);
		}
		
		st.close();
		conn.close();
		
		return idOrdine;
	}
	
	@Override
	public void AggiornaTotaleOrdine(int idOrdine,float totale) throws SQLException{
		
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate("UPDATE ordine SET totale='"+totale+"' "
				+ " WHERE id_ordine = '"+idOrdine+"' ");
		
		
		st.close();
		conn.close();
		
	}
	
	
	
	@Override
	public void CreaCompOrdine(List<int[]> prodotti,int idNuovoOrdine) throws SQLException
	{
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		String inserisciProdotti="INSERT INTO compordine(id_prodotto,numpezzi,id_ordine)\r\n"
				+ " VALUES ";
		
		for (int i = 0; i < prodotti.size(); i++) {
			if(i>0) inserisciProdotti+=",";
			int idProdotto=prodotti.get(i)[0];
			int quantita=prodotti.get(i)[1];
			inserisciProdotti+=" ('"+idProdotto+"','"+quantita+"','"+idNuovoOrdine+"') ";
		}
		
		st.executeUpdate(inserisciProdotti);
		
		st.close();
		conn.close();
	}
	

	
	@Override
	public void EliminaCompOrdine(int idOrdine) throws SQLException
	{
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate("DELETE FROM compordine WHERE id_ordine='"+idOrdine+"' ");
		
		st.close();
		conn.close();
	}
	
	
	@Override
	public void CreaInfoOrdine(int idOrdine,int idCliente, String citta, String via, String civico, String telefono, String provincia) throws SQLException
	{
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate("INSERT INTO infoordine (id_ordine,id_cliente,città,via,numcivico,telefonoc,provincia)\r\n"
				+ " VALUES ('"+idOrdine+"','"+idCliente+"','"+citta+"','"+via+"','"+civico+"','"+telefono+"','"+provincia+"')" );
		
		
		st.close();
		conn.close();
	}
	
	@Override
	public void AggiornaInfoOrdine(int idOrdine, String citta, String via, String civico, String telefono, String provincia) throws SQLException
	{
		Connection conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		
		st.executeUpdate("UPDATE infoordine SET città='"+citta+"', via='"+via+"', numcivico='"+civico+"', telefonoc='"+telefono+"', provincia='"+provincia+"' "
				+ " WHERE id_ordine = '"+idOrdine+"' ");
		
		
		st.close();
		conn.close();
	}
	
	
	@Override
	public List<Object[]> getOrdiniTabella(int idSede) throws SQLException {
		
		List<Object[]> ordini = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  O.id_ordine AS CodOrdine,id_rider AS CodRider, I.id_cliente AS CodCliente, nomec || ' ' || \r\n"
				+ "		cognomec AS NomeCliente, via || ' ' || numcivico || ', ' || città || ' (' || provincia || ')'  AS Indirizzo, \r\n"
				+ "		telefonoc AS TelefonoCliente,totale AS Totale, inizioconsegna AS Stato\r\n"
				+ "		FROM ordine AS O LEFT JOIN infoordine AS I ON I.id_ordine=O.id_ordine\r\n"
				+ "		LEFT JOIN cliente AS C ON C.id_cliente=I.id_cliente\r\n"
				+ "		WHERE id_sede='"+idSede+"' AND fineconsegna IS NULL\r\n"
				+ "		ORDER BY O.id_ordine ASC");
		while(rs.next()) {
				
			int CodOrdine = rs.getInt(1);
			int CodRider = rs.getInt(2);
			int CodCliente = rs.getInt(3);
			String NomeCliente= rs.getString(4);
			String Indirizzo= rs.getString(5);
			String TelefonoCliente = rs.getString(6);
			String Totale= "€ "+ rs.getString(7);		
			java.sql.Timestamp InizioConsegna = rs.getTimestamp(8);
			
			char Stato = 'A'; //Attesa default
			if(InizioConsegna != null) {
				Stato = 'S'; //Spedito se presente la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodRider,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,Totale,Stato};
				
			ordini.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
	}
	
	
	@Override
	public List<Object[]> getOrdiniFiltroRider(int idRider) throws SQLException{
		List<Object[]> ordini = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  id_ordine AS CodOrdine,id_rider AS CodRider, id_cliente AS CodCliente, nomec || ' ' || cognomec AS NomeCliente,\r\n"
				+ "				via || ' ' || numcivico || ', ' || città || ' (' || provincia || ')' AS Indirizzo, telefonoc AS TelefonoCliente,\r\n"
				+ "			    totale AS Totale, inizioconsegna AS Stato\r\n"
				+ "				FROM ordine AS O NATURAL JOIN infoordine AS I\r\n"
				+ "				NATURAL JOIN cliente AS C\r\n"
				+ "				WHERE id_rider='"+idRider+"' AND fineconsegna IS NULL\r\n"
				+ "				ORDER BY id_ordine ASC");
		//inserire contenuto
		
		
		while(rs.next()) {
			
			int CodOrdine = rs.getInt(1);
			int CodRider = rs.getInt(2);
			int CodCliente = rs.getInt(3);
			String NomeCliente= rs.getString(4);
			String Indirizzo= rs.getString(5);
			String TelefonoCliente = rs.getString(6);
			String Totale= "€ "+ rs.getString(7) ;		
			java.sql.Timestamp InizioConsegna = rs.getTimestamp(8);
			
			char Stato = 'A'; //Attesa default
			if(InizioConsegna != null) {
				Stato = 'S'; //Spedito se presente la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodRider,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,Totale,Stato};
				
			ordini.add(object);
		}
		
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
		
	}
	
	
	public List<Object[]> getProdottiCarrello(int idOrdine) throws SQLException  {
		
		List<Object[]> prodotti = new ArrayList<Object[]>();
		Connection conn =  DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT nomep AS nome, numpezzi AS quantita,prezzo AS prezzo, PR.id_prodotto ,STRING_AGG(nomea,',') AS allergeni\r\n"
		+ "FROM prodotto AS PR LEFT JOIN etichetta AS E ON PR.id_prodotto=E.id_prodotto\r\n"
		+ "LEFT JOIN compordine AS CO ON CO.id_prodotto=PR.id_prodotto\r\n"
		+ "WHERE CO.id_ordine='"+idOrdine+"'\r\n"
		+ "GROUP BY nome,numpezzi,prezzo,PR.id_prodotto\r\n"
		+ "ORDER BY nome ASC");
		
		while(rs.next()) {
			
			String nome=rs.getString(1);
			int quantita=rs.getInt(2);
			String prezzo= "€ "+ rs.getString(3);
			int ID=rs.getInt(4);
			String allergeni=rs.getString(5);
			
			Object[] object = new Object[] {nome,quantita,prezzo,ID,allergeni};
			
			prodotti.add(object);
			
		} 
		
		rs.close();
		st.close();
		conn.close();
		
		return prodotti;
	}
	

	@Override
	public List<Object[]> ricercaComplessaOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) throws SQLException {
		
		List<Object[]> risultato = new ArrayList<>();
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement queryRicerca = null;

			//Creazione stringa sql per la query ricerca
			
			StringBuilder sql = new StringBuilder(1024);
			sql.append("SELECT DISTINCT O.ID_Sede, O.ID_Ordine, C.ID_Cliente,  C.NomeC || ' ' || C.CognomeC AS NomeCliente, IO.via || ' ' || IO.numcivico || ',' || IO.città AS Indirizzo, "
					 + "R.ID_Rider, R.NomeR|| ' ' || R.CognomeR AS NomeRider,  O.Totale "
					 + "FROM (Veicolo AS V INNER JOIN Rider AS R ON V.NomeV=R.Veicolo) NATURAL JOIN Ordine AS O NATURAL JOIN CompOrdine AS CO NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C " );
			
			
			
			//Costruzione della clausola where in base a quali campi sono riempiti
			String ClausolaWhere = "";
			if( idSede != null ) {
				ClausolaWhere += " R.ID_Sede = ?";
			}
			if( Veicolo != null ) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  V.NomeV = ?";
			}
			if( Min != null ) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  O.Totale >= ?";
			}
			if( Max != null) {
				if(ClausolaWhere.length()>0)
					ClausolaWhere += " AND ";
				else
					ClausolaWhere += "";
				ClausolaWhere += "  O.Totale <= ?";
			}
			
			if(idProdotti!= null) {
				if(ClausolaWhere.length()>0) 
					ClausolaWhere += " AND " ;
					
				else ClausolaWhere += ""; 
				
				ClausolaWhere += "( CO.Id_Prodotto = ? ";
				int indice=1;
				while(indice<idProdotti.size()) {
					ClausolaWhere += " OR ";
					ClausolaWhere += "  CO.Id_Prodotto = ?";
				indice++;
				}
				
				ClausolaWhere += " )";
			}
			
	

			if(ClausolaWhere.length()>0)
				sql.append( " WHERE " ).append( ClausolaWhere );
			
			
			//Creazione Prepared Statement

					connection = DBConnection.getInstance().getConnection();
					queryRicerca = connection.prepareStatement( sql.toString() );
			
					//Inserimenti valori nelle condizioni (se esisitono)
					int indice = 1;
		
					if( idSede != null ) {
						queryRicerca.setInt(indice++, idSede);  
					}
					if( Veicolo != null) {
						queryRicerca.setString( indice++, Veicolo);  
					}
					if( Min != null ) {
						queryRicerca.setInt( indice++, Min );  
					}
					if( Max != null ) {
						queryRicerca.setInt( indice++, Max );  
					}
				
					if(idProdotti!= null) {
						for(int s =0; s<idProdotti.size(); s++) {
							queryRicerca.setInt(indice++, idProdotti.get(s) ); 
						}
					}
			
			
					rs = queryRicerca.executeQuery();
					while(rs.next()) {
					risultato.add(new Object[] { rs.getInt(1),
							     				rs.getInt(2),
							     				rs.getInt(3),
							     				rs.getString(4),
							     				rs.getString(5),
							     				rs.getInt(6),
							     				rs.getString(7),
							     				"€ " + rs.getFloat(8)} );
				
					}
			

					queryRicerca.getResultSet().close();
					queryRicerca.close();
					connection.close();
				

		return risultato;
	}


}
