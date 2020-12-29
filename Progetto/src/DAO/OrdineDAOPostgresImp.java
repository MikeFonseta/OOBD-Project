package DAO;

import java.sql.Connection;
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
	public void impostaInizioConsegna(Integer idOrdine) {
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
	public void impostaFineConsegna(Integer idOrdine) {
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
	public List<Object[]> getOrdiniTabella() throws SQLException {
		
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
				Stato = 'S'; //Spedito se c'� la data inizioConsegna
			}
				
			Object[] object = new Object[] {CodOrdine,CodCliente,NomeCliente,Indirizzo,TelefonoCliente,NomeRider,TelefonoRider,Totale,Stato};
				
			ordini.add(object);
		}
				
		rs.close();
		st.close();
		conn.close();
		
		return ordini;
	}
	
	
	
	
	//da cambiare tutta
	@Override
	public List<Object[]> ricercaComplessaOrdini(String IDSede, String Prodotti, String Veicolo, Integer Min, Integer Max) {
		Connection conn = null; String s2 = new String();
		System.out.println(Min);
		try {
			conn = DBConnection.getInstance().getConnection();
			
			//da restituire: CodSede CodOrdine CodCliente NomeCliente Indirizzo CodiceRider NomeRider Totale
			Statement st = conn.createStatement();


			ResultSet rs = st.executeQuery("SELECT R.ID_Sede, O.ID_Ordine, C.ID_Cliente, C.NomeC, C.CognomeC, IO.Citt�, IO.Via, IO.NumCivico, R.ID_Rider, O.Totale FROM Rider AS R NATURAL JOIN Ordine AS O NATURAL JOIN CompOrdine AS CO NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C WHERE R.ID_Sede LIKE '"+IDSede+"' AND O.Totale <= "+Max+" AND O.Totale >= "+Min+" AND R.Veicolo = '"+Veicolo+"' ");
			ArrayList<Object> lista = new ArrayList<Object>();
			while(rs.next()) {
				lista.add(rs.getString(0));
				lista.add(rs.getInt(1));
				lista.add(rs.getInt(2));
				lista.add(rs.getString(3));
				lista.add(rs.getString(4));
				lista.add(rs.getString(5));
				lista.add(rs.getString(6));
				lista.add(rs.getString(7));
				lista.add(rs.getInt(8));
				lista.add(rs.getFloat(9));
			}
			
			for(Object i : lista)
			System.out.println(i.toString());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		  }	

		
		
		
		
		
		
		
		
		
		
		
		
		return null;
	}

}
