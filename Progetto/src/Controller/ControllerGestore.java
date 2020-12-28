package Controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.OrdineDAOPostgresImp;
import Entities.Account;
import GUI.GestoreFrame;


public class ControllerGestore {
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	private MainController mainController = null;
	private Account account;
	private GestoreFrame gestoreFrame = null;
	
	
	
	public ControllerGestore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
	    gestoreFrame = new GestoreFrame(this); 
	}
	
	public void chiudiGestoreFrame(boolean logout) {
		if(logout==true) {
			this.mainController.ApriLogin();
		}
		gestoreFrame.dispose();
		this.mainController.loginFrame.dispose();
	}

	
	public Account getAccount() {
		return this.account;
	}
	
	
	
public Object[][] getDatiOrdini() {
		
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			OrdineDAOPostgresImp ordineDao = new OrdineDAOPostgresImp();
			try {
				result = ordineDao.getOrdiniTabella().toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		return result;
	}
	
	

	public void AggiornaTabella() {
		//aggiorna le tabelle
	}

    
	public void ChiudiVisualizzaOrdini() {
		
	}
	
	public void ApriCreaOrdineFrame() {
		this.gestoreFrame.setVisible(false);
		this.mainController.ApriCreaOrdineFrame();  
	}
	
	public void ApriVisualizzaOrdini() {
		this.gestoreFrame.setVisible(false);
		mainController.ApriVisualizzaOrdiniFrame();
	}
	
	public void ApriVisualizzaProdottiFrame(){
		this.gestoreFrame.setVisible(false);
		this.mainController.ApriVisualizzaProdottiFrame(); 
	}
	
	public void ImpostaFineConsegna() {
		//fa partire una query che imposta la data e l'ora attuale come fine consegna
		
	}
	
	public void ImpostaInizioConsegna() {
		//fa partire una query che imposta la data e l'ora attuale come inizio consegna
	}
	
	public void ModificaCreaOrdineFrame() {
		//apre la schermata Crea ordine ma con i campi caricati dall ordine selezionato
	}
	
	public void ApriCarrello() {
		//apre il carrello di un ordine
	}
	
	public void EliminaOrdine() {
		//cancella un ordine 
	}

	
	
	//Getter e Setter
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

	public void setAccount(Account account) {
		this.account = account;
	}

}
