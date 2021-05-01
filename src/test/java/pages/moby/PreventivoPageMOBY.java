package pages.moby;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageMOBY {

	public static void inserimentoDatiMoby(WebDriver driver, EsitoSito sito, CSVData data) throws Throwable {
		inserisciPasseggeriMoby(driver, sito, data);
		gestioneVeicoloMoby(driver, sito, data);
		selezionaSistemazione(driver, sito, data);
		cliccaContinua(driver);
	}

	private static void inserisciPasseggeriMoby(WebDriver driver, EsitoSito sito, CSVData data) throws Throwable {
		if (sito.getErrori() == null) {
			for (int i = 0; i < Integer.valueOf(data.getPasseggeriAdulti()); i++) {
				cliccaTastoPiuAdulti(driver);
			}
			for (int i = 0; i < Integer.valueOf(data.getPasseggeriBambini()); i++) {
				cliccaTastoPiuBambini(driver);
			}
			for (int i = 0; i < Integer.valueOf(data.getPasseggeriAnimali()); i++) {
				cliccaTastoPiuAnimali(driver);
			}
			Thread.sleep(3000);
		}
	}

	private static void cliccaTastoPiuAdulti(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//*[@id=\"mobyGuid22\"]/div/div/button[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L'elemento FRECCIA ADULTI non è stato trovato!");
		}
	}

	private static void cliccaTastoPiuBambini(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//*[@id=\"mobyGuid23\"]/div/div/button[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L'elemento FRECCIA BAMBINI non è stato trovato!");
		}
	}

	private static void cliccaTastoPiuAnimali(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//*[@id=\"mobyGuid26\"]/div/div/button[2]")).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L'elemento FRECCIA ANIMALI non è stato trovato!");
		}
	}

	private static void gestioneVeicoloMoby(WebDriver driver, EsitoSito esito, CSVData data) {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//button[@id='customSelectMobyGuid20']");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}

	}

	private static void selezionaSistemazione(WebDriver dirver, EsitoSito sito, CSVData data) throws Throwable {
		if (sito.getErrori() == null) {
			int passeggeri = Integer.valueOf(data.getPasseggeriAdulti()) + Integer.valueOf(data.getPasseggeriBambini());
			if (data.getSistemazione().contains("POLTRON")) {
				for (int i = 0; i < passeggeri; i++) {
					Generic.clickByXPath(dirver, "//*[@id=\"mobyGuid37\"]/div/div/button[2]");
				}
			} else {
				sito.setErrori(data.getSistemazione() + " non valida!");
			}
			Thread.sleep(2000);
		}
	}

	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}

}
