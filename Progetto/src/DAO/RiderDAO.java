package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Rider;

public interface RiderDAO {

	public Rider CercaRiderPerId(int idRider) throws SQLException;
	public List<Object[]> getRiderDaSede(int idSede) throws SQLException; 
	public int idProssimoRider() throws SQLException;
	public int InserisciRider(int idRider, String nome, String cognome, String telefono, String veicolo, int idSede) throws SQLException;
	public int EliminaRider(int idRider,int idSede) throws SQLException;
	public int AggiornaRider(int idRider, String telefono, String veicolo) throws SQLException;
	public List<Object[]> getRiderPerSede(int idSede) throws SQLException ;
	public void AggiornaDisposizione(int idRider,boolean disponibile) throws SQLException;
	public int AggiornaNumeroOrdini(int idRider,boolean aumenta)throws SQLException;
	public void Assegnazione(int idOrdine,int idRider) throws SQLException;
	public void Dissociazione(int idOrdine,int idRider) throws SQLException;
}
