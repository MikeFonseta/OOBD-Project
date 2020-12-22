package DAO;

import java.util.List;

import Entities.Sede;

public interface SedeDAO {
	
	public Sede CercaSedePerId(String id);
	public List<Sede> getSedi();

}
