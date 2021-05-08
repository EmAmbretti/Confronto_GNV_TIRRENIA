package pages.tirrenia;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageTIRRENIA {
	public static void inserisciDati(WebDriver driver, EsitoSito sito) throws Throwable {
		utenteApreBrowserTIRRENIA(driver, sito);
		cliccaTratte(driver);
		controlloTratta(driver, sito);
		cliccaCalendario(driver, sito);
		selezionaData(driver, sito);
		inserisciPasseggeri(driver, sito);
		cliccaSuCerca(driver, sito);
	}
	
	private static void utenteApreBrowserTIRRENIA(WebDriver driver, EsitoSito esito) throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.tirrenia.it/", esito.getSito(), esito);
	}
	
	private static void inserisciPasseggeri(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null) {
			inserisciAdulti(driver, sito);
		}
		if(sito.getErrori()==null) {
			inserisciBambini(driver, sito);
		}
		if(sito.getErrori()==null) {
			inserisciAnimali(driver, sito);
		}
		if(sito.getErrori()==null) {
			Generic.clickByXPath(driver, "//div[@class='tpd-content-relative']//div[@class='tpd-close-icon']");
			Thread.sleep(500);
		}
	}
	
	private static void inserisciAdulti(WebDriver driver, EsitoSito sito)throws Throwable {
		Generic.clickById(driver, "homePassAnd");
		Thread.sleep(1000);
		WebElement paxAdulti= driver.findElement(By.xpath("//div[@class='center cabine text']"));
		while(!paxAdulti.getText().contains(sito.getDatiCsv().getPasseggeriAdulti())) {
			System.out.println("paxAdulti: "+paxAdulti.getText());
			System.out.println("datiCSV: "+sito.getDatiCsv().getPasseggeriAdulti());
			try {
			Generic.clickByXPath(driver, "//div[@class='box-componente-quantita adulto']//button[@class='button right plus']");
			Thread.sleep(500);	
			}catch(Exception e) {
				sito.setErrori("Errore durante l'inserimento dei passeggeri adulti");
			}
			
		}
		
	}
	private static void inserisciBambini(WebDriver driver, EsitoSito sito)throws Throwable{
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='center cabine']"));
		WebElement paxBambini = elements.get(0);
		while(!paxBambini.getText().equals(sito.getDatiCsv().getPasseggeriBambini())) {
			try {
			Generic.clickByXPath(driver, "//*[@class='box-componente-quantita bambino']//button[@class='button right plus']");
			Thread.sleep(500);	
			}catch(Exception e) {
				sito.setErrori("Errore durante l'inserimento dei passeggeri bambini");
			}
			
		}
	}
	private static void inserisciAnimali(WebDriver driver, EsitoSito sito)throws Throwable{
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='center cabine']"));
		WebElement paxAnimali = elements.get(elements.size()-1);
		while(!paxAnimali.getText().equals(sito.getDatiCsv().getPasseggeriAnimali())) {
			try {
				Generic.clickByXPath(driver, "//*[@class='box-componente-quantita animale']/div/div/button[@class='button right plus']");
				Thread.sleep(1000);
				Generic.clickByXPath(driver, "//button[@class='confirm']");
				Thread.sleep(500);
			}catch(Exception e) {
				sito.setErrori("Errore durante l'inserimento dei passeggeri animali");
			}	
		}
	}
	
	private static void cliccaTratte(WebDriver driver) throws Throwable{
		Generic.clickById(driver, "customSelectCustomGuid1");
		Thread.sleep(1000);
	}
	private static void controlloTratta(WebDriver driver, EsitoSito sito)throws Throwable {
		boolean flag=false;
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"lineeAndataContent\"]/div/ul/li/a"));
		for(WebElement element:elements) {
			if(element.getText().equalsIgnoreCase(sito.getDatiCsv().getTrattaAndata())) {
				element.click();
				Thread.sleep(1000);
				flag=true;
				break;
			}
		}
		if(!flag) {
			sito.setErrori("Tratta non disonibile per questo sito!");
		}
	}
	private static void cliccaCalendario(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori()==null) {
			Generic.clickById(driver, "dateFrom");
			Thread.sleep(1000);
		}
	}
	private static void selezionaData(WebDriver driver, EsitoSito sito)throws Throwable{
		if(!sito.getDatiCsv().getAnno().equals("2021")) {
			System.out.println("Errore anno");
			sito.setErrori("Data non disponibile! (Anno)");
		}
		if(sito.getErrori()==null) {
			selezionaMese(driver, sito);
		}
		if(sito.getErrori()==null) {
			selezionaGiorno(driver, sito);
		}	
	}
	
	private static void selezionaMese(WebDriver driver, EsitoSito sito)throws Throwable{
		boolean flag=true;
			WebElement mese = driver.findElement(By.xpath("//th[@title='Select Month']"));
			while(flag) {
				if(!mese.getText().contains(sito.getDatiCsv().getMeseAndata().toUpperCase())) {
					Generic.clickByXPath(driver, "//th[@class='next']");
					Thread.sleep(1000);
					if(driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@data-action='next']")).getAttribute("class").contains("disabled")&&mese.getText().contains("DICEMBRE")&&!sito.getDatiCsv().getMeseAndata().toUpperCase().contains("DICEMBRE")) {
						sito.setErrori("Mese non valido!");
						System.out.println("Mese non valido!");
						flag=false;
					}
				}else {
					flag=false;
				}
			}
	}
	
	private static void selezionaGiorno(WebDriver driver, EsitoSito sito) throws Throwable{
		boolean controllo = false;
		for(int i = 1; i <= 6; i ++) {
			for(int j = 1; j <= 7; j ++) {
				WebElement element=driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[" + j + "]"));
				if(element.getText().equals(sito.getDatiCsv().getGiornoAndata())&&(Integer.valueOf(sito.getDatiCsv().getGiornoAndata())<20||i>1)) {
					Thread.sleep(2000);
					try {
						controllo=true;
						element.click();
					}catch(Exception e) {
						sito.setErrori("Il giorno inserito non è disponibile per questo sito.");
					}
					break;	
				}
			}
			if(controllo) {
				break;
			}
		}
		if(!controllo) {
			sito.setErrori("Giorno non disponibile! (Oppure giorno non esistente)");
		}
		Thread.sleep(2000);
	}
	private static void cliccaSuCerca(WebDriver driver, EsitoSito sito) throws Throwable {
		Generic.clickByXPath(driver, "//button[@type='submit'][@class='button']");
		Thread.sleep(2000);
	}
}
