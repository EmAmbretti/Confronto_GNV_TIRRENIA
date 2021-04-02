package model;

public class WebData {

	private String sito;
	private String collegamento;
	private String tratta;
	private String mese;
	private String giorno;
	private String adulti;
	private String bambini;
	private String animali;
	private String veicolo;
	private String prezzo;
	private String disponibilita;
	private String sistemazione;
	
	public WebData(String nomeSito, CSVData data) {
		sito=nomeSito;
		collegamento = data.getCollegamento();
		tratta = data.getTrattaAndata();
		mese = data.getMeseAndata();
		giorno = data.getGiornoAndata();
		adulti = data.getPasseggeriAdulti();
		bambini = data.getPasseggeriBambini();
		animali = data.getPasseggeriAnimali();
		veicolo = data.getVeicolo();
		sistemazione = data.getSistemazione();
	}

	public String getSito() {
		return sito;
	}
	public void setSito(String sito) {
		this.sito = sito;
	}
	public String getCollegamento() {
		return collegamento;
	}
	public void setCollegamento(String collegamento) {
		this.collegamento=collegamento;
	}
	public String getTratta() {
		return tratta;
	}
	public void setTratta(String tratta) {
		this.tratta = tratta;
	}
	public String getMese() {
		return mese;
	}
	public void setMese(String mese) {
		this.mese = mese;
	}
	public String getGiorno() {
		return giorno;
	}
	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}
	public String getAdulti() {
		return adulti;
	}
	public void setAdulti(String adulti) {
		this.adulti = adulti;
	}
	public String getBambini() {
		return bambini;
	}
	public void setBambini(String bambini) {
		this.bambini = bambini;
	}
	public String getAnimali() {
		return animali;
	}
	public void setAnimali(String animali) {
		this.animali = animali;
	}
	public String getVeicolo() {
		return veicolo;
	}
	public void setVeicolo(String veicolo) {
		this.veicolo = veicolo;
	}
	public String getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}
	public String getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}
	public String getSistemazione() {
		return sistemazione;
	}
	public void setSistemazione(String sistemazione) {
		this.sistemazione = sistemazione;
	}

}
