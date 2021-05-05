package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.grimaldi.BookingPageGRIMALDI;
import pages.grimaldi.HomePageGRIMALDI;
import pages.grimaldi.RecuperaImportoPageGRIMALDI;
import utils.Generic;

public class GrimaldiLines {
	public static void stepGrimaldi(WebDriver driver, CSVData testData) throws Throwable{
		EsitoSito sitoGRIMALDI = new EsitoSito("GRIMALDI",testData);
		Generic.utente_apre_browser(driver, "https://www.grimaldi-lines.com/it", sitoGRIMALDI.getSito());
		HomePageGRIMALDI.compilaHomePageGRIMALDI(driver, sitoGRIMALDI);
		Generic.switchPage(driver,sitoGRIMALDI);
		BookingPageGRIMALDI.compilaDatiBookingGRIMALDI(driver, sitoGRIMALDI);
		RecuperaImportoPageGRIMALDI.recuperaImportoGrimaldi(driver, sitoGRIMALDI);
	}
	
}
