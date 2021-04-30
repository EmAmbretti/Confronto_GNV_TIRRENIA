package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageCF {
	
	public static void classeDiStart(WebDriver driver) {
		
	}
	
	private static void scegliTrattaEData(WebDriver driver) {
		
	}
	
	private static void scegliTratta(WebDriver driver, CSVData datiSito) {
		// CLICK PER SCELTA TRATTA
		Generic.clickByXPath(driver, "//*[@id=\"search-form-single\"]/div[1]/div");
		
		// RECUPERO LISTA VIAGGI 
		ArrayList<WebElement> listaViaggi = Generic.getElementListByXPath(driver, "/html/body/div[5]/ul/li");
		
		String[] tratta=datiSito.getTrattaAndata().split(" - ");
		String partenza = tratta[0];
		String arrivo = tratta[1];
		// SCELTA TRATTA
		for (WebElement webElement : listaViaggi) {
			System.out.println(webElement.getText());
			if(webElement.getText().contains(partenza) && webElement.getText().contains(arrivo)) {
				String trattaRecuperata = webElement.getText();
				String[] tratte = trattaRecuperata.split(partenza);
				if(tratte[1].contains("➔")) {
				webElement.click();
				}
			}
		}
		
		// CLICK PER SCELTA DATA
		Generic.clickByXPath(driver, "//*[@id=\"sod\"]");
		
		// CLICK PER SCELTA MESE
		Generic.clickByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]");
		
		// SCELTA MESE 
		ArrayList<WebElement> listaMesiAnno = Generic.getElementListByXPath(driver, "/html/body/div[7]/div/div/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/div/select[1]/option");
		for (WebElement webElement : listaMesiAnno) {
			if(webElement.getText().equals(datiSito.getMeseAndata() + " " + datiSito.getAnno()) ) {
				webElement.click(); 
			}
		}
	}
	
}
