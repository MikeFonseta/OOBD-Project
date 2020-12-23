package Controller;

import java.util.ArrayList;
import java.util.List;

import DAO.ProdottoDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.Account;
import Entities.Prodotto;
import Entities.Sede;
import GUI.AggiungiProdottoFrame;
import GUI.AmministratoreFrame;
import GUI.GestioneSedeFrame;

public class ControllerAmministratore {
	
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	public AmministratoreFrame amministratoreFrame = new AmministratoreFrame(this);
	private MainController mainController = null;
	private GestioneSedeFrame gestioneSedeFrame = null;
	private AggiungiProdottoFrame aggiungiProdottoFrame = null;
	private Account account;
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		
	}
	
	public Object[][] getDatiSedi() {
		
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.getSedi().toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}
	
	public Object[][] getDatiProdotti() {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			result = prodottoDao.getTuttiProdotti().toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}
	
	public void ApriGestioneSedi(String idSede) {
		gestioneSedeFrame = new GestioneSedeFrame(this,idSede);
	}

	public Object[][] getProdottiDaSede(String idSede) {
		return null;
	}

	public Object[][] getRiderDaSede(String idSede) {
		return null;
	}

	public void ApriAggiungiProdottoFrame() {
		aggiungiProdottoFrame = new AggiungiProdottoFrame(this);
		
	}
	
	
}

