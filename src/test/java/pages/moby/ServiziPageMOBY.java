package pages.moby;

import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import utils.Generic;

public class ServiziPageMOBY {

	public static void continuaPopUp(WebDriver driver, EsitoSito esito) {
		cliccaContinuaMOBY(driver, esito);
		chiudiPopUpMOBY(driver, esito);
	}

	private static void cliccaContinuaMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaContinuaMOBY");
			try {
				Generic.clickById(driver, "buttonNextPage");
				Thread.sleep(5000);

			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("ERRORE: cliccaContinuaMOBY");
			}
		}
	}

	private static void chiudiPopUpMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: chiudiPopUpMOBY");
			try {
				Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
				Thread.sleep(1000);
				try {
					Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
					Thread.sleep(2000);
				} catch (Exception e) {
					System.out.println("Secondo PopUp non trovato!");
				}
			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("ERRORE: chiudiPopUpMOBY");
			}
		}
	}

	//	private static void chiudiPopUp(WebDriver driver, boolean assicurazione) {
	//if(sito.getErrori() == null) {
	//		if(assicurazione) {
	//			Generic.clickByXPath(driver, "/html/body/div[15]/div[7]/div/button");
	//		} else {
	//			Generic.clickByXPath(driver, "/html/body/div[15]/div[7]/button");
	//		}	
	//	}
	//	}

}
