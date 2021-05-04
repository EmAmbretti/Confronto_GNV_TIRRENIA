package pages.tirrenia;

import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import utils.Generic;

public class ServiziPageTIRRENIA {
	public static void continuaPopUp(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			cliccaContinua(driver, sito);
		}
		if(sito.getErrori() == null) {
			chiudiPopUp(driver);
		}
		
	}

	private static void cliccaContinua(WebDriver driver, EsitoSito sito) throws Throwable {
			Generic.clickById(driver, "buttonNextPage");
			Thread.sleep(3000);
	}

	private static void chiudiPopUp(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
		Thread.sleep(2000);
	}
}
