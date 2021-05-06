package pages.moby;

import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.EsitoSito;
import utils.Generic;


public class DisponibilitaPageMOBY {

	public static void selezionaCorsa(WebDriver driver, EsitoSito esito) throws Throwable {
		controlloCorsa(driver, esito);
		cliccaContinua(driver);
	}

	private static void controlloCorsa(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			boolean flag = false;
			HashMap<String, List<WebElement>> map = new HashMap<String, List<WebElement>>();
			int i=0;
			map.put("nave", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[3]/div[1]/div[1]/div")));
			map.put("partenza", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[1]/div")));
			map.put("destinazione", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[2]/div")));
			map.put("orario", driver.findElements(By.xpath("//span[@class='date bold fg-color']")));
			for(WebElement element : map.get("nave")) {
				if(element.getAttribute("data-resource").contains("moby")) {
					if(map.get("partenza").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComunePartenza())) {
						if(map.get("destinazione").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComuneArrivo())) {
							if(Generic.controlloFasciaOraria(map.get("orario").get(i).getText(), esito).equalsIgnoreCase(esito.getDatiCsv().getFasciaOraria())) {
								element.click();
								flag = true;
								Thread.sleep(500);
								break;
							} else {
								System.out.println("ERRORE: fascia oraria non disponibile.");
								esito.setErrori("La fascia oraria scelta per questo sito non è disponibile.");
							}
						}
					}
				}
				i++;
			}
			if(!flag) {
				System.out.println("ERRORE: TRATTA");
				esito.setErrori("La tratta per questo sito non è disponibile.");
			}
		}
	}

	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}
}
