package Controller;

import Entities.Account;
import Entities.Sede;
import GUI.GestoreFrame;


public class ControllerGestore {
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
		return getDatiSedi();
	}



	public void ApriVisualizzaOrdini() {

		
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


}
