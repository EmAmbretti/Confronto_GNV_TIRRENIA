package pages.moby;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageMOBY {

	public static void selezionaItinerarioMOBY(WebDriver driver, EsitoSito esito, CSVData data) {
		utenteApreBrowserMOBY(driver, esito);
		selezionaSoloAndataMOBY(driver, esito);
		selezionaTrattaMOBY(driver, esito, data);
		selezionaDataMOBY(driver, esito);
		cliccaCercaMOBY(driver, esito);
	}

	private static void utenteApreBrowserMOBY(WebDriver driver, EsitoSito esito) {
		System.out.println("Start method: utenteApreBrowserMOBY");
		try {
			Generic.utente_apre_browser(driver, "https://www.moby.it/", esito.getSito(), esito);
		} catch (Exception e) {
			esito.setErrori("Errore server");
			System.out.println("ERRORE utenteApreBrowser");
		}

	}

	private static void selezionaSoloAndataMOBY(WebDriver driver, EsitoSito esito) {
		System.out.println("Start method: selezionaSoloAndataMOBY");
		try {
			if(esito.getErrori() == null) {
				Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/label[2]/span[2]");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			esito.setErrori("GENERIC ERROR");
			System.out.println("Errore selezionaAndata");
		}


	}

	private static void selezionaTrattaMOBY(WebDriver driver, EsitoSito esito, CSVData data) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: selezionaTrattaMOBY");
			WebElement trattaDaTrovare = null;
			Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/div[1]/div/div[1]/div/span[@class='jcf-select jcf-unselectable jcf-select-select-picker']");
			try{
				Thread.sleep(2000);
			} catch (Exception e) {
				System.out.println("ATTESA?");
			}
			System.out.println("PROVO A TROVARE ELEMENTO");
			JavascriptExecutor je = (JavascriptExecutor) driver;
			try {
				System.out.println("STO NEL TRY 81");
				trattaDaTrovare = driver.findElement(By.xpath("/html/body/div/div/span/span/ul/li/span/ul/li/span[contains(.,'" + data.getTrattaAndata() + "')]"));
				je.executeScript("arguments[0].scrollIntoView(true);", trattaDaTrovare);
				trattaDaTrovare.click();
				Thread.sleep(5000);
			}catch (Exception e){
				esito.setErrori("la tratta per questo sito non è disponibile.");
				System.out.println("L'elemento TRATTA: " + data.getTrattaAndata() + " non è disponibile.");
			}		
		}
	}

	private static void selezionaDataMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: selezionaDataMOBY");
			try {		
				Generic.clickById(driver, "dateFrom");
				Thread.sleep(1000);
				selezionaMeseMOBY(driver, esito);			
			} catch (Exception e) {
				esito.setErrori("Mese non disponibile.");
				System.out.println("ERRORE: selezionaMeseMOBY");
			}
		}
		if(esito.getErrori() == null) {
			try {
				selezionaGiornoMOBY(driver, esito);
				Thread.sleep(1000);

			} catch (Exception e) {
				esito.setErrori("Giorno non disponibile.");
				System.out.println("ERRORE: selezionaGiornoMOBY");
			}
		}
	}

	private static void selezionaMeseMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			System.out.println("Start method: selezionaMeseMOBY");
			do {
				try {
					WebElement element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[2]"));
					if(element.getText().contains(esito.getDatiCsv().getMeseAndata().toUpperCase())) {
						System.out.println(element.getText());
						break;
					}else {
						cliccaFrecciaAvantiMOBY(driver, esito);
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

	private static void selezionaGiornoMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			System.out.println("Start method: selezionaGiornoMOBY");
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

	private static void cliccaFrecciaAvantiMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaFrecciaAvantiMOBY");
			try {
				driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[3]/span")).click();
			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("L'elemento FRECCIA AVANTI non è stato trovato!");
			}
		}
	}

	private static void cliccaCercaMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaCercaMOBY");
			try {
				Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/input");
				Thread.sleep(7000);
			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("ERRORE: cliccaCercaMOBY");
			}
		}
	}

}
