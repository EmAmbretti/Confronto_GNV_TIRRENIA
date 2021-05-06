package pages.gnv;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.EsitoSito;
import utils.Generic;

public class RecapPageGNV {

	public static void recuperoImportoGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			selezionaSistemaione2(driver, esito);
			scegliServizi(driver, esito);
			scegliAssicurazione(driver, esito);
			recuperaPrezzoGNV(driver, esito);
		}
	}
	private static void selezionaSistemaione2(WebDriver driver, EsitoSito esito) throws InterruptedException {
		if(esito.getErrori()==null) {
			int i = 0;
			String sistemazione = "";
			if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("POLTRONA")) {
				sistemazione = "POLTRON";
			} else if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("CAB. INTERNA")) {
				sistemazione = "CABINA INTERNA";
			} else if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("CAB. ESTERNA")) {
				sistemazione = "CABINA VISTA MARE";
			}
			System.out.println("a maronn::" + sistemazione);
			Thread.sleep(7000);
			List<WebElement> listaTesti = driver
					.findElements(By.xpath("//div[@class='card-solution--title d-inline']"));
			List<WebElement> listaSeleziona = driver.findElements(By.xpath("//div[@class='card-solution__btn']"));
			for (WebElement testo : listaTesti) {
				System.out.println("TESTO:::" + testo.getText());
				if(testo.getText().contains(sistemazione)) {
					Thread.sleep(3000);
					listaSeleziona.get(i).click();
					break;
				}else {
					i++;
				}
		}
			if (i == listaTesti.size()) {
				esito.setErrori("la sistemazione \"" + esito.getDatiCsv().getSistemazione() + "\" non è disponibile.");
			}
			Thread.sleep(3000);
			try {
				cliccaContinaGNV(driver, esito);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void selezionaSistemazioneGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			int i = 0;
			String sistemazione = "";
			switch(esito.getDatiCsv().getSistemazione()) {
			case "Cab. Interna":
				sistemazione = "CABINA INTERNA";
			case "Cab. Esterna":
				sistemazione = "CABINA VISTA MARE";
			case "Poltrona":
				sistemazione = "POLTRON";
			}
			boolean flag=false;
			Thread.sleep(7000);
				List<WebElement> listaTesti = driver
						.findElements(By.xpath("//div[@class='card-solution--title d-inline']"));
				List<WebElement> listaSeleziona = driver.findElements(By.xpath("//div[@class='card-solution__btn']"));
				for (WebElement testo : listaTesti) {
					Thread.sleep(2000);
					List<WebElement> orari = driver
							.findElements(By.xpath("//li[@class='list-inline-item departure-time']"));
					System.out.println("ORARIOOOOO:" + orari.get(1).getText());
					if (Generic.controlloFasciaOraria(orari.get(1).getText(), esito).equalsIgnoreCase(esito.getDatiCsv().getFasciaOraria())) {
						System.out.println("CIAO SONO NEL PRIMO IF");
						//WebElement check = Generic.getElementByXPath(driver, "//*[@id='nav-tabContent']/div[2]/p");
					//	if(!check.isDisplayed()) {
					//		System.out.println("Sto nel secondo if");
					//		flag2=true;
						if (testo.getText().contains(sistemazione)) {
							Thread.sleep(3000);
							listaSeleziona.get(i).click();
							flag=true;
							break;
						} else {
							i++;
						}
					}
				/*		if(!flag2) {
							esito.setErrori(check.getText());
							System.out.println(check.getText());
						}
				}*/

				if(!flag) {
					esito.setErrori("Corrispondenza fascia oraria non trovata");
					System.out.println("Corrispondenza fascia oraria non trovata");
				}	
				if (i == listaTesti.size()) {
					esito.setErrori("la sistemazione \"" + esito.getDatiCsv().getSistemazione() + "\" non è disponibile.");
				}
				Thread.sleep(3000);
				cliccaContinaGNV(driver, esito);
			}
		}
	}

	private static void scegliServizi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null)
			cliccaContinaGNV(driver, esito);
	}

	private static void scegliAssicurazione(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null)
			cliccaContinaGNV(driver, esito);
	}

	private static void cliccaContinaGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			try {
				Generic.clickByXPath(driver,
						"//button[@class='btn btn-lg gnv-btn btn-orange btn-medium booking-footer-btn']//span[@class='gnv-icon ng-star-inserted'][contains(.,'Continua')]");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Impossibile cliccare continua");

			}
		}
		Thread.sleep(2000);
	}

	private static void recuperaPrezzoGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				String importo = driver.findElement(By.xpath("//button[@id='cartDropdown']/span[@class='price']"))
						.getText();
				esito.setPrezzo(importo);
				System.out.println("PREZZO GNV: " + importo);
			}catch (Exception e) {
				esito.setErrori("prezzo non recuperato");
			}


		}
	}
}
