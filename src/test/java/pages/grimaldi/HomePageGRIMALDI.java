package pages.grimaldi;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageGRIMALDI {

	public static void gestioneVeicoloGrimaldi(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickById(driver, "carLeg1Select");
			Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[2]/b");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickByXPath(driver, "//input[@id='catH-2']");
				Generic.clickByXPath(driver, "//input[@id='mtlCar']");
				Generic.sendKeysByXPath(driver, "//input[@id='mtlCar']", "5.50");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + "non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} 
		}
	}
}
