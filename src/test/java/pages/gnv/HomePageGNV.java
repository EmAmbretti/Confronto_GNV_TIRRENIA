package pages.gnv;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageGNV {
	public static void gestioneVeicoloGNV(WebDriver driver, CSVData data, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
		Generic.clickByXPath(driver, "//div[@class='journey-summary-block active search-active last']");
		if (data.getVeicolo().equalsIgnoreCase("CAR")) {
			Generic.clickByXPath(driver, "//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
			Generic.clickByXPath(driver, "//select[@id='height']");
			Generic.clickByXPath(driver, "//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a 1,9m')]");
			Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
		}else if(data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
			Generic.clickByList(driver, "//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 1);
			Generic.clickByXPath(driver, "//select[@id='height']");
			Generic.clickByXPath(driver, "//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a  2,9m')]");
			Generic.clickByXPath(driver, "//select[@id='length']");
			Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 5m  e 6m')]");
			Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
		} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
			Generic.clickByList(driver, "//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 2);
			Generic.clickByXPath(driver, "//select[@id='height']");
			Generic.clickByXPath(driver, "//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a  2,9m')]");
			Generic.clickByXPath(driver, "//select[@id='length']");
			Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 7m  e 8m')]");
			Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
		} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
			Generic.clickByList(driver, "//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 4);
		} else {
			System.out.println("veicolo non disponibile");
		}

	}
	}
}
