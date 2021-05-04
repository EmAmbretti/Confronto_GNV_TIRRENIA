package pages.tirrenia;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class DisponibilitaPageTIRRENIA {
	
	public static void selezionaCorsa(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null) {
			controlloCorsa(driver, sito);
		}
		if(sito.getErrori()==null) {
			cliccaAvanti(driver);
		}
	}
	
	private static void controlloCorsa(WebDriver driver, EsitoSito esito) throws Throwable {
		boolean flag = false;
		HashMap<String, List<WebElement>> map = new HashMap<String, List<WebElement>>();
		int i=0;
		map.put("nave", driver.findElements(By.xpath("//div[@class='svg-resource logo']")));
		map.put("partenza", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[1]/div")));
		map.put("destinazione", driver.findElements(By.xpath("//*[@id=\"bookingAndata\"]/div/div/div[1]/div/div[2]/div/div[1]/div[3]/div[2]/div")));
		for(WebElement element : map.get("nave")) {
			if(element.getAttribute("data-resource").contains("tirrenia")) {
				if(map.get("partenza").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComunePartenza())) {
					if(map.get("destinazione").get(i).getText().equalsIgnoreCase(esito.getDatiCsv().getComuneArrivo())) {
						element.click();
						flag = true;
						Thread.sleep(500);
						break;
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
	private static void cliccaAvanti(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}
}
