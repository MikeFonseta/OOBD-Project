package Controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import DAO.AccountDAOPostgresImp;
import DAO.OrdineDAOPostgresImp;
import DAO.ProdottoDAO;
import DAO.ProdottoDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;
import GUI.VisualizzaCarrelloFrame;
import GUI.VisualizzaOrdiniFrame;
import GUI.VisualizzaProdottiFrame;
import GUI.CreaOrdineFrame;

//simbolo euro : \u20AC
//a accentata  : \u00E0
//u accentata  : \u00F9
//simbolo NULL : \u0000
public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	//private AdminController AdminController = null;
	private ControllerGestore controllerGestore = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;
	private VisualizzaCarrelloFrame visualizzaCarrelloFrame = null;
	private VisualizzaProdottiFrame visualizzaProdottiFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;

	public MainController() {
		ApriLogin();
		//LoginTry("A001","pass12"); //amministratore
		//LoginTry("U001","pass123");//gestore
	}
	
	public static void main(String[] args) {
		
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			  System.out.println("Error setting native LookAndFeelClassName: " + e);
		}
		
		
		MainController mainController = new MainController();
	}
	

	public void LoginTry(String NomeUtente, String password){
		
		Account account = new Account();
		
		AccountDAOPostgresImp accountDao = new AccountDAOPostgresImp();
			
		account = accountDao.ControlloCredenziali(NomeUtente, password);
			if(account != null){
				
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
			this.controllerAmministratore.amministratoreFrame.setVisible(false);
		else
			this.controllerGestore.getGestoreFrame().setVisible(false);
		visualizzaOrdiniFrame = new VisualizzaOrdiniFrame(this);
	}

	public void ChiudiVisualizzaOrdiniFrame() {
		this.visualizzaOrdiniFrame.dispose();
		if(this.controllerAmministratore!= null)	{
			this.controllerAmministratore.amministratoreFrame.setVisible(true);
			this.controllerAmministratore.amministratoreFrame.setEnabled(true);
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
	
	
	public void ApriVisualizzaProdottiFrame() {
		visualizzaProdottiFrame = new VisualizzaProdottiFrame(this);
	}
	
	public void ChiudiVisualizzaProdottiFrame() {
		visualizzaProdottiFrame.setVisible(false);
	}
	
	public void ChiudiCreaOrdineFrame() {
		creaOrdineFrame.setVisible(false);
	}
	
	
	
	public Object[][] getProdottiCarrello(int idOrdine){
		Object[][] risultato = null;
		if(controllerAmministratore != null) {
			if(controllerAmministratore.getImp() == controllerAmministratore.getPostgresImp()) {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				risultato = prodottoDAO.getProdottiPerId_Ordine(idOrdine).toArray(new Object[][] {});
			}
			else if(controllerAmministratore.getImp() == controllerAmministratore.getAltraImp()){
			
			}
		}
		else {
			if(controllerGestore.getImp() == controllerGestore.getPostgresImp()) {
				ProdottoDAO prodottoDAO = new ProdottoDAOPostgresImp();
				risultato = prodottoDAO.getProdottiPerId_Ordine(idOrdine).toArray(new Object[][] {});
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
					risultato = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max).toArray(new Object[][] {});
				}
				else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {//Altra Impl
					
				}
			}
			else {
					if(controllerGestore.getImp() == controllerGestore.getPostgresImp()) {
						OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
						risultato = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max).toArray(new Object[][] {});
					}
					else if(controllerGestore.getImp() == controllerGestore.getAltraImp()) {
						
					}
			}
		
		return risultato;
	}


	public String[] getCategorie() {
		
		String[] result = null;
		if(controllerGestore != null) {
			if(controllerGestore.getImp()==controllerGestore.getPostgresImp())
			{
				
				ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
				try {
					result = prodottoDao.getCategorieProdotto().toArray(new String[] {});
				} catch (SQLException e) {
					//Errore
				}
				
				
			}else if(controllerGestore.getImp()==controllerGestore.getAltraImp())
			{
				//altre implementazioni
			}
		}
		else {//controllerAmministratore
			
			
		}
		
		
		return result;
	}
	
	





}