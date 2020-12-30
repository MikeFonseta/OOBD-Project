package Entities;

public class Account {
	
	private String NomeUtente;
	private String Password;
	private Boolean amministratore;
	private Sede Sede;
	
	
	public Account(String nomeUtente, String password, Boolean amministratore, Sede Sede) {
		super();
		NomeUtente = nomeUtente;
		Password = password;
		this.amministratore = amministratore;
		this.Sede = Sede;
	}
	
	
	public Account() {
	}

	
	public String getNomeUtente() {
		return this.NomeUtente;
	}
	public String getPassword() {
		return this.Password;
	}
	public Boolean getAmministratore() {
		return this.amministratore;
	}
	
	public Sede getSede() {
		return this.Sede;
	}
	
	public void setSede(Sede sede) {
		this.Sede=sede;
	}
	
	
	public void setPassword(String password) {
		this.Password = password;
	}
	
}