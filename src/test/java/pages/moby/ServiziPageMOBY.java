package pages.moby;

import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import utils.Generic;

public class ServiziPageMOBY {

	public static void continuaPopUp(WebDriver driver, EsitoSito sito) throws Throwable {
		cliccaContinua(driver, sito);
		chiudiPopUp(driver);
	}

	private static void cliccaContinua(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			Generic.clickById(driver, "buttonNextPage");
			Thread.sleep(5000);
		}
	}

	private static void chiudiPopUp(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
		Thread.sleep(1000);
		try {
			Generic.clickByXPath(driver, "//button[@class='cancel'][contains(.,'No, grazie')]");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Secondo PopUp non trovato!");
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
