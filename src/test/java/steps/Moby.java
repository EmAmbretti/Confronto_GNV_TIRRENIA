package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.moby.DisponibilitaPageMOBY;
import pages.moby.HomePageMOBY;
import pages.moby.RecuperaImportoPageMOBY;
import pages.moby.ServiziPageMOBY;
import utils.BeforeAndAfter;

public class Moby {
	
	EsitoSito sito;
	WebDriver driver;
	
	public void allMethods(CSVData testData) throws Throwable {
		driver = BeforeAndAfter.before("MOBY");
		sito = new EsitoSito("MOBY", testData); 
		HomePageMOBY.selezionaItinerarioMOBY(driver, sito, testData);
		DisponibilitaPageMOBY.selezionaCorsa(driver);
		ServiziPageMOBY.continuaPopUp(driver, sito);
		RecuperaImportoPageMOBY.recuperaImportoMoby(driver, sito);	
	}

}
