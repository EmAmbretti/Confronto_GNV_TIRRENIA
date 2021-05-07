package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.cf.HomePageCF;
import pages.cf.PrestazioniPageCF;
import pages.cf.SceltaPageCF;
import utils.BeforeAndAfter;

public class CorsicaFerries {
	
	public static EsitoSito automation(CSVData testData) {
		EsitoSito sito = new EsitoSito("CORSICA FERRIES", testData);
		try {
			WebDriver driver = BeforeAndAfter.before("CORSICA FERRIES");
			HomePageCF.scegliTrattaEData(driver, sito);
			SceltaPageCF.automationSceltaPage(driver, sito);
			PrestazioniPageCF.automationPaginaPrestazioni(driver, sito);
		} catch (Exception e) {
			e.printStackTrace();
			sito.setErrori("ERRORE GENERICO");
		}
		return sito;
	}

}
