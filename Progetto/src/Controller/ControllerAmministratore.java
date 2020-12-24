package Controller;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
	

	public Object[][] getRiderDaSede(String idSede) {
		return null;
	}
	
	public void ApriGestioneSedi(String idSede) {
		this.gestioneSedeFrame = new GestioneSedeFrame(this,idSede);
		this.amministratoreFrame.setVisible(false);
	}

	public void ApriAggiungiProdottoFrame(String idSede) {
		this.aggiungiProdottoFrame = new AggiungiProdottoFrame(this,idSede);
		this.gestioneSedeFrame.setEnabled(false);
	}
	
	public void ChiudiGestioneSedeFrame() {
		this.gestioneSedeFrame.dispose();
		this.amministratoreFrame.setVisible(true);
	}
	
	public void ChiudiAggiungiProdottoFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.aggiungiProdottoFrame.dispose();
	}
	

	public Object[][] getMenùSede(String idSede) {
		
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

	public void aggiungiProdottoASede(String idSede, int idProdotto) {
		
		int result;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.aggiungiProdottoASede(idSede, idProdotto);
			
			if(result==1) {
				
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Operazione effettuata con successo","",JOptionPane.PLAIN_MESSAGE);
				this.gestioneSedeFrame.AggiornaProdotti();
				this.aggiungiProdottoFrame.AggiornaProdotti();
			}else {
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Operazione fallita","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
	}
	
	public void EliminaProdottoDaSede(String idSede, int idProdotto) {
		
		int result;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			result = sedeDao.EliminaProdottoDaSede(idSede, idProdotto);
			
			if(result==1) {
				
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Operazione effettuata con successo","",JOptionPane.PLAIN_MESSAGE);
				this.gestioneSedeFrame.AggiornaProdotti();
			}else {
				JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Operazione fallita","",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
	}

	
	
	
}

