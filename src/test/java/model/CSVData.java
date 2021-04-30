package model;

public class CSVData {
	
		private String tipologia = null;
		private String collegamento = null;
		private String trattaAndata = null;
		private String meseAndata = null;
		private String giornoAndata = null;
		private String passeggeriAdulti = null;
		private String passeggeriBambini = null;
		private String passeggeriAnimali = null;
		private String veicolo = null;
		private String sistemazione = null;
		private String anno = null;

		public CSVData(String[] data) {
			for (int i = 0; i < data.length; i ++)
				if (data[i].isEmpty())
					data[i] = null;

			this.tipologia = data[0];
			this.collegamento = data[1];
			this.trattaAndata = data[2];
			this.meseAndata = data[3];
			this.giornoAndata = data[4];
			this.passeggeriAdulti = data[5];
			this.passeggeriBambini = data[6];
			this.passeggeriAnimali = data[7];
			this.veicolo = data[8];
			this.sistemazione = data[9];
		}

		public CSVData() {

		}

		public CSVData(String tipologia, String collegamento, String trattaAndata, String meseAndata, String giornoAndata, String passeggeriAdulti, String passeggeriBambini, String passeggeriAnimali,
				String veicolo, String sistemazione) {
			this.tipologia = tipologia;
			this.collegamento = collegamento;
			this.trattaAndata = trattaAndata;
			this.meseAndata = meseAndata;
			this.giornoAndata = giornoAndata;
			this.passeggeriAdulti = passeggeriAdulti;
			this.passeggeriBambini = passeggeriBambini;
			this.passeggeriAnimali = passeggeriAnimali;
			this.veicolo = veicolo;	
			this.sistemazione = sistemazione;
		}

		public String getTipologia() {
			return tipologia;
		}
		
		public void setTipologia(String tipologia) {
			this.tipologia=tipologia;
		}
		
		public String getCollegamento() {
			return collegamento;
		}

		public void setCollegamento(String collegamento) {
			this.collegamento = collegamento;
		}

		public String getTrattaAndata() {
			return trattaAndata;
		}

		public void setTrattaAndata(String trattaAndata) {
			this.trattaAndata = trattaAndata;
		}

		public String getMeseAndata() {
			return meseAndata;
		}

		public void setMeseAndata(String meseAndata) {
			this.meseAndata = meseAndata;
		}

		public String getGiornoAndata() {
			return giornoAndata;
		}

		public void setGiornoAndata(String giornoAndata) {
			this.giornoAndata = giornoAndata;
		}

		public String getPasseggeriAdulti() {
			return passeggeriAdulti;
		}

		public void setPasseggeriAdulti(String passeggeriAdulti) {
			this.passeggeriAdulti = passeggeriAdulti;
		}

		public String getPasseggeriBambini() {
			return passeggeriBambini;
		}

		public void setPasseggeriBambini(String passeggeriBambini) {
			this.passeggeriBambini = passeggeriBambini;
		}

		public String getPasseggeriAnimali() {
			return passeggeriAnimali;
		}

		public void setPasseggeriAnimali(String passeggeriAnimali) {
			this.passeggeriAnimali = passeggeriAnimali;
		}

		public String getVeicolo() {
			return veicolo;
		}

		public void setVeicolo(String veicolo) {
			this.veicolo = veicolo;
		}
		
		public String getSistemazione() {
			return sistemazione;
		}

		public void setSistemazione(String sistemazione) {
			this.sistemazione = sistemazione;
		}

		@Override
		public String toString() {
			return "CSVData [tipologia=" + tipologia + ", collegamento=" + collegamento + ", trattaAndata="
					+ trattaAndata + ", meseAndata=" + meseAndata + ", giornoAndata=" + giornoAndata
					+ ", passeggeriAdulti=" + passeggeriAdulti + ", passeggeriBambini=" + passeggeriBambini
					+ ", passeggeriAnimali=" + passeggeriAnimali + ", veicolo=" + veicolo + ", sistemazione=" + sistemazione +"]";
		}

		public String getAnno() {
			return anno;
		}

		public void setAnno(String anno) {
			this.anno = anno;
		}

}
