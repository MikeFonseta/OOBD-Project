package DAO;

import java.util.List;

import Entities.Rider;

public interface RiderDAO {

	public Rider CercaRiderPerId(int idRider);
	public List<Object[]> getRiderDaSede(String idSede); 
	public int NextIdRider();
	public int InserisciRider(int idRider, String nome, String cognome, String telefono, String veicolo, String idSede);
	public int EliminaRider(int idRider,String idSede);
}
