package pages.moby;

import org.openqa.selenium.WebDriver;
import model.WebData;
import utils.Generic;

public class PreventivoPageMOBY {
	
	public static void inserimentoDatiMoby(WebDriver driver, WebData sito) {
		gestioneVeicoloMoby(driver, sito);
	}
	
	

	private static void gestioneVeicoloMoby(WebDriver driver, WebData sito) {
		if (sito.getDisponibilita() == null && !sito.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//button[@id='customSelectMobyGuid20']");
			if (sito.getVeicolo().equalsIgnoreCase("CAR")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");

			} else if (sito.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");

			} else if (sito.getVeicolo().equalsIgnoreCase("CMP")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");

			} else if (sito.getVeicolo().equalsIgnoreCase("MOTO")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");

			} else {
				System.out.println("veicolo non disponibile");
			}
		}

	}

}
