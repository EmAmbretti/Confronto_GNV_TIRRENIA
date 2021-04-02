package model;

import utils.CSVExtractor;
import utils.Path;

public class WebData {
	
	CSVData csvData=CSVExtractor.getTestDataByOffer("TestCase7", Path.PATH);

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
	
	public WebData(String sito) {
		this.sito = sito;
		collegamento = csvData.getCollegamento();
		tratta = csvData.getTrattaAndata();
		mese = csvData.getMeseAndata();
		giorno = csvData.getGiornoAndata();
		adulti = csvData.getPasseggeriAdulti();
		bambini = csvData.getPasseggeriBambini();
		animali = csvData.getPasseggeriAnimali();
		veicolo = csvData.getVeicolo();
		sistemazione = csvData.getSistemazione();
		
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
