package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Rider;

public interface RiderDAO {

	public Rider CercaRiderPerId(int idRider) throws SQLException;
	public List<Object[]> getRiderDaSede(String idSede) throws SQLException; 
	public int NextIdRider() throws SQLException;
	public int InserisciRider(int idRider, String nome, String cognome, String telefono, String veicolo, String idSede) throws SQLException;
	public int EliminaRider(int idRider,String idSede) throws SQLException;
}
