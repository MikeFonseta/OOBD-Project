package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Ordine;
import Entities.Rider;
import Database.DBConnection;

public class OrdineDAOPostgresImp implements OrdineDAO {

	
	@Override
	public void IniziaConsegna(Integer idOrdine) {
		Connection conn = null;
		int result = 0;
	
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			result = st.executeUpdate("UPDATE ordine SET inizioconsegna = CURRENT_TIMESTAMP WHERE id_ordine='"+idOrdine+"'");	
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	
	@Override
	public void TerminaConsegna(Integer idOrdine) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			result = st.executeUpdate("UPDATE ordine SET fineconsegna = CURRENT_TIMESTAMP WHERE id_ordine='"+idOrdine+"'");	
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	

	@Override
	public void CancellaOrdine(Integer idOrdine) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = DBConnection.getInstance().getConnection();
			Statement st = conn.createStatement();
			result = st.executeUpdate("DELETE FROM ordine WHERE id_ordine='"+idOrdine+ "'");	
			st.close();
			conn.close();
		}catch(SQLException e){				
			e.printStackTrace();	
		}

	}
	
	
	@Override
	public List<Object[]> getOrdiniTabella() throws SQLException {//controllare le lettere accentate
		
		List<Object[]> ordini = new ArrayList<Object[]>();
		Connection conn = null;
		
		conn = DBConnection.getInstance().getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT  id_ordine AS CodOrdine, id_cliente AS CodCliente, nomec || ' ' || cognomec AS NomeCliente,\r\n"
				+ "	via || ' ' || numcivico || ',' || città AS Indirizzo, telefonoc AS TelefonoCliente,\r\n"
				+ "	nomer || ' ' || cognomer AS NomeRider, telefonor AS TelefonoRider,\r\n"
				+ "	totale AS Totale, inizioconsegna AS Stato\r\n"
				+ "FROM ordine AS O NATURAL JOIN infoordine AS I\r\n"
				+ "		 NATURAL JOIN cliente AS C\r\n"
				+ "		 NATURAL JOIN rider AS R\r\n"
				+ "ORDER BY id_ordine ASC");
		while(rs.next()) {
				
			int CodOrdine = rs.getInt(1);
			int CodCliente = rs.getInt(2);
			String NomeCliente= rs.getString(3);
			String Indirizzo= rs.getString(4);
			String TelefonoCliente = rs.getString(5);
			String NomeRider = rs.getString(6);
			String TelefonoRider = rs.getString(7);
			float Totale =rs.getFloat(8);
			java.sql.Timestamp InizioConsegna = rs.getTimestamp(9);
			
			char Stato = 'A'; //Attesa default
			if(InizioConsegna != null) {
				Stato = 'S'; //Spedito se presente la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,NomeRider,TelefonoRider,Totale,Stato};
				
			ordini.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
	}
	
	
	
	

	@Override
	public List<Object[]> ricercaComplessaOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) {
		List<Object[]> lista = new ArrayList<>();	
		Boolean ProdottoNonEsistente = false;
		//serve per evitare di intaccare in un null pointer exception (lo metto in una funzione?)
		if(idProdotti!= null) 
			if( idProdotti.get(0) == -1)
					 ProdottoNonEsistente = true;
		
		Connection connection = null;
		Statement st = null; 
		PreparedStatement queryRicerca = null;
		

		String query1 = "CREATE TEMPORARY TABLE IF NOT EXISTS ProdottiDaCercare(ID_Prodotto INTEGER NOT NULL);";
		String query2 = "CREATE OR REPLACE VIEW SituazioneIdeale AS (SELECT ID_Ordine, ID_Prodotto FROM ProdottiDaCercare,Ordine WHERE FineConsegna IS NOT NULL);";
		String query3 = "CREATE OR REPLACE VIEW TuttiGliOrdini AS (SELECT ID_Ordine, ID_Prodotto FROM Ordine NATURAL JOIN CompOrdine WHERE FineConsegna IS NOT NULL);";	
		String query4 = "CREATE OR REPLACE VIEW OrdiniNo AS (SELECT ID_Ordine FROM SituazioneIdeale EXCEPT SELECT ID_Ordine FROM TuttiGliOrdini );";
		String query5 = "CREATE OR REPLACE VIEW OrdiniSi AS (SELECT O.ID_Ordine FROM Ordine AS O EXCEPT SELECT OrN.ID_Ordine FROM OrdiniNO AS OrN );";
		String query6 = "CREATE OR REPLACE VIEW RisultatoFinale AS (SELECT * FROM OrdiniSi NATURAL JOIN Ordine); ";

	
		try {
			connection = DBConnection.getInstance().getConnection();
			st = connection.createStatement();
			
		
			//Inserimento Prodotti da Cercare nella tabella temporanea
			if(idProdotti != null) {
				st.execute(query1);
					for(Integer i : idProdotti) 
						st.execute("INSERT INTO ProdottiDaCercare VALUES ("+i+")");
			//Creazione View SituazioneIdeale	
				st.execute(query2);
			//Creazione View TuttiGliOrdini
				st.execute(query3);
			//Creazione View degli Ordini che non hanno i prodotti che stiamo cercando (OrdiniNo)
				st.execute(query4);
			//Creazione View degli Ordini che contengono tutti i prodotti che stiamo cercando (OrdiniSi)
				st.execute(query5);
			//Creazione View RisultatoFinale per poter avere tutti gli attributi della relazione Ordine con i risultati che abbiamo trovato nella view precedente
				st.execute(query6);
			}
			
			
			//Creazione stringa sql per la query ricerca
			
			StringBuilder sql = new StringBuilder(1024);
			sql.append("SELECT R.ID_Sede, O.ID_Ordine, C.ID_Cliente, C.NomeC, C.CognomeC, IO.Città, IO.Via, IO.NumCivico, R.ID_Rider, O.Totale "
					 + "FROM Rider AS R ");
			
			if(idProdotti!= null && ProdottoNonEsistente) 	sql.append("INNER JOIN RisultatoFinale AS RF "); 
			
			sql.append("NATURAL JOIN Ordine AS O NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C" );
			
			
			
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
				ClausolaWhere += "  R.Veicolo = ?";
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

			if(ClausolaWhere.length()>0)
				sql.append( " WHERE " ).append( ClausolaWhere );
			
			
			//Creazione Prepared Statement
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
			
		
			queryRicerca.execute();
			int i=0;
			while(queryRicerca.getResultSet().next()) {
				lista.add(i, new Object[] { queryRicerca.getResultSet().getInt(1),
							     			queryRicerca.getResultSet().getInt(2),
							     			queryRicerca.getResultSet().getInt(3),
							     			queryRicerca.getResultSet().getString(4),
							     			queryRicerca.getResultSet().getString(5),
							     			queryRicerca.getResultSet().getString(6),
							     			queryRicerca.getResultSet().getString(7),
							     			queryRicerca.getResultSet().getString(8),
							     			queryRicerca.getResultSet().getInt(9),
							     			queryRicerca.getResultSet().getFloat(10)} );
				i++;
			}
			

			queryRicerca.getResultSet().close();
			queryRicerca.close();
			connection.close();
				
		}catch(SQLException e){
			
		}

		return lista;
	}


}
