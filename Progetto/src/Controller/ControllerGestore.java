package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.AccountDAOPostgresImpl;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.AmministratoreFrame;

public class ControllerGestore {

	public AmministratoreFrame amministratoreFrame = null;
	
	private MainController mainController = null;
	private Account account;
	
	public ControllerGestore(MainController mainController) {
		this.mainController = mainController;
	}

	public void ApriClient(Account account) {
		this.account = account;
		this.amministratoreFrame.CaricaDati(account);
		
		List<Sede> sedi = new ArrayList<Sede>();
		
		SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
		sedi = sedeDao.getSedi();	
		
			
		this.amministratoreFrame = new AmministratoreFrame(this,sedi.toArray(new Object[][] {}));
		
	}


}
