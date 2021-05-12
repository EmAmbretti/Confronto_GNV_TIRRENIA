package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.tirrenia.DisponibilitaPageTIRRENIA;
import pages.tirrenia.HomePageTIRRENIA;
import pages.tirrenia.PreventivoPageTIRRENIA;
import pages.tirrenia.RecuperoImportoPageTIRRENIA;
import pages.tirrenia.ServiziPageTIRRENIA;
import utils.BeforeAndAfter;

public class Tirrenia {
	
	public static EsitoSito stepTirrenia(CSVData testData) throws Throwable{
		
		WebDriver driver = BeforeAndAfter.before("TIRRENIA");
		EsitoSito sitoTIRRENIA = new EsitoSito("TIRRENIA",testData);
		HomePageTIRRENIA.inserisciDati(driver, sitoTIRRENIA);
		DisponibilitaPageTIRRENIA.selezionaCorsa(driver, sitoTIRRENIA);
		PreventivoPageTIRRENIA.inserimentoSistemazione(driver, sitoTIRRENIA);
		ServiziPageTIRRENIA.continuaPopUp(driver, sitoTIRRENIA);
		RecuperoImportoPageTIRRENIA.recuperaImportoTIRRENIA(driver, sitoTIRRENIA);
		driver.quit();
		return sitoTIRRENIA;
	}
	
}
