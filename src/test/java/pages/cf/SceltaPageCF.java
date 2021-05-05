package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;
import utils.Translator;

public class SceltaPageCF {

	public static void sceltaViaggio(WebDriver driver, EsitoSito sito) {
		if(sito.getErrori()==null) {
			ArrayList<WebElement> righeTabella = Generic.getElementListByXPath(driver, "//*[@id='SearchView']/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr");
					
			Translator.traduciTratta(sito);
			
			for (int i = 0; i < righeTabella.size(); i++) {
				WebElement tratta = null;
				try {
					tratta = righeTabella.get(i).findElement(By.xpath(".//td[1]/div/div[1]"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(tratta.getText().contains(sito.getDatiCsv().getComunePartenza()) && tratta.getText().contains(sito.getDatiCsv().getComuneArrivo())){
					String orario = tratta.findElement(By.xpath(".//td[2]/div/span/span[1]")).getText().split(":")[0];
					// SE DIURNO E..
					if(Integer.valueOf(orario)<18 && sito.getDatiCsv().getFasciaOraria().equals("DIURNA")) {
						righeTabella.get(i).click();
						// SE NOTTURNO E..
					} else if(Integer.valueOf(orario)>18 && sito.getDatiCsv().getFasciaOraria().equals("NOTTURNA")){
						righeTabella.get(i).click();
					} else {
						sito.setErrori("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
					}
					
				}
			}
			
			// XPATH NOME CITTA TRATTA
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr[2]/td[1]/div/div[1]
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr/td[1]/div/div[1]
	
			// XPATH ORARIO
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr[2]/td[2]/div/span/span[1]
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr/td[2]/div/span/span[1]
			
			// XPATH PREZZO 
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr/td[3]/div/span[2]/span
			// //*[@id="SearchView"]/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr[2]/td[3]/div/span[2]/span
			
			/*
			When using xpath be aware that webdriver follows standard conventions: a search prefixed with "//" will search the entire document, not just the children of this current node. 
			Use ".//" to limit your search to the children of this WebElement.
			So you should be able to do something like:
			WebElement foo = driver.findElement(By.xpath("//*[@title='" + title + "']"));
			WebElement bar = foo.findElement(By.xpath(".//img")); 
			 */
			
			//SCROLL DA AGGIUNGERE
			
			// CLICK BUTTON AVANTI
			Generic.clickByXPath(driver, "//*[@id=\"SearchView\"]/div[3]/button");
		}
		
	}
}
