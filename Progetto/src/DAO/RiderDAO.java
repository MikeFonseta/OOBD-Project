package DAO;

import java.util.List;

import Entities.Rider;

public interface RiderDAO {

	public Rider CercaRiderPerId(int idRider);
	public List<Object[]> getRiderDaSede(String idSede); 
}
