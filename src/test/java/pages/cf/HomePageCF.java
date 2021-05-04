package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;
import utils.Translator;

public class HomePageCF {
		
	public static void scegliTrattaEData(WebDriver driver, EsitoSito sito) {
		driver.get("https://www.corsica-ferries.it/");
		Generic.clickByXPath(driver, "//*[@id='popup-cookie']/div/button[2]");
		scegliTratta(driver, sito);
		scegliDataViaggio(driver, sito);
		if(sito.getErrori()==null)
		Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[4]/button");
	}
	
	private static void scegliTratta(WebDriver driver, EsitoSito sito) {
		System.out.println("Metodo ScegliTratta");
		// CLICK SOLO ANDATA
		Generic.clickByXPath(driver, "//*[@id=\"gwt-uid-8\"]");
		
		// CLICK PER SCELTA TRATTA
		Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[1]/div");
		
		// RECUPERO LISTA VIAGGI 
		ArrayList<WebElement> listaViaggi = Generic.getElementListByXPath(driver, "/html/body/div/ul/li");

		Translator.modificaTratta(sito);
		System.out.println("PARTENZA: "+sito.getDatiCsv().getComunePartenza()+", ARRIVO: "+sito.getDatiCsv().getComuneArrivo());
		
		// SCELTA TRATTA
		boolean flag = false;
		for (WebElement webElement : listaViaggi) {
			if(webElement.getText().contains(sito.getDatiCsv().getComunePartenza()) && webElement.getText().contains(sito.getDatiCsv().getComuneArrivo())) {
				String trattaRecuperataDalSito = webElement.getText();
				String[] tratte = trattaRecuperataDalSito.split(sito.getDatiCsv().getComunePartenza());
				if(tratte[1].contains("➔")) {
				webElement.click();
				flag = true;
				break;
				}
			}
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
			
			// CLICK PER SCELTA MESE
			Generic.clickByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]");
			
			// SCELTA MESE 
			ArrayList<WebElement> listaMesiAnno = Generic.getElementListByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]/option");
			for (WebElement webElement : listaMesiAnno) {
				if(webElement.getText().equals(sito.getDatiCsv().getMeseAndata() + " " + sito.getDatiCsv().getAnno()) ) {
					webElement.click(); 
				}
			}
			
			// SCELTA GIORNO
			ArrayList<WebElement> listaGiorni = Generic.getElementListByXPath(driver, "/html/body/div[5]/div/div/div/table/tbody/tr[2]/td/table/tbody/tr/td/div");
			for (int i=0 ; i>listaGiorni.size(); i++) {
				if(!listaGiorni.get(i).getText().equals("1")) {
					listaGiorni.remove(i);
				} else {
					break;
				}
			}
			
			listaGiorni.get(Integer.valueOf(sito.getDatiCsv().getGiornoAndata())+1);
		}
	}
	
}
