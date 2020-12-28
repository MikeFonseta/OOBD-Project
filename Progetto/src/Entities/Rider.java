package Entities;

public class Rider {
	
	private int idRider;
	private String nomeRider;
	private String cognomeRider;
	private String telefonoRider;
	private String veicoloRider;
	private boolean disponibilita;
	private Sede sede;
	
	
	public Rider() {
		
	}
	
	public Rider(int idRider, String nomeRider, String cognomeRider, String telefonoRider, String veicoloRider,
			boolean disponibilita, Sede sede) {
		this.idRider = idRider;
		this.nomeRider = nomeRider;
		this.cognomeRider = cognomeRider;
		this.telefonoRider = telefonoRider;
		this.veicoloRider = veicoloRider;
		this.disponibilita = disponibilita;
		this.sede = sede;
	}
	
	public Rider(int idRider, String nomeRider, String cognomeRider, String telefonoRider, String veicoloRider) {
		this.idRider = idRider;
		this.nomeRider = nomeRider;
		this.cognomeRider = cognomeRider;
		this.telefonoRider = telefonoRider;
		this.veicoloRider = veicoloRider;
	}
	
	
	public Rider(int idRider, String nomeRider, String cognomeRider, String telefonoRider, String veicoloRider,
			Boolean disponibilita) {
		this.idRider = idRider;
		this.nomeRider = nomeRider;
		this.cognomeRider = cognomeRider;
		this.telefonoRider = telefonoRider;
		this.veicoloRider = veicoloRider;
		this.disponibilita = disponibilita;
	}

	public int getIdRider() {
		return idRider;
	}
	public String getNomeRider() {
		return nomeRider;
	}
	public String getCognomeRider() {
		return cognomeRider;
	}
	public String getTelefonoRider() {
		return telefonoRider;
	}
	public String getVeicoloRider() {
		return veicoloRider;
	}
	public boolean isDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	public Sede getSede() {
		return this.sede;
	}
	
}
