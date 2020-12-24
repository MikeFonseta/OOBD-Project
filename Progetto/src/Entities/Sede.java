package Entities;

public class Sede {

	private String idSede;
	private String nomeSede;
	private String telefonoSede;
	private String provincia;
	private String citt�;
	private String via;
	private String numCivico;
	
	public Sede(String idSede, String nomeSede,String telefonoSede, String provincia,
			String citt�, String via, String numCivico) {
		this.idSede = idSede;
		this.nomeSede = nomeSede;
		this.telefonoSede = telefonoSede;
		this.provincia = provincia;
		this.citt� = citt�;
		this.via = via;
		this.numCivico = numCivico;
	}
	
	public Sede() {
		
	}

	
	public String getIdSede() {
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

	public String getCitt�() {
		return citt�;
	}

	public String getVia() {
		return via;
	}

	public String getNumCivico() {
		return numCivico;
	}

	
}
