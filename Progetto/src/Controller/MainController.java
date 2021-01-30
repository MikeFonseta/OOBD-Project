package Controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import DAO.AccountDAOPostgresImp;
import DAO.OrdineDAOPostgresImp;
import DAO.ProdottoDAO;
import DAO.ProdottoDAOPostgresImp;
import DAO.RiderDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;
import GUI.VisualizzaCarrelloFrame;
import GUI.VisualizzaOrdiniFrame;
import Utility.DatiExcel;
import GUI.CreaOrdineFrame;

//simbolo euro : \u20AC
//a accentata  : \u00E0
//e accentata  : \u00E8
//u accentata  : \u00F9
//simbolo NULL : \u0000
public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	private ControllerGestore controllerGestore = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;
	private VisualizzaCarrelloFrame visualizzaCarrelloFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;

	public MainController() {
		//ApriLogin();		
		ProvaLogin("A001","pass12"); //amministratore
		//ProvaLogin("U00001","pass123");//gestore
	}
	
	public static void main(String[] args) {
		
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			  System.out.println("Error setting native LookAndFeelClassName: " + e);
		}
		
		
		MainController mainController = new MainController();
	}
	

	public void ProvaLogin(String NomeUtente, String password){
		
		Account account = new Account();
		
		AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			
		try {
			account = accountDao.ControlloCredenziali(NomeUtente, password);
			
			if(account != null)
			{
				if(account.getAmministratore() == false) {
					this.controllerGestore = new ControllerGestore(this, account);
				}else {
					this.controllerAmministratore = new ControllerAmministratore(this,account);
				}
				this.loginFrame.setVisible(false);
				
			}  
			else 
			{
				JOptionPane.showMessageDialog(this.loginFrame,"Credenziali invalide!","Errore",JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this.loginFrame,"Credenziali invalide!","Errore",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ApriLogin() {
		this.controllerGestore = null;
		this.controllerAmministratore = null;
		this.loginFrame.setVisible(true);
		this.loginFrame.getTxfNomeUtente().setText("");
		this.loginFrame.getPsfPassword().setText("");
	}
	
	public void ChiudiLogin() {
		this.loginFrame.dispose();
	}
	
		
	public void ApriVisualizzaOrdiniFrame() {
		if(this.controllerAmministratore!= null)	
			this.controllerAmministratore.getAmministratoreFrame().setVisible(false);
		else
			this.controllerGestore.getGestoreFrame().setVisible(false);
		visualizzaOrdiniFrame = new VisualizzaOrdiniFrame(this);
	}

	public void ChiudiVisualizzaOrdiniFrame() {
		this.visualizzaOrdiniFrame.dispose();
		if(this.controllerAmministratore!= null)	{
			this.controllerAmministratore.getAmministratoreFrame().setVisible(true);
			this.controllerAmministratore.getAmministratoreFrame().setEnabled(true);
		}
		else {
			this.controllerGestore.getGestoreFrame().setVisible(true);
			this.controllerGestore.getGestoreFrame().setEnabled(true);
		}
	}
	
	
	public void ApriVisualizzaCarrelloFrame(int idOrdine) {
		if(this.visualizzaOrdiniFrame != null)
			this.visualizzaOrdiniFrame.setEnabled(false);
		else 
			this.controllerGestore.getGestoreFrame().setEnabled(false);

		visualizzaCarrelloFrame = new VisualizzaCarrelloFrame(this,idOrdine);
	}
		
	
	public void ChiudiVisualizzaCarrelloFrame() {
		if(this.visualizzaOrdiniFrame != null)
			this.visualizzaOrdiniFrame.setEnabled(true);
		else 
			this.controllerGestore.getGestoreFrame().setEnabled(true);

		this.visualizzaCarrelloFrame.dispose();
		
	}
	
	public void ChiudiCreaOrdineFrame() {
		creaOrdineFrame.setVisible(false);
	}
	
		
	public Object[][] getProdottiCarrello(int idOrdine){
		Object[][] risultato = null;
		if(controllerAmministratore != null) {
			if(controllerAmministratore.getImp() == controllerAmministratore.getPostgresImp()) {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				try {
					risultato = prodottoDAO.getProdottiPerId_Ordine(idOrdine).toArray(new Object[][] {});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(controllerAmministratore.getImp() == controllerAmministratore.getAltraImp()){
			
			}
		}
		else {
			if(controllerGestore.getImp() == controllerGestore.getPostgresImp()) {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				try {
					risultato = prodottoDAO.getProdottiPerId_Ordine(idOrdine).toArray(new Object[][] {});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(controllerGestore.getImp() == controllerGestore.getAltraImp()) {
			
			}
		}
		return risultato;
		
		
	}
		
	String[] convertiInArrayStringhe(List<String> list) {
	String risultato[] = new String[list.size()];
	
		for(int i=0;i<list.size();i++)	
			risultato[i] = list.get(i);	
	return risultato;
	}
	
	
	public String[] getIDSedi() {
		List<String> IDsedi = new ArrayList<String>();
		String[] risultato = null;
		
		if(controllerAmministratore != null) {

			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
				SedeDAOPostgresImp SedeDAOPostgres = new SedeDAOPostgresImp();
				try {
					IDsedi = SedeDAOPostgres.CercaTutteLeSedi();
					IDsedi.add(0, "Tutte Le Sedi");
					risultato = convertiInArrayStringhe(IDsedi);
				} catch (SQLException e) {
					
					//Inserito cosi come ho fatto in controllerAmministratore per non farti avere l'errore nella classe
					JOptionPane.showMessageDialog(this.loginFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); 
				}
			}		
			else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {
			//Altra implementazione
			}
	    }
		else {
					risultato = new String[] { String.valueOf(controllerGestore.getAccount().getSede().getIdSede()) };			
		}
		return risultato;
	}

	public List<Integer> getID_ProdottiPerNomeP(String NomiP) {
		List<Integer> risultato = new ArrayList<>();
		if(NomiP.isBlank()== false) {
			String Nprodotti[] = NomiP.split(","); 		
			if(controllerAmministratore != null) {
				if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
					ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
						try {
							risultato = prodottoDAO.getTuttiProdottiPerNome(Nprodotti);
							
						} catch (SQLException e) {	
							e.printStackTrace();
						}
				}
				else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()){ //AltraImpl
					
				}				

			}
			else {
				ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
				try {
					risultato = prodottoDAO.getTuttiProdottiPerNome(Nprodotti);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
			else risultato = null;
		
		return risultato;
	}
		
	public Object[][] getOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) {
		Object[][] risultato = null;		
			if(controllerAmministratore != null) {
				if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
					OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
					try {
						risultato = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max).toArray(new Object[][] {});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {//Altra Impl
					
				}
			}
			else {
					if(controllerGestore.getImp() == controllerGestore.getPostgresImp()) {
						OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
						try {
							risultato = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max).toArray(new Object[][] {});
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(controllerGestore.getImp() == controllerGestore.getAltraImp()) {
						
					}
			}
		
		return risultato;
	}

	public List<Integer> getIDProdottiPerAllergeni(String Allergeni) {
		List<Integer> risultato = new ArrayList<>();
			if(Allergeni.isBlank()== false) {
				String NomiAllergeni[] = Allergeni.split(","); 
				if(this.controllerAmministratore != null) {
					if(this.controllerAmministratore.getImp() == this.controllerAmministratore.getPostgresImp()) {
						try {
							ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
							risultato = prodottoDAO.getProdottiPerAllergeni(NomiAllergeni);
	
						}catch (SQLException e) {		
							JOptionPane.showMessageDialog(this.controllerAmministratore.getGestioneProdottiFrame(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(this.controllerAmministratore.getImp() == this.controllerAmministratore.getAltraImp())
					{ 
						//altra implementazione
					}				
				}
				else {// controller gestore
					if(this.controllerGestore.getImp() == this.controllerGestore.getPostgresImp()) {
						try {
							ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
							risultato = prodottoDAO.getProdottiPerAllergeni(NomiAllergeni);
	
						}catch (SQLException e) {		
							JOptionPane.showMessageDialog(this.controllerGestore.getGestioneProdottiFrame(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(this.controllerGestore.getImp() == this.controllerGestore.getAltraImp())
					{ 
						//altra implementazione
					}									
				}
			}
			else risultato = null;

		return risultato;

	}

	public Object[][] ricercaProdotto(String Categoria, Integer Min, Integer Max, Integer idSede, List<Integer>idProdottiConAllergeni){
		Object[][] risultato = null;
		if(this.controllerAmministratore!= null) {
			if(this.controllerAmministratore.getImp() == this.controllerAmministratore.getPostgresImp()) {
				try {
					ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
					risultato = prodottoDAO.ricercaComplessaProdotti(Categoria, Min, Max, idSede, idProdottiConAllergeni).toArray(new Object[][] {});
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.controllerAmministratore.getGestioneProdottiFrame(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(this.controllerAmministratore.getImp() == this.controllerAmministratore.getAltraImp()){
				//altra implementazione
			}
		}
		else {
			if(this.controllerGestore.getImp() == this.controllerGestore.getPostgresImp()){
				try {
					ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
					risultato = prodottoDAO.ricercaComplessaProdotti(Categoria, Min, Max, idSede, idProdottiConAllergeni).toArray(new Object[][] {});
				} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.controllerGestore.getGestioneProdottiFrame(),e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(this.controllerGestore.getImp() == this.controllerGestore.getAltraImp()){
				//altra implementazione
			
			}
		}
		return risultato;
	}
		
	public String[] getCategorie() {
		
		String[] categorie = null;
		if(controllerGestore != null) {
			if(controllerGestore.getImp()==controllerGestore.getPostgresImp())
			{
				
				ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
				try {
					categorie = prodottoDao.getCategorieProdotto(controllerGestore.getAccount().getSede().getIdSede()).toArray(new String[] {});
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}else if(controllerGestore.getImp()==controllerGestore.getAltraImp())
			{
				//altre implementazioni
			}
		}
		else {//controllerAmministratore
			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp())
			{
				
				ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
				try {
					categorie = prodottoDao.getCategorieTotali().toArray(new String[] {});
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp())
			{
				//altre implementazioni
			}
			
		}
		
		
		return categorie;
	}
	
	
	public Object[] getProvince() {
		
		List<String> risultato = new ArrayList<String>();
		DatiExcel DE = new DatiExcel();
		try {
			risultato = DE.ottieniProvince();
		} catch (IOException e) {
			if(this.controllerAmministratore != null) {
				this.controllerAmministratore.Errore(e.getMessage());
			}else {
				//ControllerGestore
			}
		}
		
		return risultato.toArray();
	}
	
	public List<String> getComuniProvincia(String Provincia) {
		
		List<String> risultato = new ArrayList<String>();
		DatiExcel DE = new DatiExcel();
		try {
			risultato = DE.ottieniComuniProvincia(Provincia);
		} catch (IOException e) {
			if(this.controllerAmministratore != null) {
				this.controllerAmministratore.Errore(e.getMessage());
			}else {
				//ControllerGestore
			}
		}
		
		return risultato;
	}

	
	
	public String[] getVeicoli() {
		
		
		String[] veicoli=null;
		
		if(controllerGestore != null) {
			if(controllerGestore.getImp()==controllerGestore.getPostgresImp())
			{
				
				RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
				try {
					veicoli = riderDao.getVeicoli().toArray(new String[] {});
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}else if(controllerGestore.getImp()==controllerGestore.getAltraImp())
			{
				//altre implementazioni
			}
		}
		else {//controllerAmministratore
			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp())
			{
				
				RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
				try {
					veicoli = riderDao.getVeicoli().toArray(new String[] {});
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp())
			{
				//altre implementazioni
			}
			
		}
		
		return veicoli;
		
	}
	





}