package pages.gnv;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class RecapPageGNV {

	public static void recuperoImporto(WebDriver driver, CSVData data, EsitoSito esito) throws Throwable {
		selezionaSistemazioneGNV(driver, data, esito);
		scegliServizi(driver);
		scegliAssicurazione(driver);
		recuperaPrezzoGNV(driver, esito);
	}

	private static void selezionaSistemazioneGNV(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			int i = 0;
			boolean flag=false;
			Thread.sleep(5000);
			List<WebElement> listaTesti = driver
					.findElements(By.xpath("//div[@class='card-solution--title d-inline']"));
			List<WebElement> listaSeleziona = driver.findElements(By.xpath("//div[@class='card-solution__btn']"));
			for (WebElement testo : listaTesti) {
				Thread.sleep(2000);
				List<WebElement> orari = driver
						.findElements(By.xpath("//li[@class='list-inline-item departure-time']"));
				if (Generic.controlloFasciaOraria(orari.get(1).getText()).equalsIgnoreCase(sito.getFasciaOraria())) {
					System.out.println("CIAO SONO NEL PRIMO IF");
					if (testo.getText().substring(0, testo.getText().length() - 2).equalsIgnoreCase(
							sito.getSistemazione().substring(0, sito.getSistemazione().length() - 2))) {
						Thread.sleep(3000);
						listaSeleziona.get(i).click();
						flag=true;
						break;
					} else {
						i++;
					}
				}
			}
			System.out.println("flag: " + flag);
			if(!flag) {
				esito.setErrori("Corrispondenza fascia oraria non trovata");
				System.out.println("Corrispondenza fascia oraria non trovata");
			}	
			if (i == listaTesti.size()) {
				esito.setErrori("la sistemazione \"" + sito.getSistemazione() + "\" non è disponibile.");
			}
			Thread.sleep(3000);
			cliccaContinaGNV(driver);
		}
	}

	private static void scegliServizi(WebDriver driver) throws Throwable {
		cliccaContinaGNV(driver);
	}

	private static void scegliAssicurazione(WebDriver driver) throws Throwable {
		cliccaContinaGNV(driver);
	}

	private static void cliccaContinaGNV(WebDriver driver) throws Throwable {
		try {
			Generic.clickByXPath(driver,
					"//button[@class='btn btn-lg gnv-btn btn-orange btn-medium booking-footer-btn']//span[@class='gnv-icon ng-star-inserted'][contains(.,'Continua')]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
	}

	private static void recuperaPrezzoGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			String importo = driver.findElement(By.xpath("//button[@id='cartDropdown']/span[@class='price']"))
					.getText();
			esito.setPrezzo(importo);
			System.out.println("PREZZO GNV: " + importo);
		}
	}
}
