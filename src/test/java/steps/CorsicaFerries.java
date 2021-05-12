package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.cf.HomePageCF;
import pages.cf.PrestazioniPageCF;
import pages.cf.SceltaPageCF;
import utils.BeforeAndAfter;
import utils.Generic;

public class CorsicaFerries {
	
	public static EsitoSito automation(CSVData testData) {
		CSVData datiCSV = Generic.clonaOggetto(testData);
		EsitoSito sito = new EsitoSito("CORSICA FERRIES", datiCSV);
		try {
			WebDriver driver = BeforeAndAfter.before("CORSICA FERRIES");
			HomePageCF.scegliTrattaEData(driver, sito);
			SceltaPageCF.automationSceltaPage(driver, sito);
			PrestazioniPageCF.automationPaginaPrestazioni(driver, sito);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			sito.setErrori("ERRORE GENERICO");
		}
		return sito;
	}

}
