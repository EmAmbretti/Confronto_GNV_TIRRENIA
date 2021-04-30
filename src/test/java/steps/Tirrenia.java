package steps;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.WebData;
import utils.Generic;

public class Tirrenia {
	
	public static void stepTirrenia(WebDriver driver, CSVData testData) throws Throwable {
		WebData	sitoTIRRENIA = new WebData("TIRRENIA",testData);
		
		Generic.utente_apre_browser(driver, "https://www.tirrenia-prenotazioni.it", sitoTIRRENIA.getSito());
		
		
		
		
		
		
	}
}
