package steps;

import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import pages.cf.HomePageCF;
import pages.cf.PrestazioniPageCF;
import pages.cf.SceltaPageCF;

public class CorsicaFerries {
	
	public static void automation(WebDriver driver, EsitoSito sito) {
		try {
			HomePageCF.scegliTrattaEData(driver, sito);
			SceltaPageCF.sceltaViaggio(driver, sito);
			PrestazioniPageCF.automationPaginaPrestazioni(driver, sito);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
