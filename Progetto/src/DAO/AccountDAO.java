package DAO;

import Entities.Account;

public interface AccountDAO{
	
	public Account ControlloCredenziali(String username, String Password);
	public Account CercaAccountPerIdSede(int idSede);
}
