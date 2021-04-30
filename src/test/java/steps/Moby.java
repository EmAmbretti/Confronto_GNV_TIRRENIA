package steps;

import org.openqa.selenium.WebDriver;

import pages.moby.DisponibilitaPageMOBY;
import pages.moby.HomePageMOBY;
import pages.moby.RecuperaImportoPageMOBY;
import pages.moby.ServiziPageMOBY;

public class Moby {
	
	public void faiCose(WebDriver driver) throws Throwable {
		HomePageMOBY.selezionaItinerarioMOBY(null, null);
		DisponibilitaPageMOBY.selezionaCorsa(driver);
		ServiziPageMOBY.continuaPopUp(driver);
		RecuperaImportoPageMOBY.recuperaImportoMoby(driver, null);
	
	}

}
