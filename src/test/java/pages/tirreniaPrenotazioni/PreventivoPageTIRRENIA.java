package pages.tirreniaPrenotazioni;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageTIRRENIA {

	public static void gestioneVeicoloTirrenia(WebDriver driver, CSVData data, EsitoSito esito) throws Throwable {

		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//button[@id='customSelectMobyGuid20']");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "750");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "200");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}
	}

}
