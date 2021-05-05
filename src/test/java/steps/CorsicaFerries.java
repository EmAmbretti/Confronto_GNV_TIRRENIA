package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.cf.HomePageCF;
import pages.cf.PrestazioniPageCF;
import pages.cf.SceltaPageCF;
import utils.BeforeAndAfter;

public class CorsicaFerries {
	
	public static void automation(CSVData testData) {
		try {
			WebDriver driver = BeforeAndAfter.before("CORSICA FERRIES");
			EsitoSito sito = new EsitoSito("CORSICA FERRIES", testData);
			HomePageCF.scegliTrattaEData(driver, sito);
			SceltaPageCF.sceltaViaggio(driver, sito);
			PrestazioniPageCF.automationPaginaPrestazioni(driver, sito);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
