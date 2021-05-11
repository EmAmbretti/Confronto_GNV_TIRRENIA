package pages.moby;

import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.EsitoSito;
import utils.Config;
import utils.Generic;


public class DisponibilitaPageMOBY {

	public static void selezionaCorsa(WebDriver driver, EsitoSito esito) {
		controlloCorsaMOBY(driver, esito);
		cliccaContinuaMOBY(driver, esito);
	}

	private static void controlloCorsaMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: controlloCorsaMOBY");
			try {
				boolean controlloNave = false;
				boolean controlloPartenza = false;
				boolean controlloDestinazione = false;
				boolean controlloOrario = false;
				HashMap<String, List<WebElement>> map = new HashMap<String, List<WebElement>>();
				int i=0;
				map.put("nave", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[3]/div[1]/div[1]/div")));
				map.put("partenza", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[1]/div")));
				map.put("destinazione", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[2]/div")));
				map.put("orario", driver.findElements(By.xpath("//span[@class='date bold fg-color']")));
				for(WebElement element : map.get("nave")) {
					if(element.getAttribute("data-resource").contains("moby")) {
						controlloNave = true;
						if(map.get("partenza").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComunePartenza())) {
							controlloPartenza = true;
							if(map.get("destinazione").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComuneArrivo())) {
								controlloDestinazione = true;
								if(Generic.controlloFasciaOraria(map.get("orario").get(i).getText(), esito).equalsIgnoreCase(Config.get("fasciaOraria").toUpperCase())) {
									element.click();
									controlloOrario = true;
									Thread.sleep(500);
									break;
								} 
//								else {
//									System.out.println("ERRORE: fascia oraria non disponibile.");
//									esito.setErrori("La fascia oraria scelta per questo sito non è disponibile.");
//								}
							}
						}
					}
					i++;
				}
				if (!controlloOrario) {
					System.out.println("ERRORE: ORARIO");
					esito.setErrori("La fascia oraria per questo sito non è disponibile.");
				}
				if (!controlloDestinazione) {
					System.out.println("ERRORE: DESTINAZIONE");
					esito.setErrori("La destinazione per questo sito non è disponibile.");
				}
				if (!controlloPartenza) {
					System.out.println("ERRORE: PARTENZA");
					esito.setErrori("La partenza per questo sito non è disponibile.");
				}
				if (!controlloNave) {
					System.out.println("ERRORE: PARTENZA");
					esito.setErrori("La nave MOBY per questo sito non è disponibile.");
				}
			} catch (Exception e) {
				esito.setErrori("Corsa non disponibile");
				System.out.println("ERRORE: controlloCorsaMOBY");
			}	
		}
	}

	private static void cliccaContinuaMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaContinuaMOBY");
			try {

				Generic.clickById(driver, "buttonNextPage");
				Thread.sleep(2000);
			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("ERRORE: cliccaContinuaMOBY");
			}
		}
	}
	
}
