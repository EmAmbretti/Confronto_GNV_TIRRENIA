package pages.moby;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageMOBY {

	public static void selezionaItinerarioMOBY(WebDriver driver, EsitoSito sito, CSVData data) throws Throwable {
		utenteApreBrowserMOBY(driver, sito);
		selezionaSoloAndataMOBY(driver);
		selezionaTrattaMOBY(driver, sito, data);
		selezionaDataMOBY(driver, sito);
		cliccaCerca(driver);
	}
	
	private static void utenteApreBrowserMOBY(WebDriver driver, EsitoSito sito) throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.moby.it/", sito.getSito());
	}

	private static void selezionaSoloAndataMOBY(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/label[2]/span[2]");
		Thread.sleep(2000);
	}

	private static void selezionaTrattaMOBY(WebDriver driver, EsitoSito sito, CSVData data) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/div[1]/div/div[1]/div/span");
		Thread.sleep(1000);			
		try {
			Generic.clickByXPath(driver, "/html/body/div/div/span/span/ul/li/span/ul/li/span[contains(.,'" + data.getTrattaAndata() + "')]");
		}catch (Exception e){
			sito.setErrori("la tratta per questo sito non è disponibile.");
			System.out.println("L'elemento TRATTA: " + data.getTrattaAndata() + " non è disponibile.");
		}
		Thread.sleep(2000);
	}

	private static void selezionaDataMOBY(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			Generic.clickById(driver, "//*[@id=\"dateFrom\"]");
			Thread.sleep(1000);
			selezionaMeseMOBY(driver, sito);			
		}
		if(sito.getErrori() == null) {
			selezionaGiornoMOBY(driver, sito);
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
	}

	private static void selezionaMeseMOBY(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori()==null) {
			do {
				try {
					WebElement element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[2]/text()"));
					if(element.getText().contains(sito.getDatiCsv().getMeseAndata())) {
						break;
					}else {
						cliccaFrecciaAvanti(driver);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("L'elemento MESE non è stato trovato!");
				}
			} while(true);
			Thread.sleep(2000);
		}
	}

	private static void selezionaGiornoMOBY(WebDriver driver, EsitoSito sito ) throws Throwable{
		if(sito.getErrori()==null) {
			boolean controllo = false;
			WebElement element = null;
			for(int i = 1; i <= 6; i ++) {
				for(int j = 1; j <= 7; j ++) {
					try {
						element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("L'elemento GIORNO non è stato trovato!");
					}
					if(element.getText().equals(sito.getDatiCsv().getGiornoAndata())) {
						try{
							element.click();
							controllo = true;
							break;

						} catch(Exception e) {
							e.printStackTrace();
							sito.setErrori("Il giorno inserito non è valido!");
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

	private static void cliccaCerca(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/input");
		Thread.sleep(2000);
	}

}
