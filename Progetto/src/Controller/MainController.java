package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import DAO.AccountDAOPostgresImp;
import DAO.OrdineDAOPostgresImp;
import DAO.ProdottoDAO;
import DAO.ProdottoDAOPostgresImp;
import DAO.SedeDAOPostgresImp;
import Entities.*;
import GUI.LoginFrame;
import GUI.VisualizzaOrdiniFrame;
import GUI.VisualizzaProdottiFrame;
import GUI.CreaOrdineFrame;

public class MainController {
	
	public LoginFrame loginFrame = new LoginFrame(this);
	private ControllerAmministratore controllerAmministratore = null;
	//private AdminController AdminController = null;
	private ControllerGestore controllerGestore = null;
	private VisualizzaOrdiniFrame visualizzaOrdiniFrame = null;
	private VisualizzaProdottiFrame visualizzaProdottiFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;

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
	
	
	
	public void ApriVisualizzaOrdiniFrame() {
		visualizzaOrdiniFrame = new VisualizzaOrdiniFrame(this);
	}

	public void ChiudiVisualizzaOrdiniFrame() {
		visualizzaOrdiniFrame.setVisible(false);
	}
	
	public void ApriVisualizzaProdottiFrame() {
		visualizzaProdottiFrame = new VisualizzaProdottiFrame(this);
	}
	
	public void ChiudiVisualizzaProdottiFrame() {
		visualizzaProdottiFrame.setVisible(false);
	}
	
	public void ApriCreaOrdineFrame() {
		creaOrdineFrame = new CreaOrdineFrame(this);
	}
	
	public void ChiudiCreaOrdineFrame() {
		creaOrdineFrame.setVisible(false);
	}
	
	
	String[] convertiInArrayStringhe(List<String> list) {
	String ris[] = new String[list.size()];
	
		for(int i=0;i<list.size();i++)	
			ris[i] = list.get(i);	
	return ris;
	}
	
	
	
	
	
	
	public String[] getIDSedi() {
		List<String> IDsedi = new ArrayList<String>();
		String[] result = null;
		
		if(controllerAmministratore != null) {

			if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
				SedeDAOPostgresImp SedeDAOPostgres = new SedeDAOPostgresImp();
				try {
					IDsedi = SedeDAOPostgres.CercaTutteLeSedi();
					IDsedi.add(0, "Tutte Le Sedi");
					result = convertiInArrayStringhe(IDsedi);
				} catch (SQLException e) {
					
					//Inserito cosi come ho fatto in controllerAmministratore per non farti avere l'errore nella classe
					JOptionPane.showMessageDialog(this.loginFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); 
				}
			}		
			else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {
			//Altra implementazione
			}
	    }
		else {
					result = new String[] { String.valueOf(controllerGestore.getAccount().getSede().getIdSede()) };			
		}
		return result;
	}


	public List<Integer> getID_ProdottiPerNomeP(String NomiP) {
		List<Integer> result = new ArrayList<>();
		if(NomiP.isBlank()== false) {
			String Nprodotti[] = NomiP.split(","); 		
			if(controllerAmministratore != null) {
				if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
					ProdottoDAOPostgresImp prodottoDAO = new ProdottoDAOPostgresImp();
						try {
							result = prodottoDAO.getTuttiProdottiPerNome(Nprodotti);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()){ //AltraImpl
					
				}				

			}
			else {//codice gestore	
				
				
				
			}
		}
			else result = null;
		
		return result;
	}
	
	
	public Object[][] getOrdini(Integer idSede, List<Integer> idProdotti, String Veicolo, Integer Min, Integer Max) {
		Object [][]object = null;
		List<Object[]> result = new ArrayList<Object[]>();			
			if(controllerAmministratore != null) {
				if(controllerAmministratore.getImp()==controllerAmministratore.getPostgresImp()) {
					OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
					result = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max);
					if(result!=null)
					object = result.toArray(new Object[][]{});
					
				}
				else if(controllerAmministratore.getImp()==controllerAmministratore.getAltraImp()) {//Altra Impl
					
				}
			}
			else {
					if(controllerGestore.getImp() == controllerGestore.getPostgresImp()) {
						OrdineDAOPostgresImp OrdineDAO = new OrdineDAOPostgresImp();
						result = OrdineDAO.ricercaComplessaOrdini(idSede,idProdotti,Veicolo,Min,Max);
					}
					else if(controllerGestore.getImp() == controllerGestore.getAltraImp()) {
						
					}
			}
			
			
			
		
		return object;
	}


	
	





}