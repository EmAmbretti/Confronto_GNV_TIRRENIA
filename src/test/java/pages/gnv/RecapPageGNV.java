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
		System.out.println("ERRORE::: " + esito.getErrori());
		if (esito.getErrori() == null) {
			System.out.println("BUONASERA");
			int i = 0;
			Thread.sleep(3000);
			List<WebElement> listaTesti = driver
					.findElements(By.xpath("//div[@class='card-solution--title d-inline']"));
			List<WebElement> listaSeleziona = driver
					.findElements(By.xpath("//div[@class='card-solution__btn']"));
			for (WebElement testo : listaTesti) {
				System.out.println("sto nel for bro");
				if (testo.getText().substring(0, testo.getText().length() - 2)
						.equalsIgnoreCase(sito.getSistemazione().substring(0, sito.getSistemazione().length() - 2))) {
					Thread.sleep(3000);
					System.out.println("sto nell'if brotherrr");
					listaSeleziona.get(i).click();
					break;
				} else {
					i++;
				}
			}
			if (i == listaTesti.size()) {
				esito.setErrori("la sistemazione \"" + sito.getSistemazione() + "\" non è disponibile.");
			}
			Thread.sleep(3000);
			cliccaContinaGNV(driver);
		}
	}
	
	private static void scegliServizi(WebDriver driver) throws Throwable  {
		cliccaContinaGNV(driver);
	}
	
	private static void scegliAssicurazione(WebDriver driver) throws Throwable  {
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