package Entities;

public class Prodotto {

	private int idProdotto;
	private String nomeProdotto;
	private String descrizione;
	private float prezzo;
	private String categoria;
	
	
	public Prodotto(int idProdotto, String nomeProdotto, String descrizione, float prezzo, String categoria) {
		this.idProdotto = idProdotto;
		this.nomeProdotto = nomeProdotto;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.categoria = categoria;
	}
	
	public Prodotto() {}
}
