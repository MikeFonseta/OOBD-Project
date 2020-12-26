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
		this.gestoreFrame.dispose();
		if(logout==true) {
			this.mainController.ApriLogin();
		}
	}

	
	public Object[][] getDatiSedi() //funzione provvisoria
	{
		return null;
	}



	public void ApriVisualizzaOrdini() {
		mainController.ApriVisualizzaOrdiniFrame();
	}
    
	public void ChiudiVisualizzaOrdini() {
		
	}
	
	static public void TornaAlLogin() {
		
	}
	
	static public void ApriCreaOrdineFrame() {
		//creaOrdineGUI
	}
	
	static public void ApriVisualizzaOrdineFrame(){
	
	}
	
	static public void ApriVisualizzaProdottiFrame(){
		
	}
	
	static public void ImpostaFineConsegna() {
		
	}
	
	static public void ImpostaInizioConsegna() {
		
	}
	
	static public void ModificaCreaOrdineFrame() {
		
	}
	
	static public void AggiornaTabella() {
		
	}
	
	static public void ApriCarrello() {
		
	}
	
	static public void EliminaOrdine() {
		
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}




}
