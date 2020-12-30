package Entities;

public class Ordine {
	private Integer idOrdine;
	private java.sql.Timestamp creazioneOrdine;
	private java.sql.Timestamp inizioConsegna;
	private java.sql.Timestamp fineConsegna;
	private float totale;
	private Rider rider;
	

	public Ordine(Integer idOrdine, java.sql.Timestamp creazioneOrdine, float totale, Rider rider) {
		super();
		this.idOrdine = idOrdine;
		this.creazioneOrdine = creazioneOrdine;
		this.totale = totale;
		this.rider = rider;
	}


	public Ordine() {
		
	}
	
	
	
	}

