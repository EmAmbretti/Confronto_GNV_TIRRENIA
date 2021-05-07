package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import pages.grimaldi.BookingPageGRIMALDI;
import pages.grimaldi.HomePageGRIMALDI;
import pages.grimaldi.RecuperaImportoPageGRIMALDI;
import utils.BeforeAndAfter;
import utils.Generic;

public class GrimaldiLines {
	
	public static EsitoSito stepGrimaldi(CSVData testData) throws Throwable{
		
		WebDriver driver = BeforeAndAfter.before("GRIMALDI");
		EsitoSito sitoGRIMALDI = new EsitoSito("GRIMALDI",testData);
		HomePageGRIMALDI.compilaHomePageGRIMALDI(driver, sitoGRIMALDI);
		Generic.switchPage(driver,sitoGRIMALDI);
		BookingPageGRIMALDI.compilaDatiBookingGRIMALDI(driver, sitoGRIMALDI);
		RecuperaImportoPageGRIMALDI.recuperaImportoGrimaldi(driver, sitoGRIMALDI);
		return sitoGRIMALDI;
	}
	
}
