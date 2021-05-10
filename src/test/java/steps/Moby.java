package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.moby.DisponibilitaPageMOBY;
import pages.moby.HomePageMOBY;
import pages.moby.PreventivoPageMOBY;
import pages.moby.RecuperaImportoPageMOBY;
import pages.moby.ServiziPageMOBY;
import utils.BeforeAndAfter;

public class Moby {
	
	public static EsitoSito allMethods(CSVData testData) {
		
		WebDriver driver = BeforeAndAfter.before("MOBY");
		EsitoSito sitoMOBY = new EsitoSito("MOBY", testData); 
		HomePageMOBY.selezionaItinerarioMOBY(driver, sitoMOBY, testData);
		DisponibilitaPageMOBY.selezionaCorsa(driver, sitoMOBY);
		PreventivoPageMOBY.inserimentoDatiMoby(driver, sitoMOBY, testData);
		//ServiziPageMOBY.continuaPopUp(driver, sitoMOBY);
		RecuperaImportoPageMOBY.recuperaImportoMOBY(driver, sitoMOBY);
		driver.quit();
		return sitoMOBY;
		
	}

}
