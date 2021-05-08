package model;

public class Differenza {

	EsitoSito grimaldi;
	EsitoSito competitor;
	Double differenzaPrezzo;
	int differenzaPrezzoPercentuale;

	public EsitoSito getGrimaldi() {
		return grimaldi;
	}

	public void setGrimaldi(EsitoSito grimaldi) {
		this.grimaldi = grimaldi;
	}

	public EsitoSito getCompetitor() {
		return competitor;
	}

	public void setCompetitor(EsitoSito competitor) {
		this.competitor = competitor;
	}

	public Double getDifferenzaPrezzo() {
		return differenzaPrezzo;
	}

	public void setDifferenzaPrezzo(Double differenzaPrezzo) {
		this.differenzaPrezzo = differenzaPrezzo;
	}

	public int getDifferenzaPrezzoPercentuale() {
		return differenzaPrezzoPercentuale;
	}

	public void setDifferenzaPrezzoPercentuale(int differenzaPrezzoPercentuale) {
		this.differenzaPrezzoPercentuale = differenzaPrezzoPercentuale;
	}

	public Differenza(EsitoSito grimaldi, EsitoSito competitor) {
		this.grimaldi = grimaldi;
		this.competitor = competitor;
		if(grimaldi.getErrori() == null && competitor.getErrori() == null) {
			System.out.println("GRIMALDI : " + grimaldi.getPrezzo());
			System.out.println("COMPETITOR: " + competitor.getPrezzo());
			if(grimaldi.getPrezzo() != null && competitor.getPrezzo() != null) {
				differenzaPrezzo = Double.valueOf(grimaldi.getPrezzo()) - Double.valueOf(competitor.getPrezzo());
				differenzaPrezzoPercentuale = 100 - (int) Math.round((Double.valueOf(grimaldi.getPrezzo()) / Double.valueOf(competitor.getPrezzo()) * 100));
			}
		}
	}

}
