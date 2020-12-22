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

	public MainController() {
		
	}
	
	public static void main(String[] argc) {
		MainController mainController = new MainController();
	}
	

	public void LoginTry(String NomeUtente, String password){
		
		Account account = new Account();
		
		AccountDAOPostgresImpl accountDao = new AccountDAOPostgresImpl();
			
		account = accountDao.ControlloCredenziali(NomeUtente, password);
			if(account != null){
				
				if(account.getAmministratore() == false) {
					
					this.controllerAmministratore = new ControllerAmministratore(this);
					this.controllerAmministratore.SetAccount(account);
					this.controllerAmministratore.ApriClient();
				}else {
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
		this.loginFrame.NomeUtente_tf.setText("");
		this.loginFrame.Password_pf.setText("");
	}