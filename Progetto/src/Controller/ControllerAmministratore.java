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
	private GestioneSedeFrame gestioneSede = null;
	private AggiungiProdottoFrame aggiungiProdotto = null;
	private Account account;
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		
	}
	
	public Object[][] getDatiSedi() {
		
		List<Sede> sedi = null;
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.getSedi().toArray(new Object[][] {});
			System.out.println("Ciao");
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}
	
	public Object[][] getProdotti() {
		
		List<Prodotto> prodotti = null;
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			result = prodottoDao.getTuttiProdotti().toArray(new Object[][] {});
			System.out.println("Ciao");
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}
	
	public void ApriGestioneSedi(String idSede) {
		gestioneSede = new GestioneSedeFrame(this,idSede);
	}

	public Object[][] getProdottiDaSede(String idSede) {
		return null;
	}

	public Object[][] getRiderDaSede(String idSede) {
		return null;
	}
	
	
}

