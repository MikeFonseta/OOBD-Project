package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.SedeDAOPostgresImp;
import Entities.Account;
import Entities.Sede;
import GUI.AmministratoreFrame;

public class ControllerAmministratore {

    
	public AmministratoreFrame amministratoreFrame = null;
	
	private MainController mainController = null;
	private Account account;
	
	public ControllerAmministratore(MainController mainController) {
		this.mainController = mainController;
	}

	public void ApriClient(Account account) {
		this.account = account;
		this.amministratoreFrame.CaricaDati(account);
		
		List<Sede> sedi = new ArrayList<Sede>();
		
		SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
		sedi = sedeDao.getSedi();	
		System.out.println("ooo");	
		this.amministratoreFrame = new AmministratoreFrame(this,sedi.toArray(new Object[][] {}));
		
	}

	public void SetAccount(Account account) {
		this.amministratoreFrame.CaricaDati(account);
	}
	
	public void ApriClient() {
		this.amministratoreFrame.setVisible(true);
	}
	
}
