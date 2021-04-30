package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.moby.DisponibilitaPageMOBY;
import pages.moby.HomePageMOBY;
import pages.moby.RecuperaImportoPageMOBY;
import pages.moby.ServiziPageMOBY;

public class Moby {
	
	EsitoSito sito;
	
	public void allMethods(WebDriver driver, CSVData testData) throws Throwable {
		sito = new EsitoSito("MOBY", testData); 
		HomePageMOBY.selezionaItinerarioMOBY(null, null);
		DisponibilitaPageMOBY.selezionaCorsa(driver);
		ServiziPageMOBY.continuaPopUp(driver);
		RecuperaImportoPageMOBY.recuperaImportoMoby(driver, null);
	
	}

}
