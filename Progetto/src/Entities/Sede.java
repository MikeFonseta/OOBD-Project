package Entities;

public class Sede {

	private String idSede;
	private String nomeSede;
	private String telefonoSede;
	private String indirizzoSede;
	private String provincia;
	private String citt�;
	private String via;
	private String numCivico;
	
	public Sede(String idSede, String nomeSede, String indirizzoSede, String telefonoSede, String provincia,
			String citt�, String via, String numCivico) {
		this.idSede = idSede;
		this.nomeSede = nomeSede;
		this.telefonoSede = telefonoSede;
		this.indirizzoSede = indirizzoSede;
		this.provincia = provincia;
		this.citt�  = citt�;
		this.via = via;
		this.numCivico = numCivico;
	}
	
	public Sede() {
		
	}

}
