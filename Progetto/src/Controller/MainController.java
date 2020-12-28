package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import DAO.AccountDAOPostgresImp;
import DAO.OrdineDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;
import GUI.VisualizzaOrdiniFrame;
import GUI.VisualizzaProdottiFrame;
import GUI.CreaOrdineFrame;

public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	//private AdminController AdminController = null;
	private ControllerGestore controllerGestore = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;
	private VisualizzaProdottiFrame visualizzaProdottiFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;

	public MainController() {
		ApriLogin();
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
		visualizzaOrdiniFrame = new VisualizzaOrdiniFrame(this);
	}

	public void ChiudiVisualizzaOrdiniFrame() {
		visualizzaOrdiniFrame.setVisible(false);
	}
	
	public void ApriVisualizzaProdottiFrame() {
		visualizzaProdottiFrame = new VisualizzaProdottiFrame(this);
	}
	
	public void ChiudiVisualizzaProdottiFrame() {
		visualizzaProdottiFrame.setVisible(false);
	}
	
	public void ApriCreaOrdineFrame() {
		creaOrdineFrame = new CreaOrdineFrame(this);
	}
	
	public void ChiudiCreaOrdineFrame() {
		creaOrdineFrame.setVisible(false);
	}
	
	
	String[] convertiInArrayStringhe(List<String> list) {
	String ris[] = new String[list.size()];
	
		for(int i=0;i<list.size();i++)	
			ris[i] = list.get(i);	
	return ris;
	}
	
	
	
	
	
	
	public String[] getIDSedi() {
		List<String> IDsedi = new ArrayList<String>();
		String[] ris = null;
		
		if(controllerAmministratore != null) {

			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
				SedeDAOPostgresImp SedeDAOPostgres = new SedeDAOPostgresImp();
				try {
					IDsedi = SedeDAOPostgres.CercaTutteLeSedi();
					IDsedi.add(0, "Tutte Le Sedi");
					ris = convertiInArrayStringhe(IDsedi);
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
					ris = new String[] { String.valueOf(controllerGestore.getAccount().getSede().getIdSede()) };			
		}
		return ris;
	}


	
	
	public Object[][] getOrdini(String IDSede, String Prodotti, String Veicolo, Integer Min, Integer Max) {
		List<Object[]> a = new ArrayList<Object[]>();
		if(controllerAmministratore != null) {
			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
				//da restituire: CodSede CodOrdine CodCliente NomeCliente Indirizzo CodiceRider NomeRider Totale
				OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
				System.out.print("cioa");
			    a = OrdineDAO.ricercaComplessaOrdini(IDSede,Prodotti,Veicolo,Min,Max);
				
			}else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {
			//Altra implementazione
			 }
		}
		else {	
		//codice gestore
			
			
			
		}
	
	return null;
	}




	
	





}