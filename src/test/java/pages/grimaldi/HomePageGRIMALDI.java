package pages.grimaldi;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageGRIMALDI {

	public static void gestioneVeicoloGrimaldi(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickById(driver, "carLeg1Select");
			Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[2]/b");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickById(driver, "createcar");
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickByXPath(driver, "//input[@id='catH-2']");
				Generic.clickByXPath(driver, "//input[@id='mtlCar']");
				Generic.sendKeysByXPath(driver, "//input[@id='mtlCar']", "5.50");
				Generic.clickById(driver, "createcar");
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				Generic.clickById(driver, "createcar");
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				Generic.clickById(driver, "createcar");
			} else {
				System.out.println("veicolo non disponibile");
			}

		}

	}
}
