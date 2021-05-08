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

	public static void automationSceltaPage(WebDriver driver, EsitoSito sito) {
		if(sito.getErrori()== null) {
			impostazioneVeicoloNull(driver, sito);
			sceltaViaggio(driver, sito);
		}
	}
	
	private static void impostazioneVeicoloNull(WebDriver driver, EsitoSito sito) {
		if(sito.getErrori()==null) {
			System.out.println("\nMetodo impostazioneVeicoloNull");
			try {
				Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[4]/div[2]/input");
				Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[4]/div[2]/div/div/ul[1]/li[5]/span/label");
			} catch (Exception e) {
				System.out.println("ERRORE NELL'IMPOSTAZIONE DEL VEICOLO A NULL");
				sito.setErrori("ERRORE CF: ERRORE NELLA SCELTA VIAGGIO");
			}
		}
	}
	
	private static void sceltaViaggio(WebDriver driver, EsitoSito sito) {
		if(sito.getErrori()==null) {
			System.out.println("\nMetodo sceltaViaggio");
			Generic.waitSeconds(3);
			
			// RECUPERA LISTA VIAGGI
			ArrayList<WebElement> righeTabella = Generic.getElementListByXPath(driver, "//*[@id='SearchView']/div[1]/div/div/div[2]/div/div[1]/div/div/div[3]/div/div[3]/table/tbody/tr");
					
			Translator.traduciTratta(sito);
			boolean trattaTrovata = false;
			
			ArrayList <WebElement> tratteDaValutare = new ArrayList <WebElement>();
			if(righeTabella!=null && !righeTabella.isEmpty()) {
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
									//righeTabella.get(i).click();
									trattaTrovata = true;
									tratteDaValutare.add(righeTabella.get(i));
								}
								
							} catch (NoSuchElementException e) {
								System.out.println("ORARIO NON TROVATO PER QUESTO XPATH");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				System.out.println("ERRORE CF: ERRORE NEL RECUPERARE LISTA TABELLA VIAGGI");
				sito.setErrori("ERRORE CF: ERRORE NEL RECUPERO LISTA VIAGGI");
			}
			
			if(!trattaTrovata) {
				System.out.println("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
				sito.setErrori("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
			} else {
				if(sito.getDatiCsv().getFasciaOraria().contains("NOTTURN") ) {
					if(tratteDaValutare.isEmpty()) {
						System.out.println("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
						sito.setErrori("NESSUNA TRATTA TROVATA PER LA FASCIA ORARIA RICHIESTA");
					} else {
						tratteDaValutare.get(tratteDaValutare.size()-1).click();
					}
				}
			}
			
			//SCROLL DA AGGIUNGERE
			// CLICK BUTTON AVANTI
			if(sito.getErrori()== null) {
				Generic.clickByXPath(driver, "//*[@id=\"SearchView\"]/div[3]/button");
			}
		}
		
	}
}
