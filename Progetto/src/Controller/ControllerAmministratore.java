package Controller;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

import DAO.AccountDAOPostgresImp;
import DAO.ProdottoDAOPostgresImp;
import DAO.RiderDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.Account;
import Entities.Prodotto;
import Entities.Rider;
import Entities.Sede;
import GUI.AggiungiProdottoFrame;
import GUI.AmministratoreFrame;
import GUI.EliminaSedeFrame;
import GUI.GestioneRiderFrame;
import GUI.GestioneSedeFrame;
import GUI.VisualizzaOrdiniFrame;

public class ControllerAmministratore {
	
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	public AmministratoreFrame amministratoreFrame = new AmministratoreFrame(this);
	private MainController mainController = null;
	private GestioneSedeFrame gestioneSedeFrame = null;
	private AggiungiProdottoFrame aggiungiProdottoFrame = null;
	private EliminaSedeFrame eliminaSedeFrame = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;
	private GestioneRiderFrame gestioneRiderFrame = null;
	private Account account;
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		
	}
	
	public void chiudiAmministratoreFrame() {
		this.amministratoreFrame.dispose();
		this.mainController.ApriLogin();
		
	}
	
	public void ApriModificaSediFrame(String idSede) {
		
		Sede sede = new Sede();
		Account gestoreSede = new Account();
		if(this.imp.equals(this.postgresImp))
		{
			AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			gestoreSede = accountDao.CercaAccountPerIdSede(idSede);
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		
		this.gestioneSedeFrame = new GestioneSedeFrame(this,gestoreSede);
		this.amministratoreFrame.setVisible(false);
	}
	
//	public void ApriCreazioneSedeFrame() {
//		this.gestioneSedeFrame = new GestioneSedeFrame(this);
//	}
	
	public void ApriVisualizzaOrdiniFrame() {
		this.amministratoreFrame.setEnabled(false);
		//this.mainController.ApriVisualizzaOrdiniFrame(); ????????????????????????
	}
	
	public void ChiudiVisualizzaOrdiniFrame() {
		this.amministratoreFrame.setEnabled(true);
		//this.mainController.ChiudiVisualizzaOrdiniFrame(); ????????????????????????
	}
	
	public void ChiudiGestioneSedeFrame() {
		this.gestioneSedeFrame.dispose();
		this.amministratoreFrame.setVisible(true);
	}

	public void ApriAggiungiProdottoFrame(String idSede) {
		this.aggiungiProdottoFrame = new AggiungiProdottoFrame(this,idSede);
		this.gestioneSedeFrame.setEnabled(false);
	}
	
	public void ChiudiAggiungiProdottoFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.aggiungiProdottoFrame.dispose();
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
	
	public Object[][] getRiderDaSede(String idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			result = riderDao.getRiderDaSede(idSede).toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
		
	}

	public Object[][] getMenuSede(String idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			result = prodottoDao.getProdottiDellaSede(idSede).toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}

	public void getProdottiSedeCategoria(String idSede, String categoria) {
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			result = prodottoDao.getProdottiSedeCategoria(idSede,categoria).toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		
		this.aggiungiProdottoFrame.AggiornaProdottiConCategoria(result);
	}
	
	public Object[][] getProdottiPerUnaSede(String idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			result = prodottoDao.getProdottiPerUnaSede(idSede).toArray(new Object[][] {});
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}

	public void AggiungiProdottoASede(String idSede, int idProdotto) {
		
		int result;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.aggiungiProdottoASede(idSede, idProdotto);
			
			if(result==1) {
				
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Prodotto aggiunto!","",JOptionPane.PLAIN_MESSAGE);
				this.gestioneSedeFrame.AggiornaProdotti();
				this.aggiungiProdottoFrame.AggiornaProdotti();
			}else {
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Operazione fallita","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}
	
	public void EliminaProdottoDaSede(String idSede, int idProdotto) {
		
		int result;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.EliminaProdottoDaSede(idSede, idProdotto);
			
			if(result==1) {
				
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Prodotto eliminato!","",JOptionPane.PLAIN_MESSAGE);
				this.gestioneSedeFrame.AggiornaProdotti();
			}else {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Operazione fallita","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}
	
	public void EliminaSede(String idSede) {
		this.amministratoreFrame.setEnabled(false);
		this.eliminaSedeFrame = new EliminaSedeFrame(this,idSede);
	}
	
	public void ConfermaEliminazioneSede(String password,String idSede) {
		
		int result = 0;
		
		this.eliminaSedeFrame.dispose();
		this.amministratoreFrame.setEnabled(true);
		
		if(this.account.getPassword().equals(password)) 
		{
			if(this.imp.equals(this.postgresImp)) 
			{
				SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
				result = sedeDao.EliminaSede(idSede);
				
				if(result == 1) 
				{
					JOptionPane.showMessageDialog(this.amministratoreFrame,"Sede '" + idSede + "' eliminata","",JOptionPane.PLAIN_MESSAGE);
					this.amministratoreFrame.AggiornaSedi();
				}else
				{
					JOptionPane.showMessageDialog(this.amministratoreFrame,"Operazione fallita","",JOptionPane.ERROR_MESSAGE);
				}
		
			}else
			{
				//altra implementazione
			}
		}else
		{
			JOptionPane.showMessageDialog(this.amministratoreFrame,"Password non corretta!","",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void ChiudiEliminaSedeFrame() {
		this.amministratoreFrame.setEnabled(true);
		this.eliminaSedeFrame.dispose();
	}

	public int getIdProssimoRider() {
		return 6;
	}

	public void ApriNuovoRiderFrame() {
		this.gestioneSedeFrame.setEnabled(false);
		this.gestioneRiderFrame = new GestioneRiderFrame(this);
	}
	
	public void ApriModificaRiderFrame(String idRider,String idSede) {
		Rider rider = new Rider();
		
		if(this.imp.equals(this.postgresImp)) 
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			rider = riderDao.CercaRiderPerId(Integer.parseInt(idRider));
				
		}else
		{
			//altra implementazione
		}
		
		this.gestioneRiderFrame = new GestioneRiderFrame(this,rider);
		this.gestioneSedeFrame.setEnabled(false);
	}
	
	public void ChiudiGestioneRiderFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.gestioneRiderFrame.dispose();
	}

		
	
}

