package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.AccountDAOPostgresImp;
import DAO.OrdineDAOPostgresImp;
import DAO.ProdottoDAOPostgresImp;
import DAO.RiderDAOPostgresImp;
import Entities.Account;
import GUI.CreaOrdineFrame;
import GUI.GestoreFrame;
import GUI.InfoProdottoFrame;


public class ControllerGestore {
	//da caricare: CreaOrdineFrame, VisualizzaProdottiFrame, InfoProdottoFrame
	private String imp = "postgres";
	private String postgresImp = "postgres";
	private String altraImp = "altraImp";
	private MainController mainController = null;
	private Account account;
	private GestoreFrame gestoreFrame = null;
	private CreaOrdineFrame creaOrdineFrame = null;
	private InfoProdottoFrame infoProdottoFrame = null;

	public ControllerGestore(MainController mainController, Account account) {
		
		this.mainController = mainController;
		this.account = account;
	    gestoreFrame = new GestoreFrame(this); 
	    
	    
	}
	
	public void ApriVisualizzaCarrello(int indice) {
		this.gestoreFrame.setEnabled(false);
		this.mainController.ApriVisualizzaCarrelloFrame(indice); 
    }
	
	public void ChiudiGestoreFrame(boolean logout) {
		if(logout==true) {
			this.mainController.ApriLogin();
		}
		gestoreFrame.dispose();
	}

	public Account getAccount() {
		return this.account;
	}
	
	public List<String> getComuniProvincia(String Provincia) {
		return this.mainController.getComuniProvincia(Provincia);
	}
	
	public Object[] getProvince(){
		return this.mainController.getProvince();
	}
	
	public void CancellaCodiciRider(int idRider) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.CancellaCodiceRider(idRider);
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
	}
	
	public int AssegnaOrdineAlRider(int idOrdine,int idRider,boolean associa) {
		int numeroordini=0;
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao=new RiderDAOPostgresImp();
			try {
				if(associa) {
					riderDao.Assegnazione(idOrdine,idRider);
					numeroordini=riderDao.AggiornaNumeroOrdini(idRider,true);
				}
				else {
					riderDao.Dissociazione(idOrdine,idRider);
				}
					
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		return numeroordini;
	}
	
	public void AggiornaDisposizioneRider(int idRider,boolean input) {
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao=new RiderDAOPostgresImp();
			try {
				riderDao.AggiornaDisposizione(idRider,input);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
	}
	
	public Object[][] getDatiRider(){
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			RiderDAOPostgresImp riderDao = new RiderDAOPostgresImp();
			try {
				result = riderDao.getRiderPerSede(this.account.getSede().getIdSede()).toArray(new Object[][] {});
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		return result;
	}
	
	public Object[][] getDatiOrdini(int idRider) {
		
		Object[][] result = null;
		if(this.imp.equals(this.postgresImp))
		{
			OrdineDAOPostgresImp ordineDao = new OrdineDAOPostgresImp();
			try {
				if(idRider==0)
					result = ordineDao.getOrdiniTabella(this.account.getSede().getIdSede()).toArray(new Object[][] {});
				else 
					result = ordineDao.getOrdiniFiltroRider(idRider).toArray(new Object[][] {});
				
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
	
	public String[] getSingoloProdotto(int idProdotto) {
		
		String[] result = null;
		
		if(this.imp.equals(this.postgresImp))
		{
			ProdottoDAOPostgresImp prodottoDao = new ProdottoDAOPostgresImp();
			try {
				result = prodottoDao.getDatiSingoloProdotto(idProdotto);
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

	public String[] getDati(int id) {
		String [] dati =null;
		
		if(this.imp.equals(this.postgresImp)) {
			AccountDAOPostgresImp accountDAO = new AccountDAOPostgresImp();
			try {
				dati=accountDAO.getDatiCliente(id);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		return dati ;
	}
	
	public String getProssimoID() {
		String ID=null;
		
		if(this.imp.equals(this.postgresImp)) {
			AccountDAOPostgresImp accountDAO = new AccountDAOPostgresImp();
			try {
				int id = accountDAO.getClienteID();
				ID=String.valueOf(++id);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		return ID;
	}

	public void ApriCreaOrdineFrame() {
		//this.gestoreFrame.setEnabled(false);  //da reimpostare dopo aver finito i test
		creaOrdineFrame = new CreaOrdineFrame(this,0);
	}
	
	public void ApriVisualizzaOrdini() {
		//this.gestoreFrame.setEnabled(false);	//da reimpostare dopo aver finito i test
		mainController.ApriVisualizzaOrdiniFrame();
	}
	
	public void ApriVisualizzaProdotti(){
		//this.gestoreFrame.setEnabled(false);	//da reimpostare dopo aver finito i test
		this.mainController.ApriVisualizzaProdottiFrame(); 
	}
	
	public void ApriInfoProdottoFrame(int idProdotto){
		if(this.infoProdottoFrame!=null) {
			if(this.infoProdottoFrame.getID()==idProdotto) {
				JOptionPane.showMessageDialog(this.creaOrdineFrame,"Queste info sono gi\u00E0 aperte");
				this.infoProdottoFrame.setVisible(true);
				return;
			}
			else {
				infoProdottoFrame.dispose();
				JOptionPane.showMessageDialog(this.creaOrdineFrame,"Le info sul prodotto aperte verranno chiuse");
			}
		}
		
		infoProdottoFrame = new InfoProdottoFrame(this, idProdotto);
		
	}
	
	public void ChiudiInfoProdottoFrame() {
		infoProdottoFrame.dispose();
		infoProdottoFrame=null;
	}
	
	public void ChiudiCreaOrdineFrame() {
		gestoreFrame.AggiornaOrdini(gestoreFrame.getFiltroRider());
		gestoreFrame.AggiornaRider();
		creaOrdineFrame.dispose();
		creaOrdineFrame=null;
	}
	
	
	public void ImpostaInizioConsegna(int idRider, boolean annulla) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.IniziaConsegna(idRider,annulla);
			
			try {
				RiderDAOPostgresImp riderDAO =new RiderDAOPostgresImp();
				riderDAO.AggiornaDisposizione(idRider,annulla);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	public void AnnullaConsegna(int idRider) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.IniziaConsegna(idRider,true);
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	
	
	public void ImpostaFineConsegna(int idRider) {
		
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.TerminaConsegna(idRider);
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	
	
	public void ModificaCreaOrdineFrame(int idOrdine) {//scrivere dopo aver caricato CreaOrdineFrame
		
		//this.gestoreFrame.setEnabled(false);  //da reimpostare dopo aver finito i test
		creaOrdineFrame = new CreaOrdineFrame(this,idOrdine);
	}
	
	
	public int EliminaOrdine(int idOrdine,int idRider) {
		int numeroordini=0;
		if(this.imp.equals(this.postgresImp)) {
			OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
			ordineDAO.CancellaOrdine(idOrdine);
			try {
				RiderDAOPostgresImp riderDAO =new RiderDAOPostgresImp();
				numeroordini=riderDAO.AggiornaNumeroOrdini(idRider,false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		 return numeroordini;
	}

	public void CreaNuovoCliente(String id,String nome,String cognome) {
		
		if(this.imp.equals(this.postgresImp)) {
			
			try {
				AccountDAOPostgresImp accountDAO = new AccountDAOPostgresImp();
				accountDAO.CreaCliente(Integer.parseInt(id),nome,cognome);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	public int CreazioneOrdine(float totale) {
		int idOrdine=0;
		int idSede=this.account.getSede().getIdSede();
		int idRider=0;
		
		if(this.imp.equals(this.postgresImp)) {
			RiderDAOPostgresImp riderDAO = new RiderDAOPostgresImp();
			try {
				idRider=riderDAO.TrovaRiderDisponibile(idSede);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
				idOrdine=ordineDAO.CreaOrdine(totale,idRider,idSede);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				int numeroordini=0;
				numeroordini=riderDAO.AggiornaNumeroOrdini(idRider,true);
				if(numeroordini==3)
				{
					this.ImpostaInizioConsegna(idRider,false);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
				
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		
		return idOrdine;
	}
	
	
	public void CreazioneCompOrdine(List<int[]> prodotti,int idNuovoOrdine)
	{
		if(this.imp.equals(this.postgresImp)) {
			try {
				OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
				ordineDAO.CreaCompOrdine(prodotti,idNuovoOrdine);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
	}
	
	
	public void CreazioneInfoOrdine(int idOrdine,int idCliente, String citta, String via, String civico, String telefono, String provincia){
		
		if(this.imp.equals(this.postgresImp)) {
			try {
				OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
				ordineDAO.CreaInfoOrdine(idOrdine,idCliente,citta,via,civico,telefono,provincia);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(this.imp.equals(this.altraImp))
		{
			//altre implementazioni
		}
		
		
		
	}
	
	public Object[][] PrelevaProdottiCarrello(int idOrdine){
		
		Object[][] prodotti = null;
		
		if(idOrdine!=0) {
			if(this.imp.equals(this.postgresImp)) {
				try {
					
					OrdineDAOPostgresImp ordineDAO = new OrdineDAOPostgresImp();
					prodotti=ordineDAO.getProdottiCarrello(idOrdine).toArray(new Object[][] {});
				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(this.gestoreFrame,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(this.imp.equals(this.altraImp))
			{
				//altre implementazioni
			}
		}else prodotti=new Object[][] {};
		
		return prodotti;
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
