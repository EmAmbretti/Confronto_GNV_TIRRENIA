package pages.grimaldi;

import org.openqa.selenium.WebDriver;
import model.WebData;
import utils.Generic;

public class HomePageGRIMALDI {

	public static void gestioneVeicoloGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if (sito.getDisponibilita() == null && !sito.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickById(driver, "carLeg1Select");
			Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[2]/b");
			if (sito.getVeicolo().equalsIgnoreCase("CAR")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickById(driver, "createcar");
			} else if (sito.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickByXPath(driver, "//input[@id='catH-2']");
				Generic.clickByXPath(driver, "//input[@id='mtlCar']");
				Generic.sendKeysByXPath(driver, "//input[@id='mtlCar']", "5.50");
				Generic.clickById(driver, "createcar");
			} else if (sito.getVeicolo().equalsIgnoreCase("CMP")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				Generic.clickById(driver, "createcar");
			} else if (sito.getVeicolo().equalsIgnoreCase("MOTO")) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				Generic.clickById(driver, "createcar");
			} else {
				System.out.println("veicolo non disponibile");
			}

		}

	}
}