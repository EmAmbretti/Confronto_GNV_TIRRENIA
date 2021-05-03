package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.grimaldi.HomePageGRIMALDI;
import pages.grimaldi.RecuperaImportoPageGRIMALDI;
import utils.Generic;

public class GrimaldiLines {
	public static void stepGrimaldi(WebDriver driver, CSVData testData) throws Throwable{
		EsitoSito sitoGRIMALDI = new EsitoSito("GRIMALDI",testData);
		Generic.utente_apre_browser(driver, "https://www.grimaldi-lines.com/it", sitoGRIMALDI.getSito());
		HomePageGRIMALDI.bypassFrame(driver);
		
		HomePageGRIMALDI.cliccaSoloAndataGrimaldi(driver);
		Generic.clickById(driver, "start-route");
		HomePageGRIMALDI.selezionaAndataGrimaldi(driver, testData, sitoGRIMALDI);	
		HomePageGRIMALDI.cliccaSuDataGrimaldi(driver, sitoGRIMALDI);
		HomePageGRIMALDI.prenotaOraGrimaldi(driver, sitoGRIMALDI);
		Generic.switchPage(driver,sitoGRIMALDI);
		HomePageGRIMALDI.chiudiPopupPag2Grimaldi(driver, sitoGRIMALDI);
		HomePageGRIMALDI.scegliDataGrimaldi(driver, testData, sitoGRIMALDI);
		HomePageGRIMALDI.aggiungiSistemazioniPasseggeriGrimaldi(driver, testData, sitoGRIMALDI);
		HomePageGRIMALDI.selezionaAnimaliGrimaldi(driver, testData, sitoGRIMALDI);
		HomePageGRIMALDI.gestioneVeicoloGrimaldi(driver, sitoGRIMALDI, testData);
		HomePageGRIMALDI.cliccaRicercaGrimaldi(driver, sitoGRIMALDI); 
		RecuperaImportoPageGRIMALDI.recuperaImportoGrimaldi(driver, testData, sitoGRIMALDI);
	}
	
}
