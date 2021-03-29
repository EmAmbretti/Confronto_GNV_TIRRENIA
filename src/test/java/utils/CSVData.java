package utils;

public class CSVData {

		private String collegamento = null;
		private String trattaAndata = null;
		private String meseAndata = null;
		private String giornoAndata = null;
		private String passeggeriAdulti = null;
		private String passeggeriBambini = null;
		private String passeggeriAnimali = null;
		private String veicolo = null;

		public CSVData(String[] data) {
			for (int i = 0; i < data.length; ++i)
				if (data[i].isEmpty())
					data[i] = null;

			this.collegamento = data[0];
			this.trattaAndata = data[1];
			this.meseAndata = data[2];
			this.giornoAndata = data[3];
			this.passeggeriAdulti = data[4];
			this.passeggeriBambini = data[5];
			this.passeggeriAnimali = data[6];
			this.veicolo = data[7];
		}

		public CSVData() {

		}

		public CSVData(String collegamento, String trattaAndata, String meseAndata, String giornoAndata, String passeggeriAdulti, String passeggeriBambini, String passeggeriAnimali,
				String veicolo) {
			this.collegamento = collegamento;
			this.trattaAndata = trattaAndata;
			this.meseAndata = meseAndata;
			this.giornoAndata = giornoAndata;
			this.passeggeriAdulti = passeggeriAdulti;
			this.passeggeriBambini = passeggeriBambini;
			this.passeggeriAnimali = passeggeriAnimali;
			this.veicolo = veicolo;
			
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

		@Override
		public String toString() {
			return "CSVData [collegamento=" + collegamento + ", trattaAndata=" + trattaAndata + ", meseAndata="
					+ meseAndata + ", giornoAndata=" + giornoAndata + ", passeggeriAdulti=" + passeggeriAdulti
					+ ", passeggeriBambini=" + passeggeriBambini + ", passeggeriAnimali=" + passeggeriAnimali
					+ ", veicolo=" + veicolo + "]";
		}
		


	
}
