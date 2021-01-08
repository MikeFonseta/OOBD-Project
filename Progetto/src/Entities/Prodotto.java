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


	
	
	public int getIdProdotto() {
		return idProdotto;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
		
}
