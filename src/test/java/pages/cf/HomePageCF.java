package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;
import utils.Translator;

public class HomePageCF {
		
	public static void scegliTrattaEData(WebDriver driver, EsitoSito sito) {
		
		Generic.utente_apre_browser(driver, "https://www.corsica-ferries.it/", sito.getSito(), sito);

		Generic.clickByXPath(driver, "//*[@id='popup-cookie']/div/button[2]");
		scegliTratta(driver, sito);
		scegliDataViaggio(driver, sito);
		
		// CLICK BUTTON
		if(sito.getErrori()==null) {
			Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[4]/button");
		}
	}
	
	private static void scegliTratta(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo ScegliTratta");
		// CLICK SOLO ANDATA
		Generic.clickByXPath(driver, "//*[@id=\"gwt-uid-8\"]");
		
		// CLICK PER SCELTA TRATTA
		Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[1]/div");
		
		// RECUPERO LISTA VIAGGI 
		ArrayList<WebElement> listaViaggi = Generic.getElementListByXPath(driver, "/html/body/div/ul/li");

		Translator.modificaTratta(sito);
		
		// SCELTA TRATTA
		boolean flag = false;
		if(listaViaggi!=null && !listaViaggi.isEmpty()) {
			for (WebElement webElement : listaViaggi) {
				if(webElement.getText().contains(sito.getDatiCsv().getComunePartenza()) && webElement.getText().contains(sito.getDatiCsv().getComuneArrivo())) {
					String trattaRecuperataDalSito = webElement.getText();
					String[] tratte = null;
					try {
					 tratte = trattaRecuperataDalSito.split(sito.getDatiCsv().getComunePartenza());
					} catch (Exception e) {
						System.out.println("ERRORE CF: ERRORE SPLIT TRATTA");
						sito.setErrori("ERRORE CF: ERRORE NEL RECUPERARE LISTA TRATTE");
					}
					if(tratte[1].contains("➔")) {
					webElement.click();
					flag = true;
					break;
					}
				}
			}
		} else {
			sito.setErrori("ERRORE CF: ERRORE NEL RECUPERARE LISTA TRATTE");
		}
		
		if(!flag) {
			sito.setErrori("TRATTA NON TROVATA SUL SITO CORSICA FERRIES");
		}
	}
	
	private static void scegliDataViaggio(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo ScegliDataViaggio");
		if(sito.getErrori()==null) {
			// CLICK PER SCELTA DATA
			Generic.clickByXPath(driver, "//*[@id=\"sod\"]");
			
			System.out.println("\nScelta mese...");
			// CLICK PER SCELTA MESE
			Generic.clickByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]");
			
			// SCELTA MESE 
			ArrayList<WebElement> listaMesiAnno = Generic.getElementListByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]/option");
			if(listaMesiAnno!=null && !listaMesiAnno.isEmpty()) {	
				for (WebElement webElement : listaMesiAnno) {
					if(webElement.getText().toLowerCase().equals(sito.getDatiCsv().getMeseAndata().toLowerCase() + " " + sito.getDatiCsv().getAnno().toLowerCase()) ) {
						webElement.click(); 
						Generic.clickByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]");
					}
				}
			} else {
				sito.setErrori("ERRORE CF: ERRORE NELLA SCELTA DATA");
			}
			
			System.out.println("\nScelta Giorno...");
			// SCELTA GIORNO
			ArrayList<WebElement> listaGiorni = Generic.getElementListByXPath(driver, "//table[@class='datePickerDays']/tbody/tr/td/div");
			if(listaGiorni!=null && !listaGiorni.isEmpty()) {
				for (int i=0; i<listaGiorni.size(); i++) {
					if(!listaGiorni.get(i).getText().equals("1")) {
						listaGiorni.remove(i);
						i--;
					} else {
						break;
					}
				}
			} else {
				sito.setErrori("ERRORE CF: ERRORE NELLA SCELTA DATA");
			}
			
			if(sito.getErrori()==null) {
				listaGiorni.get( (Integer.valueOf(sito.getDatiCsv().getGiornoAndata() ) - 1 ) ).click();
			}
		} else {
			System.out.println("ERRORI: "+sito.getErrori()+"\n");
		}
	}
	
}
