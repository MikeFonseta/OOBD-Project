package Controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.OrdineDAOPostgresImp;
import DAO.ProdottoDAOPostgresImp;
import Entities.Account;
import GUI.CreaOrdineFrame;
import GUI.GestoreFrame;


public class ControllerGestore {
	//da caricare: CreaOrdineFrame, VisualizzaProdottiFrame, InfoProdottoFrame
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	private MainController mainController = null;
	private Account account;
	private GestoreFrame gestoreFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;
	

	public ControllerGestore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
	    gestoreFrame = new GestoreFrame(this); 
	    
	    
	}
	
	public void ApriVisualizzaCarrello(int indice) {
		this.gestoreFrame.setEnabled(false);
		this.mainController.ApriVisualizzaCarrelloFrame(indice); 
    }
	
	public void chiudiGestoreFrame(boolean logout) {
		if(logout==true) {
			this.mainController.ApriLogin();
		}
		gestoreFrame.dispose();
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
	
	
public Object[][] getDatiProdotti(String categoria) { //prodotti
		
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				result = prodottoDao.getProdottiTabella(categoria).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		return result;
	}
	
	
	public String[] getCategorieBox() {
		
		String[] result = this.mainController.getCategorie();
		
		return result;
	}


	public void ApriCreaOrdineFrame() {
		//this.gestoreFrame.setEnabled(false);  //da reimpostare dopo aver finito i test
		creaOrdineFrame = new CreaOrdineFrame(this);
	}
	
	public void ApriVisualizzaOrdini() {
		//this.gestoreFrame.setEnabled(false);	//da reimpostare dopo aver finito i test
		mainController.ApriVisualizzaOrdiniFrame();
	}
	
	public void ApriVisualizzaProdottiFrame(){
		//this.gestoreFrame.setEnabled(false);	//da reimpostare dopo aver finito i test
		this.mainController.ApriVisualizzaProdottiFrame(); 
	}
	
	public void ImpostaFineConsegna(int idOrdine) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.TerminaConsegna(idOrdine);
			this.gestoreFrame.AggiornaOrdini();
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	public void ImpostaInizioConsegna(int idOrdine) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.IniziaConsegna(idOrdine);
			this.gestoreFrame.AggiornaOrdini();
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	public void ModificaCreaOrdineFrame() {//scrivere dopo aver caricato CreaOrdineFrame
		
		//apre la schermata Crea ordine ma con i campi caricati dall ordine selezionato
	}
	
	public void EliminaOrdine(int idOrdine) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.CancellaOrdine(idOrdine);
			this.gestoreFrame.AggiornaOrdini();
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		 
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

	public GestoreFrame getGestoreFrame() {
		return gestoreFrame;
	}

	public void setGestoreFrame(GestoreFrame gestoreFrame) {
		this.gestoreFrame = gestoreFrame;
	}

}
