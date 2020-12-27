package Controller;

import Entities.Account;
import Entities.Sede;
import GUI.GestoreFrame;


public class ControllerGestore {
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	private MainController mainController = null;
	private Account account;
	public GestoreFrame gestoreFrame = new GestoreFrame(this);
	
	
	
	public ControllerGestore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
		
	}
	
	public void chiudiGestoreFrame(boolean logout) {
		if(logout==true) {
			this.mainController.ApriLogin();
		}
		this.gestoreFrame.dispose();
		this.mainController.loginFrame.dispose();
	}

	
	public Account getAccount() {
		return this.account;
	}
	
	public Object[][] getDatiSedi() //funzione provvisoria
	{
		return null;
	}

	public void AggiornaTabella() {
		//aggiorna le tabelle
	}

	public void ApriVisualizzaOrdini() {
		mainController.ApriVisualizzaOrdiniFrame();
	}
    
	public void ChiudiVisualizzaOrdini() {
		
	}
	
	public void ApriCreaOrdineFrame() {
		//NB:cambiare il nome del file creaOrdineGUI con CreaOrdineFrame
		this.gestoreFrame.setVisible(false);
		//this.mainController.ApriCreaOrdineFrame();  funzione ancora non creata
	}
	
	public void ApriVisualizzaOrdiniFrame(){
		this.gestoreFrame.setVisible(false);
		//this.mainController.ApriVisualizzaOrdiniFrame();  funzione ancora non creata
	}
	
	public void ApriVisualizzaProdottiFrame(){
		this.gestoreFrame.setVisible(false);
		//this.mainController.ApriVisualizzaProdottiFrame();  funzione ancora non creata
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
