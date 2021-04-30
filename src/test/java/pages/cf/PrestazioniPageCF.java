package pages.cf;

import org.openqa.selenium.WebDriver;

import model.WebData;
import utils.Generic;

public class PrestazioniPageCF {
	
	public static void gestioneVeicoloMoby(WebDriver driver, WebData sito) {
		if (sito.getDisponibilita() == null && !sito.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver,"");
			if (sito.getVeicolo().equalsIgnoreCase("CAR")) {

			} else if (sito.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {

			} else if (sito.getVeicolo().equalsIgnoreCase("CMP")) {

			} else if (sito.getVeicolo().equalsIgnoreCase("MOTO")) {

			} else {
				System.out.println("veicolo non disponibile");
			}
		}
	}

}
