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
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement queryRicerca = null;

			//Creazione stringa sql per la query ricerca
			
			StringBuilder sql = new StringBuilder(1024);
			sql.append("SELECT R.ID_Sede, O.ID_Ordine, C.ID_Cliente,  C.NomeC || ' ' || C.CognomeC AS NomeCliente, IO.via || ' ' || IO.numcivico || ',' || IO.città AS Indirizzo, "
					 + "R.ID_Rider, R.NomeR|| ' ' || R.CognomeR AS NomeRider,  O.Totale "
					 + "FROM Rider AS R NATURAL JOIN Ordine AS O NATURAL JOIN CompOrdine AS CO NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C " );
			
			
			
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
			
			if(idProdotti!= null) {
				for(int i=0; i<idProdotti.size(); i++) {
					if(ClausolaWhere.length()>0)
						ClausolaWhere += " AND ";
					else
						ClausolaWhere += "";
					ClausolaWhere += "  CO.Id_Prodotto = ?";
				}
			}

			if(ClausolaWhere.length()>0)
				sql.append( " WHERE " ).append( ClausolaWhere );
			
			
			//Creazione Prepared Statement
			try {
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
							queryRicerca.setInt( indice++, idProdotti.get(s) ); 
						}
					}
			
			
					rs = queryRicerca.executeQuery();
					int i=0;
					while(rs.next()) {
					lista.add(i, new Object[] { rs.getInt(1),
							     				rs.getInt(2),
							     				rs.getInt(3),
							     				rs.getString(4),
							     				rs.getString(5),
							     				rs.getInt(6),
							     				rs.getString(7),
							     				rs.getFloat(8)} );
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
