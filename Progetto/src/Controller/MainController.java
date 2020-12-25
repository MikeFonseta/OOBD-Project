package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.postgresql.jdbc2.ArrayAssistantRegistry;

import DAO.AccountDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;
import GUI.VisualizzaOrdiniFrame;

public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	//private AdminController AdminController = null;
	private ControllerGestore controllerGestore = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;

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
	
	
	public void ApriVisualizzaOrdini() {
		List<String> sedi = new ArrayList<String>(); 
		if(controllerAmministratore != null) {
			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
				SedeDAOPostgresImp SedeDAO = new SedeDAOPostgresImp();
				sedi = SedeDAO.CercaTutteLeSedi();
				String[] ris = new String[sedi.size()+1];
				ris[0] = "Tutte Le Sedi";
				for(int i=1;i<sedi.size()+1;i++)
					ris[i] = sedi.get(i-1);
					visualizzaOrdiniFrame = new VisualizzaOrdiniFrame(this,ris);
			
			}
			else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {//Altra impl
			}
	    }
		else {
			//codice per il gestore
		}
		visualizzaOrdiniFrame.setVisible(true);
	}
	

	public void ChiudiVisualizzaOrdiniFrame() {
		visualizzaOrdiniFrame.setVisible(false);
		
	}
}