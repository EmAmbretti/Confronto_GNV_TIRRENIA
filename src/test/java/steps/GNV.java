package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.gnv.HomePageGNV;
import pages.gnv.RecapPageGNV;
import utils.BeforeAndAfter;

public class GNV {
	
	public static EsitoSito allMethods(CSVData data) throws Throwable {
		WebDriver driver = BeforeAndAfter.before("GNV");
		EsitoSito esito = new EsitoSito("GNV", data);
		HomePageGNV.selezionaItinerarioGNV(driver, esito);
		RecapPageGNV.recuperoImportoGNV(driver, esito);
		driver.quit();
		return esito;
	}

}
