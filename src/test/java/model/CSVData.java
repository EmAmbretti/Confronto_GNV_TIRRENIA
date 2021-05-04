package model;

public class CSVData {
	
		private String id = null;
		private String collegamento = null;
		private String trattaAndata = null;
		private String anno = null;
		private String meseAndata = null;
		private String giornoAndata = null;
		private String passeggeriAdulti = null;
		private String passeggeriBambini = null;
		private String passeggeriAnimali = null;
		private String veicolo = null;
		private String sistemazione = null;
		private String fasciaOraria = null;
		private String comunePartenza = null;
		private String comuneArrivo = null;
		private String stagione = null;
		
		public String getComunePartenza() {
			return comunePartenza;
		}

		public void setComunePartenza(String comunePartenza) {
			this.comunePartenza = comunePartenza;
		}

		public String getComuneArrivo() {
			return comuneArrivo;
		}

		public void setComuneArrivo(String comuneArrivo) {
			this.comuneArrivo = comuneArrivo;
		}

		public CSVData(String[] data) {
			for (int i = 0; i < data.length; i ++)
				if (data[i].isEmpty())
					data[i] = null;

			this.id = data[0];
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


		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id=id;
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
			return "CSVData [tipologia=" + id + ", collegamento=" + collegamento + ", trattaAndata="
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

		public String getFasciaOraria() {
			return fasciaOraria;
		}

		public void setFasciaOraria(String fasciaOraria) {
			this.fasciaOraria = fasciaOraria;
		}

		public String getStagione() {
			return stagione;
		}

		public void setStagione(String stagione) {
			this.stagione = stagione;
		}

}
