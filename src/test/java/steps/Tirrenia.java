package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.tirrenia.HomePageTIRRENIA;
import pages.tirrenia.ServiziPageTIRRENIA;
import pages.tirrenia.PreventivoPageTIRRENIA;
import pages.tirrenia.RecuperoImportoPageTIRRENIA;
import pages.tirrenia.DisponibilitaPageTIRRENIA;
import utils.Generic;

public class Tirrenia {
	public static void stepTirrenia(WebDriver driver, CSVData testData) throws Throwable{
		EsitoSito sitoTIRRENIA = new EsitoSito("TIRRENIA",testData);
		Generic.utente_apre_browser(driver, "https://www.tirrenia.it/", sitoTIRRENIA.getSito());
		HomePageTIRRENIA.inserisciDati(driver, sitoTIRRENIA);
		DisponibilitaPageTIRRENIA.selezionaCorsa(driver, sitoTIRRENIA);
		PreventivoPageTIRRENIA.inserimentoSistemazione(driver, sitoTIRRENIA);
		ServiziPageTIRRENIA.continuaPopUp(driver, sitoTIRRENIA);
		RecuperoImportoPageTIRRENIA.recuperaImportoTIRRENIA(driver, sitoTIRRENIA);
	}
	
}
