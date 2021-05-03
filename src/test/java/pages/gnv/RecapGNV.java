package pages.gnv;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class RecapGNV {

	public static void selezionaSistemazioneGNV(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			int i = 0;
			Thread.sleep(3000);
			List<WebElement> listaTesti = driver
					.findElements(By.xpath("//div/app-card-solution/div/div[1]/div[2]/div[1]/div"));
			List<WebElement> listaSeleziona = driver
					.findElements(By.xpath("//div/app-card-solution/div/div[2]/app-button"));
			for (WebElement testo : listaTesti) {
				if (testo.getText().substring(0, testo.getText().length() - 2)
						.equalsIgnoreCase(sito.getSistemazione().substring(0, sito.getSistemazione().length() - 2))) {
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
		}
	}

	public static void cliccaContinaGNV(WebDriver driver) {
		try {
			Generic.clickByXPath(driver,
					"//button[@class='btn btn-lg gnv-btn btn-orange btn-medium booking-footer-btn']//span[@class='gnv-icon ng-star-inserted'][contains(.,'Continua')]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void recuperaPrezzoGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			String importo = driver.findElement(By.xpath("//button[@id='cartDropdown']/span[@class='price']"))
					.getText();
			esito.setPrezzo(importo);
		}
	}
}