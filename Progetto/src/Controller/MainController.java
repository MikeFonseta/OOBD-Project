package Controller;

import java.awt.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import DAO.AccountDAOPostgresImp;
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
		this.loginFrame.getNomeUtente_tf().setText("");
		this.loginFrame.getPassword_pf().setText("");
	}
	
	public void ChiudiLogin() {
		this.loginFrame.dispose();
	}
}