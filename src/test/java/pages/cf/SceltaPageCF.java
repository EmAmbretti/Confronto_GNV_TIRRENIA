package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;
import utils.Translator;

public class SceltaPageCF {

	public static void sceltaViaggio(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo sceltaViaggio");
		if(sito.getErrori()==null) {
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			ArrayList<WebElement> righeTabella = Generic.getElementListByXPath(driver, "//*[@id='SearchView']/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr");
					
			Translator.traduciTratta(sito);
			boolean trattaTrovata = false;
			for (int i = 0; i < righeTabella.size(); i++) {
				WebElement tratta = null;
				boolean flag = false;
				try {
					tratta = righeTabella.get(i).findElement(By.xpath(".//td[1]/div/div[1]"));
					flag = true;
				} catch (NoSuchElementException e) {
					System.out.println("TRATTA NON TROVATA PER QUESTO xPath");
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(flag) {
					String testoTratta = tratta.getText().replace("\n", " ");
					if(testoTratta.contains(sito.getDatiCsv().getComunePartenza()) && testoTratta.contains(sito.getDatiCsv().getComuneArrivo())){
						String orario = null;
						
						try {
							orario = righeTabella.get(i).findElement(By.xpath(".//td[2]/div/span/span[1]")).getText().split(":")[0];
						
							// SE DIURNO E..
							if(Integer.valueOf(orario)<18 && sito.getDatiCsv().getFasciaOraria().contains("DIURN")) {
								righeTabella.get(i).click();
								trattaTrovata = true;
								break;
								// SE NOTTURNO E..
							} else if(Integer.valueOf(orario)>18 && sito.getDatiCsv().getFasciaOraria().contains("NOTTURN")){
								righeTabella.get(i).click();
								trattaTrovata = true;
								break;
							}
							
						} catch (NoSuchElementException e) {
							System.out.println("ORARIO NON TROVATO PER QUESTO XPATH");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			if(!trattaTrovata) {
				sito.setErrori("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
			}
			
			//SCROLL DA AGGIUNGERE
			// CLICK BUTTON AVANTI
			Generic.clickByXPath(driver, "//*[@id=\"SearchView\"]/div[3]/button");
			
		}
		
	}
}
