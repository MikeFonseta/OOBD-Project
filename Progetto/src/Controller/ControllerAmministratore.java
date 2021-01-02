package Controller;

import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import DAO.AccountDAOPostgresImp;
import DAO.ProdottoDAOPostgresImp;
import DAO.RiderDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Database.DBConnection;
import Entities.Account;
import Entities.Prodotto;
import Entities.Rider;
import Entities.Sede;
import GUI.AggiungiProdottoFrame;
import GUI.AmministratoreFrame;
import GUI.CreaSedeFrame;
import GUI.EliminaSedeFrame;
import GUI.GestioneRiderFrame;
import GUI.GestioneSedeFrame;
import GUI.VisualizzaOrdiniFrame;

public class ControllerAmministratore {
	
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	public AmministratoreFrame amministratoreFrame = null;
	private MainController mainController = null;
	private GestioneSedeFrame gestioneSedeFrame = null;
	private AggiungiProdottoFrame aggiungiProdottoFrame = null;
	private EliminaSedeFrame eliminaSedeFrame = null;
	private GestioneRiderFrame gestioneRiderFrame = null;
	private CreaSedeFrame creaSedeFrame=null;
	private Account account;
	
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		
		this.mainController = mainController;
		this.account = account;
		amministratoreFrame = new AmministratoreFrame(this);
		
	}
	
	public Account getAccount() {
		return this.account;
	}

	public void ApriVisualizzaOrdini() {
		this.amministratoreFrame.setEnabled(false);
		this.mainController.ApriVisualizzaOrdiniFrame(); 
	}	

	public void ChiudiVisualizzaOrdini() {
		this.amministratoreFrame.setEnabled(true);
		this.mainController.ChiudiVisualizzaOrdiniFrame();
	}
	
	public void ApriModificaSediFrame(int idSede) {
		
		Account gestoreSede = new Account();
		if(this.imp.equals(this.postgresImp))
		{
			AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			gestoreSede = accountDao.CercaAccountPerIdSede(idSede);
			
			this.gestioneSedeFrame = new GestioneSedeFrame(this,gestoreSede);
			this.amministratoreFrame.setVisible(false);
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}

	}
		
    public void ApriCreazioneSedeFrame() {
    	
		int idProssimaSede;
		String nomeUtenteGestore=null;
		
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			try {
				idProssimaSede = sedeDao.ProssimoIdSede();
				nomeUtenteGestore = accountDao.NomeUtentePerNuovaSede(idProssimaSede);
				this.amministratoreFrame.setEnabled(false);
				this.creaSedeFrame = new CreaSedeFrame(this,idProssimaSede,nomeUtenteGestore);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(this.imp.equals(this.altraImp))
		{
			
		}
    	
    }

	public void ChiudiCreaSedeFrame() {
		this.amministratoreFrame.setEnabled(true);
		this.creaSedeFrame.dispose();
	}
    
	public void ChiudiGestioneSedeFrame() {
		this.gestioneSedeFrame.dispose();
		this.amministratoreFrame.setVisible(true);
		this.amministratoreFrame.AggiornaSedi();
	}

	public void ApriEliminaSedeFrame(int idSede) {
		this.amministratoreFrame.setEnabled(false);
		this.eliminaSedeFrame = new EliminaSedeFrame(this,idSede);
	}
	
	public void ChiudiEliminaSedeFrame() {
		this.amministratoreFrame.setEnabled(true);
		this.eliminaSedeFrame.dispose();
	}
	
	public void ApriAggiungiProdottoFrame(int idSede) {
		this.aggiungiProdottoFrame = new AggiungiProdottoFrame(this,idSede);
		this.gestioneSedeFrame.setEnabled(false);
	}

	public void ChiudiAggiungiProdottoFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.aggiungiProdottoFrame.dispose();
	}
	
	
	public void chiudiAmministratoreFrame(boolean logout) {
		
		if(logout==true) {
			this.mainController.ApriLogin();
		}else {
			this.mainController.loginFrame.dispose();
		}
		this.amministratoreFrame.dispose();
	}

	public int SalvaSede(JButton btnSalva, String nomeSede, String telefono,String provincia, String citta, String via, String numCivico,Account gestoreSede, String nuovaPassword) {
		
		int result=0;
		
		Sede sede = new Sede(gestoreSede.getSede().getIdSede(),nomeSede,telefono,provincia,citta,via,numCivico);
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				result = sedeDao.AggiornaSede(sede,gestoreSede,nuovaPassword);
				//PROVVISORIO
				
				if(result==2) {
					JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Sede aggiornata!","",JOptionPane.PLAIN_MESSAGE);
					gestoreSede.setSede(new Sede(gestoreSede.getSede().getIdSede(),nomeSede,telefono,provincia,citta,via,numCivico));
					btnSalva.setEnabled(false);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		
		return result;
	}
	
	public void CreaSede(int idSede,String nomeSede, String telefono,String provincia, String citta, String via, String numCivico, String nomeUtente,String Password) {
	
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				Sede sede = new Sede(idSede,nomeSede,telefono,provincia,citta,via,numCivico);
				Account account = new Account(nomeUtente,Password,false,sede);
				if(sedeDao.CreaSede(sede, nomeUtente, Password)==2) {
					this.creaSedeFrame.dispose();
					this.gestioneSedeFrame = new GestioneSedeFrame(this,account);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
	}
	
	public Object[][] getDatiSedi() {
		
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				result = sedeDao.getSedi().toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
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
			try {
				result = prodottoDao.getTuttiProdotti().toArray(new Object[][] {});
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.aggiungiProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}
	
	public Object[][] getRiderDaSede(int idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.getRiderDaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
		
	}

	public Object[][] getMenuSede(int idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				result = prodottoDao.getProdottiDellaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}

	public void getProdottiSedeCategoria(int idSede, String categoria) {
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				result = prodottoDao.getProdottiSedeCategoria(idSede,categoria).toArray(new Object[][] {});
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.aggiungiProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		
		this.aggiungiProdottoFrame.AggiornaProdottiConCategoria(result);
	}
	
	public Object[][] getProdottiPerUnaSede(int idSede) {
		
		Object[][] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				result = prodottoDao.getProdottiPerUnaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazioni
		}
		return result;
	}

	public void AggiungiProdottoASede(int idSede, int idProdotto) {
		
		int result = 0;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				result = sedeDao.aggiungiProdottoASede(idSede, idProdotto);
				
				if(result==1) {	
					JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Prodotto aggiunto!","",JOptionPane.PLAIN_MESSAGE);
					this.gestioneSedeFrame.AggiornaProdotti();
					this.aggiungiProdottoFrame.AggiornaProdotti();
				}
				
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.aggiungiProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}
	
	public void EliminaProdottoDaSede(int idSede, int idProdotto) {
		
		int result = 0;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				result = sedeDao.EliminaProdottoDaSede(idSede, idProdotto);
				
				if(result==1) {
					
					JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Prodotto eliminato!","",JOptionPane.PLAIN_MESSAGE);
					this.gestioneSedeFrame.AggiornaProdotti();
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
	
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}

	public void ConfermaEliminazioneSede(String password,int idSede) {
		
		int result = 0;
		
		this.eliminaSedeFrame.dispose();
		this.amministratoreFrame.setEnabled(true);
		
		if(this.account.getPassword().equals(password)) 
		{
			if(this.imp.equals(this.postgresImp)) 
			{
				SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
				try {
					result = sedeDao.EliminaSede(idSede);
					
					if(result == 1) 
					{
						JOptionPane.showMessageDialog(this.amministratoreFrame,"Sede '" + idSede + "' eliminata","",JOptionPane.PLAIN_MESSAGE);
						this.amministratoreFrame.AggiornaSedi();
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

	
	public void ApriNuovoRiderFrame(int idSede) {
		
		int result = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.ProssimoIdRider();
				this.gestioneSedeFrame.setEnabled(false);
				this.gestioneRiderFrame = new GestioneRiderFrame(this,idSede,result);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		
	}
	
	public void ApriModificaRiderFrame(String idRider,int idSede) {
		Rider rider = new Rider();
		

		if(this.imp.equals(this.postgresImp)) 
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				rider = riderDao.CercaRiderPerId(Integer.parseInt(idRider));
				this.gestioneRiderFrame = new GestioneRiderFrame(this,rider);
				this.gestioneSedeFrame.setEnabled(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
				
		}else
		{
			//altra implementazione
		}
		
	}
	
	public void AggiornaRider(int idRider, String telefono, String veicolo) {

		int result = 0;

		if(this.imp.equals(this.postgresImp)) 
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.AggiornaRider(idRider,telefono,veicolo);
				
				if(result == 1) {
					this.gestioneSedeFrame.setEnabled(true);
					this.gestioneRiderFrame.dispose();
					this.gestioneSedeFrame.AggiornaRider();
					JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Rider aggiornato!","",JOptionPane.PLAIN_MESSAGE);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
				
		}else
		{
			//altra implementazione
		}
		
	}
	
	public void CreaRider(int idRider, String nome, String cognome, String telefono, String veicolo, int idSede) {
		
		int result = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.InserisciRider(idRider,nome,cognome,telefono,veicolo,idSede);
				
				if(result == 1) 
				{
					this.gestioneSedeFrame.setEnabled(true);
					this.gestioneRiderFrame.dispose();
					this.gestioneSedeFrame.AggiornaRider();
					JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Rider inserito correttamente!","",JOptionPane.PLAIN_MESSAGE);
				}
				
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.gestioneRiderFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		
	}
	
	public void EliminaRider(int idSede, String idRider) {
		
		int result = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.EliminaRider(Integer.parseInt(idRider),idSede);
				
				if(result == 1) 
				{
					JOptionPane.showMessageDialog(this.amministratoreFrame,"Rider eliminato correttamente!","",JOptionPane.PLAIN_MESSAGE);
					this.gestioneSedeFrame.AggiornaRider();
				}
				
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(this.imp.equals(this.altraImp))
		{
			
		}
	}
	
	public void ChiudiGestioneRiderFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.gestioneRiderFrame.dispose();
	}

	//getter e setter
	public String getImp() {
		return imp;
	}

	public String getPostgresImp() {
		return postgresImp;
	}

	public String getAltraImp() {
		return altraImp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}

	public void setPostgresImp(String postgresImp) {
		this.postgresImp = postgresImp;
	}

	public void setAltraImp(String altraImp) {
		this.altraImp = altraImp;
	}


}

