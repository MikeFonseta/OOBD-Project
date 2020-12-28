package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;

public class OrdineDAOPostgresImp implements OrdineDAO {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//da cambiare tutta
	@Override
	public List<Object[]> ricercaComplessaOrdini(String IDSede, String Prodotti, String Veicolo, Integer Min, Integer Max) {
		Connection conn = null; String s2 = new String();
		System.out.println(Min);
		try {
			conn = DBConnection.getInstance().getConnection();
			String prodotti[] = Prodotti.split(";");  
			//da restituire: CodSede CodOrdine CodCliente NomeCliente Indirizzo CodiceRider NomeRider Totale
			Statement st = conn.createStatement();
			for(String s : prodotti){
				s2 = s2 + ",'" + s + "'";
				}
			System.out.print("cioa");
			ResultSet rs = st.executeQuery("SELECT R.ID_Sede, O.ID_Ordine, C.ID_Cliente, C.NomeC, C.CognomeC, IO.Città, IO.Via, IO.NumCivico, R.ID_Rider, O.Totale FROM Rider AS R NATURAL JOIN Ordine AS O NATURAL JOIN CompOrdine AS CO NATURAL JOIN InfoOrdine AS IO NATURAL JOIN Cliente AS C WHERE R.ID_Sede LIKE '"+IDSede+"' AND O.Totale <= "+Max+" AND O.Totale >= "+Min+" AND R.Veicolo = '"+Veicolo+"' ");
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
