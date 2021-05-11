package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.tirreniaPrenotazioni.HomePageTIRRENIA;
import pages.tirreniaPrenotazioni.RecapPageTIRRENIA;
import pages.tirreniaPrenotazioni.RecuperaImportoPageTIRRENIA;
import utils.Generic;

public class TirreniaPrenotazioni {

	public static void stepTirrenia(WebDriver driver, CSVData testData) throws Throwable {
		EsitoSito sitoTIRRENIA = new EsitoSito("TIRRENIA",testData);
		//Generic.utente_apre_browser(driver, "https://www.tirrenia-prenotazioni.it", sitoTIRRENIA.getSito());
		HomePageTIRRENIA.bypassFrame(driver);
		HomePageTIRRENIA.inserisciDatiTirrenia(driver, sitoTIRRENIA, testData);
		Generic.switchPage(driver,sitoTIRRENIA); 
		RecapPageTIRRENIA.controlloDisponibilitaFinaleTirrenia(driver, sitoTIRRENIA);
		RecapPageTIRRENIA.controlloDisponibilitaSistemazioneTirrenia(driver, sitoTIRRENIA);
		RecuperaImportoPageTIRRENIA.recuperaImportoTIRRENIA(driver, sitoTIRRENIA);	
		System.out.println("Prezzo: "+sitoTIRRENIA.getPrezzo());
		System.out.println("Eventuali errori: "+sitoTIRRENIA.getErrori());
	}
}
