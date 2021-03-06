package model;

import java.time.LocalDate;

public class EsitoSito {

	private String sito;
	private String errori;
	private CSVData datiCsv;
	private String prezzo;
	private String note;
	private LocalDate data;
	
	public EsitoSito(String nomeSito, CSVData datiCSV) {
		this.sito = nomeSito;
		this.datiCsv = datiCSV;
	}
	
	public String getSito() {
		return sito;
	}
	public void setSito(String sito) {
		this.sito = sito;
	}
	public String getErrori() {
		return errori;
	}
	public void setErrori(String errori) {
		this.errori = errori;
	}
	public CSVData getDatiCsv() {
		return datiCsv;
	}
	public void setDatiCsv(CSVData datiCsv) {
		this.datiCsv = datiCsv;
	}
	public String getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}
	public String getNote() {
		return note;
	}
	public void appendNote(String note) {
		if(this.note==null || this.note.equals("")) {
			this.note = note;
		} else if(this.note!=null && this.note.length()>1) {
			this.note += "\n" + note;
		}
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}
