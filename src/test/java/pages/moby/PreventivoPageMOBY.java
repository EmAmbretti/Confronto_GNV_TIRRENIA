package pages.moby;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageMOBY {

	public static void inserimentoDatiMoby(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		inserisciPasseggeriMoby(driver, esito, data);
		gestioneVeicoloMoby(driver, esito, data);
		selezionaSistemazione(driver, esito, data);
		cliccaContinua(driver);
	}

	private static void inserisciPasseggeriMoby(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null) {
			for (int i = 1; i < Integer.valueOf(data.getPasseggeriAdulti()); i++) {
				cliccaTastoPiuAdulti(driver);
				Thread.sleep(1000);
			}
			for (int i = 0; i < Integer.valueOf(data.getPasseggeriBambini()); i++) {
				cliccaTastoPiuBambini(driver);
				Thread.sleep(1000);
			}
			for (int i = 0; i < Integer.valueOf(data.getPasseggeriAnimali()); i++) {
				cliccaTastoPiuAnimali(driver);
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
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
			WebElement tastoPiu = driver.findElement(By.xpath("//div[@class='box-componente-quantita Animali']/div/div/button[2]"));
			tastoPiu.click();
			Thread.sleep(1000);
			WebElement tastoConferma = driver.findElement(By.xpath("/html/body/div[13]/div[7]/div/button"));
			if(tastoConferma.isDisplayed()) {
				tastoConferma.click();
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("L'elemento FRECCIA ANIMALI non è stato trovato!");
			System.exit(0);
		}
	}

	private static void gestioneVeicoloMoby(WebDriver driver, EsitoSito eesito, CSVData data) {
		if (eesito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//button[@id='customSelectMobyGuid20']");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					eesito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					eesito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "750");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "200");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					eesito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					eesito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}
	}

	private static void selezionaSistemazione(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null) {
			WebElement elementoSistemazione = driver.findElement(By.xpath("//div[@class='box-componente-quantita poltrona']/div/div/div"));
			int passeggeri = Integer.valueOf(data.getPasseggeriAdulti()) + Integer.valueOf(data.getPasseggeriBambini());
			System.out.println("Pax: " + passeggeri);
			System.out.println("SISTEMAZIONE: " + data.getSistemazione());
			if (data.getSistemazione().toUpperCase().contains("POLTRON")) {
				//				for (int i = 0; i < passeggeri; i++) {
				//					Generic.clickByList(driver, "//div[@class='wrapper-calcola-center']/button[@class='button right plus']", 15);
				//					Thread.sleep(2000);
				//				}
				while (!elementoSistemazione.getText().contains(passeggeri + "")) {
					System.out.println("CLICK: ------------------------");
					Generic.clickByList(driver, "//div[@class='wrapper-calcola-center']/button[@class='button right plus']", 15);
					Thread.sleep(2000);
				}
			} else {
				esito.setErrori(data.getSistemazione() + " non valida!");
			}
			Thread.sleep(2000);
		}
	}

	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(3000);
	}
}
