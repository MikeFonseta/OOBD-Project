package Entities;

public class Sede {

	private int idSede;
	private String nomeSede;
	private String telefonoSede;
	private String provincia;
	private String citta;
	private String via;
	private String numCivico;
	
	public Sede(int idSede, String nomeSede,String telefonoSede, String provincia,
			String citta, String via, String numCivico) {
		this.idSede = idSede;
		this.nomeSede = nomeSede;
		this.telefonoSede = telefonoSede;
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.numCivico = numCivico;
	}
	
	public Sede() {
		
	}

	
	public int getIdSede() {
		return idSede;
	}

	public String getNomeSede() {
		return nomeSede;
	}

	public String getTelefonoSede() {
		return telefonoSede;
	}


	public String getProvincia() {
		return provincia;
	}

	public String getCitta() {
		return citta;
	}

	public String getVia() {
		return via;
	}

	public String getNumCivico() {
		return numCivico;
	}

	
}
