package Controller;

import Entities.Account;
<<<<<<< .mine
import Entities.Sede;
||||||| .r21
=======
import GUI.GestoreFrame;
>>>>>>> .r23

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
<<<<<<< .mine


	public void ApriVisualizzaOrdini() {

		
	}
||||||| .r21
=======
	
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

>>>>>>> .r23
}
