package Controller;

import java.awt.List;

import javax.swing.JOptionPane;

import DAO.AccountDAOPostgresImpl;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;

public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	//private AdminController AdminController = null;
	private ControllerGestore controllerGestore = null;

	public MainController() {
		ApriLogin();
	}
	
	public static void main(String[] args) {
		MainController mainController = new MainController();
	}
	

	public void LoginTry(String NomeUtente, String password){
		
		Account account = new Account();
		
		AccountDAOPostgresImpl accountDao = new AccountDAOPostgresImpl();
			
		account = accountDao.ControlloCredenziali(NomeUtente, password);
			if(account != null){
				
				if(account.getAmministratore() == false) {
					//sbaglio o qui va il gestore?
				}else {
					this.controllerAmministratore = new ControllerAmministratore(this);
					this.controllerAmministratore.SetAccount(account);
					this.controllerAmministratore.ApriClient();
					//this.controllerAmministratore = new ControllerAmministratore(this);
					//Qualche funzione per passsare dati (?)
					//this.controllerAmministratore.ApriClient();
				}
				this.loginFrame.setVisible(false);
				
			}  
			else 
			{
				JOptionPane.showMessageDialog(this.loginFrame,"Credenziali invalide!","Errore",JOptionPane.ERROR_MESSAGE);
			}
			
			System.out.println("Sono uscito");
			System.out.println("Ciao");
			System.out.println("1");
	}

	public void ApriLogin() {
		this.controllerGestore = null;
		//this.ControllerAdmin = null;
		this.loginFrame.setVisible(true);
		this.loginFrame.getNomeUtente_tf().setText("");
		this.loginFrame.getPassword_pf().setText("");
	}
	
}