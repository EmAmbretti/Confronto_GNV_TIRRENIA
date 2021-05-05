package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageMOBY {

	public static void selezionaItinerarioMOBY(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		utenteApreBrowserMOBY(driver, esito);
		selezionaSoloAndataMOBY(driver);
		selezionaTrattaMOBY(driver, esito, data);
		selezionaDataMOBY(driver, esito);
		cliccaCerca(driver, esito);
	}

	private static void utenteApreBrowserMOBY(WebDriver driver, EsitoSito esito) throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.moby.it/", esito.getSito());
	}

	private static void selezionaSoloAndataMOBY(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/label[2]/span[2]");
		Thread.sleep(2000);
	}

	private static void selezionaTrattaMOBY(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/div[1]/div/div[1]/div/span");
		Thread.sleep(1000);			
		try {
			Generic.clickByXPath(driver, "/html/body/div/div/span/span/ul/li/span/ul/li/span[contains(.,'" + data.getTrattaAndata() + "')]");
		}catch (Exception e){
			esito.setErrori("la tratta per questo sito non è disponibile.");
			System.out.println("L'elemento TRATTA: " + data.getTrattaAndata() + " non è disponibile.");
		}
		Thread.sleep(5000);
	}

	private static void selezionaDataMOBY(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickById(driver, "dateFrom");
			Thread.sleep(1000);
			selezionaMeseMOBY(driver, esito);			
		}
		if(esito.getErrori() == null) {
			selezionaGiornoMOBY(driver, esito);
			Thread.sleep(1000);
		}
	}

	private static void selezionaMeseMOBY(WebDriver driver, EsitoSito esito) throws Throwable{
		if(esito.getErrori()==null) {
			do {
				try {
					WebElement element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[2]"));
					if(element.getText().contains(esito.getDatiCsv().getMeseAndata().toUpperCase())) {
						System.out.println(element.getText());
						break;
					}else {
						cliccaFrecciaAvanti(driver);
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("L'elemento MESE non è stato trovato!");
					esito.setErrori("Il mese selezionato non è disponibile per questo sito.");
				}
			} while(true);
		}
	}

	private static void selezionaGiornoMOBY(WebDriver driver, EsitoSito esito) throws Throwable{
		if(esito.getErrori()==null) {
			boolean controllo = false;
			WebElement element = null;
			for(int i = 1; i <= 6; i ++) {
				for(int j = 1; j <= 7; j ++) {
					try {
						element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("L'elemento GIORNO non è stato trovato!");
						esito.setErrori("Il giorno selezionato non è disponibile per questo sito.");
					}
					if(element.getText().equals(esito.getDatiCsv().getGiornoAndata())) {
						try{
							element.click();
							controllo = true;
							break;
						} catch(Exception e) {
							e.printStackTrace();
							System.out.println("L'elemento GIORNO non è stato trovato!");
							esito.setErrori("Il giorno selezionato non è disponibile per questo sito.");
							break;
						}				
					}
				}
				if(controllo) {
					break;
				}
			}
		}
	}

	private static void cliccaFrecciaAvanti(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[3]/span")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L'elemento FRECCIA AVANTI non è stato trovato!");
		}
	}

	private static void cliccaCerca(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/input");
			Thread.sleep(7000);
		}
	}

}
