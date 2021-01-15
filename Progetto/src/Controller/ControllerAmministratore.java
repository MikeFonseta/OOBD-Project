package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import DAO.AccountDAOPostgresImp;
import DAO.AllergeneDaoPostgresImp;
import DAO.EtichettaDAO;
import DAO.EtichettaDaoPostgresImp;
import DAO.ProdottoDAO;
import DAO.ProdottoDAOPostgresImp;
import DAO.RiderDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.Account;
import Entities.Prodotto;
import Entities.Rider;
import Entities.Sede;
import GUI.AggiungiAllergeniFrame;
import GUI.AggiungiProdottoFrame;
import GUI.AmministratoreFrame;
import GUI.CreaProdottoFrame;
import GUI.CreaSedeFrame;
import GUI.EliminaProdottoFrame;
import GUI.EliminaSedeFrame;
import GUI.GestioneProdottiFrame;
import GUI.GestioneRiderFrame;
import GUI.GestioneSedeFrame;
import GUI.ModificaProdottoFrame;


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
	private GestioneProdottiFrame gestioneProdottiFrame = null;
	private EliminaProdottoFrame eliminaProdottoFrame = null;
	private CreaProdottoFrame creaProdottoFrame = null;
	private ModificaProdottoFrame modificaProdottoFrame = null;
	private AggiungiAllergeniFrame aggiungiAllergeneFrame = null;
	private Account account;
	
	
	public ControllerAmministratore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		amministratoreFrame = new AmministratoreFrame(this);
		
	}
	
	public void ErroreFile(String Messaggio) {
		if(this.creaSedeFrame == null) {
			JOptionPane.showMessageDialog(this.gestioneSedeFrame,Messaggio,"Error",JOptionPane.ERROR_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this.creaSedeFrame,Messaggio,"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public List<String> getComuniProvincia(String Provincia) {
		return this.mainController.getComuniProvincia(Provincia);
	}
	
	public Object[] getProvince(){
		return this.mainController.getProvince();
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
		
	public void ApriGestioneProdottiFrame() {
		this.amministratoreFrame.setEnabled(false);
		this.amministratoreFrame.setVisible(false);
		this.gestioneProdottiFrame = new GestioneProdottiFrame(this);
	}
	
	public void ChiudiGestioneProdottiFrame(){
		this.gestioneProdottiFrame.dispose();
		this.amministratoreFrame.setEnabled(true);
		this.amministratoreFrame.setVisible(true);
	}
		
	public void ApriEliminaProdottoFrame(String NomeProdottoDaEliminare, int idProdottoDaEliminare) {
		this.gestioneProdottiFrame.setEnabled(false);
		this.eliminaProdottoFrame = new EliminaProdottoFrame(this,NomeProdottoDaEliminare, idProdottoDaEliminare);
	}
	
	public void ChiudiEliminaProdottoFrame() {
		this.eliminaProdottoFrame.dispose();
		this.gestioneProdottiFrame.setEnabled(true);
		this.gestioneProdottiFrame.setVisible(true);
	}
	
	public void ApriModificaSediFrame(int idSede) {
		
		Account gestoreSede = new Account();
		if(this.imp.equals(this.postgresImp))
		{
			AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			try {
				gestoreSede = accountDao.CercaAccountPerIdSede(idSede);
				this.gestioneSedeFrame = new GestioneSedeFrame(this,gestoreSede);
				this.amministratoreFrame.setVisible(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
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
				idProssimaSede = sedeDao.idProssimaSede();
				nomeUtenteGestore = accountDao.NomeUtentePerNuovaSede(idProssimaSede);
				this.amministratoreFrame.setEnabled(false);
				this.creaSedeFrame = new CreaSedeFrame(this,idProssimaSede,nomeUtenteGestore);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
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
	
	public void ApriCreaProdottoFrame() {
		int idProssimoProdotto = 0;
		if(this.imp == this.postgresImp) {
			try {
				ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
				idProssimoProdotto = prodottoDAO.idProssimoProdotto();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == this.altraImp){
		//altra implementazione
			}
			this.gestioneProdottiFrame.setEnabled(false);
			this.creaProdottoFrame = new CreaProdottoFrame(this, idProssimoProdotto);
	}
	
	public void ChiudiCreaProdottoFrame() {
		this.creaProdottoFrame.dispose();
		this.gestioneProdottiFrame.setEnabled(true);
		this.gestioneProdottiFrame .setVisible(true);
	}

	public void ApriAggiungiProdottoFrame(int idSede) {
		this.aggiungiProdottoFrame = new AggiungiProdottoFrame(this,idSede);
		this.gestioneSedeFrame.setEnabled(false);
	}

	public void ChiudiAggiungiProdottoFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.aggiungiProdottoFrame.dispose();
	}
	
	public void ApriModificaProdotto(int idProdotto) {
		Prodotto prodotto = null;
			if(this.imp == this.postgresImp) {
				ProdottoDAOPostgresImp ProdottoDAO = new ProdottoDAOPostgresImp();
				try {
					prodotto = ProdottoDAO.getProdottoPerId(idProdotto);
					this.gestioneProdottiFrame.dispose();
					this.modificaProdottoFrame = new ModificaProdottoFrame(this, prodotto);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (this.imp == this.altraImp) {
				//Altra implementazione
			}
	}
	
	public void ChiudiModificaProdotto() {
		this.modificaProdottoFrame.dispose();
		this.ApriGestioneProdottiFrame();
	}
	
	public void ApriAggiungiAllergeniFrame(int idProdotto) {
		this.modificaProdottoFrame.setEnabled(false);
		this.aggiungiAllergeneFrame = new AggiungiAllergeniFrame(this, idProdotto);
	}
	
	public void ChiudiAggiungiAllergeniFrame() {
		this.modificaProdottoFrame.setEnabled(true);
		this.aggiungiAllergeneFrame.dispose();
		this.modificaProdottoFrame.AggiornaTabellaAllergeni();	
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
		
		int risultato=0;
		
		Sede sede = new Sede(gestoreSede.getSede().getIdSede(),nomeSede,telefono,provincia,citta,via,numCivico);
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				
				risultato= sedeDao.AggiornaSede(sede,gestoreSede,nuovaPassword);
			
				if(risultato==2) {
					JOptionPane.showMessageDialog(this.gestioneSedeFrame,"Sede aggiornata!","",JOptionPane.PLAIN_MESSAGE);
					gestoreSede.setSede(new Sede(gestoreSede.getSede().getIdSede(),nomeSede,telefono,provincia,citta,via,numCivico));
					btnSalva.setEnabled(false);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		
		return risultato;
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
					this.amministratoreFrame.setEnabled(true);
					this.amministratoreFrame.setVisible(false);
					this.gestioneSedeFrame = new GestioneSedeFrame(this,account);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}
	
	public Object[][] getDatiSedi() {
		
		Object[][] risultato = null;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				risultato = sedeDao.getSedi().toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.amministratoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		return risultato;
	}
	
	public Object[][] getDatiProdotti() {
		
		Object[][] risultato= null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				risultato = prodottoDao.getTuttiProdotti().toArray(new Object[][] {});
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.aggiungiProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		return risultato;
	}
	
	public Object[][] getRiderDaSede(int idSede) {
		
		Object[][] risultato = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				risultato = riderDao.getRiderDaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		return risultato;
		
	}

	public Object[][] getMenuSede(int idSede) {
		
		Object[][] risultato = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				risultato = prodottoDao.getProdottiDellaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		return risultato;
	}

	public void getProdottiSedeCategoria(int idSede, String categoria) {
		Object[][] risultato = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				risultato = prodottoDao.getProdottiSedeCategoria(idSede,categoria).toArray(new Object[][] {});
			} catch (SQLException e) {
				this.gestioneSedeFrame.setEnabled(true);
				this.aggiungiProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		
		this.aggiungiProdottoFrame.AggiornaProdottiConCategoria(risultato);
	}
	
	public Object[][] getProdottiPerUnaSede(int idSede) {
		
		Object[][] risultato = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				risultato = prodottoDao.getProdottiPerUnaSede(idSede).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
		return risultato;
	}

	public void AggiungiProdottoASede(int idSede, int prodotti[]) {
		
		int risultato = 0;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				for(int i=0;i<prodotti.length;i++) {
				risultato+= sedeDao.aggiungiProdottoASede(idSede, prodotti[i]);
				}
				if(risultato==prodotti.length) {	
					if(prodotti.length==1) {
						JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Prodotto aggiunto!","",JOptionPane.PLAIN_MESSAGE);
					}else if(prodotti.length>1){
						JOptionPane.showMessageDialog(this.aggiungiProdottoFrame,"Prodotti aggiunti!","",JOptionPane.PLAIN_MESSAGE);
					}
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
		
		int risultato = 0;
		if(this.imp.equals(this.postgresImp))
		{
			SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
			try {
				risultato = sedeDao.EliminaProdottoDaSede(idSede, idProdotto);	
				if(risultato==1) {	
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
		
		int risultato = 0;
		this.eliminaSedeFrame.dispose();
		this.amministratoreFrame.setEnabled(true);
		
		if(this.account.getPassword().equals(password)) 
		{
			if(this.imp.equals(this.postgresImp)) 
			{
				SedeDAOPostgresImp sedeDao = new SedeDAOPostgresImp();
				try {
					risultato = sedeDao.EliminaSede(idSede);
					if(risultato == 1) 
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
		
		int risultato = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				risultato = riderDao.idProssimoRider();
				this.gestioneSedeFrame.setEnabled(false);
				this.gestioneRiderFrame = new GestioneRiderFrame(this,idSede,risultato);
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

		int risultato = 0;
		if(this.imp.equals(this.postgresImp)) 
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				risultato = riderDao.AggiornaRider(idRider,telefono,veicolo);			
				if(risultato == 1) {
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
		
		int risultato = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				risultato = riderDao.InserisciRider(idRider,nome,cognome,telefono,veicolo,idSede);
				if(risultato == 1) 
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
		
		int risultato = 0;
		
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				risultato = riderDao.EliminaRider(Integer.parseInt(idRider),idSede);
				if(risultato == 1) 
				{
					JOptionPane.showMessageDialog(this.amministratoreFrame,"Rider eliminato correttamente!","",JOptionPane.PLAIN_MESSAGE);
					this.gestioneSedeFrame.AggiornaRider();
				}
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneSedeFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altra implementazione
		}
	}
	
	public void ChiudiGestioneRiderFrame() {
		this.gestioneSedeFrame.setEnabled(true);
		this.gestioneRiderFrame.dispose();
	}
	
	public List<Integer> getIDProdottiPerAllergeni(String Allergeni) {
		List<Integer> risultato = new ArrayList<>();
			if(Allergeni.isBlank()== false) {
				String NomiAllergeni[] = Allergeni.split(","); 		
					if(this.imp == this.postgresImp) {
						try {
							ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
							risultato = prodottoDAO.getProdottiPerAllergeni(NomiAllergeni);
	
						}catch (SQLException e) {		
							JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(this.imp == this.altraImp)
					{ 
						//altra implementazione
					}				

			}
			else risultato = null;

		return risultato;

	}

	public Object[][] ricercaProdotto(String Categoria, Integer Min, Integer Max, List<Integer>idProdottiConAllergeni){
		Object[][] risultato = null;
		if(this.imp == postgresImp) {
			try {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				risultato = prodottoDAO.ricercaComplessaProdotti(Categoria, Min, Max, idProdottiConAllergeni).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == altraImp)
		{
			//altra implementazione
		}
		
		return risultato;
	}
	
	public void ConfermaEliminaProdotto(String password, int idProdotto, String nomeProdottoDaEliminare) {
		int risultato = 0;
		this.ChiudiEliminaProdottoFrame();
		if(this.account.getPassword().equals(password)) {
			if(this.imp == this.postgresImp) {
				ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
				try {
					risultato = prodottoDAO.eliminaProdottoDaTutteLeSedi(idProdotto);
					if(risultato == 1) {
						JOptionPane.showMessageDialog(this.gestioneProdottiFrame,"Prodotto '" + nomeProdottoDaEliminare + "' eliminato","",JOptionPane.PLAIN_MESSAGE);
						this.gestioneProdottiFrame.AggiornaTabella();
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(this.imp == this.altraImp)
			{
				//altra implementazione
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this.gestioneProdottiFrame,"Password non corretta!","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}	
			
	
	public void CreaProdotto(int idProssimoProdotto, String Nome, String Descrizione, float Prezzo, String Categoria) {
		int risultato = 0;
		Prodotto prodotto = new Prodotto(idProssimoProdotto, Nome, Descrizione, Prezzo, Categoria);
			if(this.imp == this.postgresImp) {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				try {
					risultato = prodottoDAO.CreaProdotto(idProssimoProdotto, Nome, Descrizione, Prezzo, Categoria);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.gestioneProdottiFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (this.imp == this.altraImp)
			{
				//altra implementazione
			}
		if(risultato == 1) {
			this.creaProdottoFrame.dispose();
			this.gestioneProdottiFrame.setEnabled(false);
			this.gestioneProdottiFrame.setVisible(false);
			this.modificaProdottoFrame = new ModificaProdottoFrame(this, prodotto);
		}
	}	
	
	public Object[][] getAllergeniMancanti(int idProdotto){
		Object[][] risultato = null;
		if(this.imp == this.postgresImp) {
			AllergeneDaoPostgresImp AllergeneDAO = new AllergeneDaoPostgresImp();
			try {
				risultato = AllergeneDAO.getAllergeniMancanti(idProdotto).toArray(new Object[][] {});
			} catch (SQLException e) {

				JOptionPane.showMessageDialog(this.modificaProdottoFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == this.altraImp) {
			//Altra implementazione
		}
		return risultato;
	}
	
	
	public void AggiungiAllergeniAProdotto(int idProdotto, String[] Allergeni) {
		int risultato = 0;
		if(this.imp == this.postgresImp) {
			try {
				for(int i=0; i<Allergeni.length; i++) {
					EtichettaDaoPostgresImp EtichettaDAO = new EtichettaDaoPostgresImp();
					risultato += EtichettaDAO.AggiungiAllergeneAProdotto(idProdotto, Allergeni[i]);
					}
				if(risultato!=0) {
					this.ChiudiAggiungiAllergeniFrame();
					if(risultato==1) {	
						JOptionPane.showMessageDialog(this.modificaProdottoFrame,"Allergene aggiunto!","",JOptionPane.PLAIN_MESSAGE);
					}else if(risultato>1){
						JOptionPane.showMessageDialog(this.modificaProdottoFrame,"Allergeni aggiunti!","",JOptionPane.PLAIN_MESSAGE);
					}
					
				}	
			}catch (SQLException e) {
				this.ChiudiAggiungiAllergeniFrame();
				JOptionPane.showMessageDialog(this.modificaProdottoFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == altraImp) {
		//Altra Implementazione 
		}	
	}
	
	
	public void EliminaAllergeniDaProdotto(int idProdotto, String[] Allergeni) {
		int risultato = 0;
		if(this.imp == this.postgresImp) {
			try {
				for(int i=0; i<Allergeni.length; i++) {
					EtichettaDaoPostgresImp EtichettaDAO = new EtichettaDaoPostgresImp();
					risultato += EtichettaDAO.EliminaAllergeneDaProdotto(idProdotto, Allergeni[i]);
					}
				if(risultato!=0) {
					if(risultato==1) {	
						JOptionPane.showMessageDialog(this.modificaProdottoFrame,"Allergene eliminato!","",JOptionPane.PLAIN_MESSAGE);
					}else if(risultato>1){
						JOptionPane.showMessageDialog(this.modificaProdottoFrame,"Allergeni eliminati!","",JOptionPane.PLAIN_MESSAGE);
					}			
				}	
				this.modificaProdottoFrame.AggiornaTabellaAllergeni();
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(this.modificaProdottoFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == altraImp) {
		//Altra Implementazione 
		}	
	}
	
	
	public Object[][] getAllergeniProdotto(int idProdotto) {
		Object[][] risultato = null;
		if(this.imp == this.postgresImp){
			EtichettaDaoPostgresImp EtichettaDAO = new EtichettaDaoPostgresImp();
			try {
				risultato = EtichettaDAO.getAllergeniProdotto(idProdotto).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.modificaProdottoFrame, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp == this.altraImp) {
		//Altra implementazione
		}
		return risultato;
	}
	
	
	public void AggiornaProdotto(int idProdotto, String nome, String descrizione, float prezzo, String categoria) {
		int risultato = 0;
		Prodotto prodotto = new Prodotto(idProdotto, nome, descrizione, prezzo, categoria);
		if(this.imp == this.postgresImp) {
			try {
				ProdottoDAOPostgresImp ProdottoDAO = new ProdottoDAOPostgresImp();
				risultato = ProdottoDAO.AggiornaProdotto(prodotto);
				if(risultato == 1) {
					ChiudiModificaProdotto();
					JOptionPane.showMessageDialog(this.gestioneProdottiFrame,"Prodotto aggiornato!","",JOptionPane.PLAIN_MESSAGE);
				}
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestioneProdottiFrame,"Error!","",JOptionPane.ERROR_MESSAGE);	
			}
		}
		else if(this.imp == this.altraImp) {
		//Altra implementazione
		}
		
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

