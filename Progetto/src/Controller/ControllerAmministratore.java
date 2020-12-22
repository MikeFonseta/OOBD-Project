package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.SedeDAOPostgresImp;
import Entities.Account;
import Entities.Sede;
import GUI.AmministratoreFrame;

public class ControllerAmministratore {

 
	public AmministratoreFrame amministratoreFrame = new AmministratoreFrame(this);
	
	private MainController mainController = null;
	private Account account;
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		
	}
	
	public Object[][] getDatiSedi() {
		
		List<Sede> sedi = null;
		SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
		return sedeDao.getSedi().toArray(new Object[][] {});
	}
	
	
}

