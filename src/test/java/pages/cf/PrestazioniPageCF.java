package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class PrestazioniPageCF {

	public static void automationPaginaPrestazioni(WebDriver driver, EsitoSito sito) {
		System.out.println("\nAutomationPaginaPrestazioni");
		if (sito.getErrori() == null) {
			gestionePasseggeri(driver, sito);
			gestioneVeicoloCF(driver, sito);
			gestioneSistemazione(driver, sito);
			sceltaTariffa(driver, sito);
		} else {
			System.out.println("ERRORE: " + sito.getErrori());
		}
	}

	private static void gestionePasseggeri(WebDriver driver, EsitoSito esito) {
		System.out.println("\nMetodo gestionePasseggeri");

		Generic.waitSeconds(3);

		int i = 1;
		int numeroPasseggeriAdulti = Integer.valueOf(esito.getDatiCsv().getPasseggeriAdulti());
		while (i < numeroPasseggeriAdulti) {
			i++;
			
			Generic.clickByXPath(driver,
					"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]");
			Generic.waitSeconds(2);
		} // //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]
			// //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]
		i = 0;
		int numeroPasseggeriBambini = Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini());
		while (i < numeroPasseggeriBambini) {
			i++;
			Generic.clickByXPath(driver,
					"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]");
			Generic.waitSeconds(2);
		}

		i = 1;
		int numeroPasseggeriAnimali = Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali());
		if (numeroPasseggeriAnimali != 0) {
			Generic.clickByXPath(driver,
					"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/div/span/span[1]");
			Generic.waitSeconds(2);
		}
		while (i < numeroPasseggeriAnimali) {
			i++;
			// + CANI
			Generic.clickByXPath(driver,
					"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/ul/li[1]/div[2]/button[2]");
			Generic.waitSeconds(2);
		}

	}

	private static void gestioneVeicoloCF(WebDriver driver, EsitoSito esito) {
		System.out.println("\nMetodo gestioneVeicoloCF");
		
		Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[1]/div[2]/button[1]");
		
		if (esito.getErrori() == null && !esito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			
			ArrayList<WebElement> listaDivAuto = Generic.getElementListByXPath(driver, "//div[@class='travel-go']/div/div[@class='choice']/ul/li");
			for (int i = 0; i < listaDivAuto.size(); i++) {
				
				if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
					//listaDivAuto.get(i).findElement(By.xpath())
	
				} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
					
	
				} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) { 
					
					
				} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
					
				}
			}
		} else {
			Generic.clickByXPath(driver,
					"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[5]/span/span[1]");
			System.out.println("Non ci sono veicoli da inserire.");
		}
	}

	// Click + "Bici":
	// //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[4]/div[2]/button[2]

	private static void gestioneSistemazione(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo gestioneSistemazione");
		// LISTA CONTENENTE TUTTE LE SISTEMAZIONI VISIBILI A FRONT END
		ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver,
				"//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[4]/div[4]/div/div[1]/div[2]/ul/li[@class='choice-item choice-item--withHead']/div[2]");

		for (int i = 0; i < elementList.size(); i++) {
			if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("POLTRON")) {
				if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().toUpperCase()
						.contains("POLTRON")) {
					int whilev = 0;
					int numeroPasseggeriAdulti = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti());
					while (whilev < numeroPasseggeriAdulti) {
						whilev++;
						elementList.get(i).findElement(By.xpath(".//div//div[@class='quantity']/button[2]")).click();
					}
				}
			}

			System.out.println("SISTEMAZIONE CSV: "+sito.getDatiCsv().getSistemazione()+", SISTEMAZIONE SITO: "+elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText());
			if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("ESTERNA")) {
				if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().toLowerCase()
						.contains("cabina esclusiva con oblò")) {
					elementList.get(i).findElement(By.xpath(".//div/button[1]")).click();
					elementList.get(i).findElement(By.xpath(".//div/div/div/div/div/div[@class='quantity']/button[1]"))
							.click();
					elementList.get(i).findElement(By.xpath(".//div[2]/div/button[2]")).click();
					break;
				}
			}

			if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("INTERNA")) {
				if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().toLowerCase()
						.contains("cabina esclusiva senza oblò")) {
					elementList.get(i).findElement(By.xpath(".//div/button[1]")).click();
					elementList.get(i).findElement(By.xpath(".//div/div/div/div/div/div[@class='quantity']/button[1]"))
							.click();
					elementList.get(i).findElement(By.xpath(".//div[2]/div/button[2]")).click();
					break;
				}
			}
		}

	}

	private static void sceltaTariffa(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo sceltaTariffa");

		Generic.waitSeconds(4);

		ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver,
				"//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[8]/div[2]/div/div[@class='BookingRateSummary-item']");

		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).findElement(By.xpath(".//div[1]/span[@class='price-name']")).getText()
					.contains("STANDARD")) {
				elementList.get(i).findElement(By.xpath(".//div[2]")).click();
				break;
			}
		}

		Generic.scrollPage(driver, "document.body.scrollHeight");

		Generic.waitSeconds(3);

		sito.setPrezzo(
				Generic.getElementByXPath(driver, "//*[@id=\"content\"]/div/div[2]/div/div/div[2]/div[2]/div/div[2]")
						.getText());
		System.out.println("PREZZO RECUPERATO: " + sito.getPrezzo());
	}

}
