package pages.gnv;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class HomePageGNV {

	public static void selezionaItinerarioGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
		utenteApreBrowserGNV(driver, esito);
		closePopupGNV(driver, esito);
		selezionaViaggioGNV(driver, esito);
		scegliTrattaGNV(driver,esito);
		controlloMeseGNV(driver, esito);
		cliccaDataSceltaGNV(driver, esito);
		inserisciPasseggeriGNV(driver, esito);
		gestioneVeicoloGNV(driver, esito);
		cliccaCercaGNV(driver, esito);
		}
	}

	private static void utenteApreBrowserGNV(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null)
			Generic.utente_apre_browser(driver, "https://www.gnv.it/it", sito.getSito());
	}

	private static void scegliTrattaGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			cliccaSoloAndataGNV(driver, esito);
			selezionaTrattaGNV(driver, esito);
			cliccaContinuaGNV(driver, esito);
		}
	}

	private static void inserisciPasseggeriGNV(WebDriver driver, EsitoSito esito) throws InterruptedException {
		if(esito.getErrori()==null) {
			cliccaAdutiGNV(driver, esito);
			cliccaBambiniGNV(driver, esito);
			cliccaAnimaliGNV(driver, esito);
			cliccaContinuaGNV(driver, esito);
		}
	}

	private static void closePopupGNV(WebDriver driver, EsitoSito sito) throws InterruptedException {
		Thread.sleep(6000);
		if(sito.getErrori()==null) {
			try {
				driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).click();
				Thread.sleep(5000);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				e.printStackTrace();
			}
			try {
				driver.findElement(By.xpath("//*[@id=\"closeXButton\"]/span/p/span")).click();
				Thread.sleep(5000);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("AMBIENTE NON TROVATO");
			}
		}
	}

	private static void selezionaViaggioGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			try {
				driver.findElement(By.xpath(
						"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span"))
				.click();
				Thread.sleep(3000);
			}catch (Exception e) {
				esito.setErrori("viaggio non selezionato");
			}
		}
	}

	private static void selezionaTrattaGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			String[] tratte = esito.getDatiCsv().getTrattaAndata().split(" - ");
			String partenza = tratte[0];
			String arrivo = tratte[1];
			try {
				List<WebElement> elementList = driver
						.findElements(By.xpath("//label/span[contains(text(),'" + partenza + "')]"));
				elementList.get(0).click();
				Thread.sleep(1000);
				elementList = driver.findElements(By.xpath("//label/span[contains(text(),'" + arrivo + "')]"));
				elementList.get(elementList.size() - 1).click();
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("la tratta per questo sito non è disponibile.");
			}
		}
	}

	private static void cliccaSoloAndataGNV(WebDriver driver, EsitoSito esito)  {
		if(esito.getErrori()==null) {
			try {
				driver.findElement(By.xpath(
						"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[1]/app-booking-wizard-step1/div/div/app-travel-viewer-map/div/div[1]/div[1]/label"))
				.click();
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				esito.setErrori("Click solo andata non riuscito");
				e.printStackTrace();
			}
		}
	}

	private static void cliccaContinuaGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			try {
				Generic.clickByXPath(driver, "//button[@class='btn btn-lg gnv-btn widget-button next']");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Click continua non riuscito");
			}
		}
	}

	private static void cliccaFrecciaAvantiGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			try {
				driver.findElement(By.xpath("//i[@class='gnv-fe-icon-arrow-light-right']")).click();
			}catch (Exception e) {
				esito.setErrori("Freccia avanti non cliccata");
			}
		}
	}

	private static void controlloMeseGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			do {
				Thread.sleep(2000);
				WebElement element = driver.findElement(By.xpath(
						"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[1]/span"));
				if (element.getText().contains(esito.getDatiCsv().getMeseAndata())) {
					break;
				} else {
					cliccaFrecciaAvantiGNV(driver, esito);
				}
			} while (true);
			Thread.sleep(1000);
		}
	}

	private static void cliccaDataSceltaGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			for (int j = 1; j <= 5; j++) {
				for (int i = 1; i <= 7; i++) {
					WebElement element = driver.findElement(By.xpath(
							"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div["
									+ j + "]/div[" + i + "]/div"));
					if (element.getText().equals(esito.getDatiCsv().getGiornoAndata())) {
						Thread.sleep(2000);
						try {
							element.click();
							break;
						} catch (Exception e) {
							esito.setErrori("Il giorno scelto non è disponibile per questo sito.");
						}
					}
				}
			}
			cliccaContinuaGNV(driver, esito);
			Thread.sleep(1000);
		}
	}

	private static void cliccaAdutiGNV(WebDriver driver, EsitoSito sito) throws InterruptedException {
		if(sito.getErrori()==null) {
			if (Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()) != 0) {
				for (int i = 0; i < Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()); i++) {
					System.out.println("ADULTI:::" + Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()));
					Thread.sleep(700);
					try {
						Generic.clickByXPath(driver, "//app-counter-wrapper[1]//button[@class='input-number-increment']");
					}catch (Exception e) {
						sito.setErrori("Click adulti non riuscito");
					}
				}
			}
		}
	}

	private static void cliccaBambiniGNV(WebDriver driver, EsitoSito esito) throws InterruptedException {
		if(esito.getErrori()==null) {
			if (Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()) != 0) {
				for (int i = 0; i <  Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()); i++) {
					System.out.println("BAMBINI:::" + Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()));
					Thread.sleep(700);
					try {
						Generic.clickByXPath(driver, "//app-counter-wrapper[2]//button[@class='input-number-increment']");
					}catch (Exception e) {
						esito.setErrori("Click bambini non riuscito");
					}
				}
			}
		}
	}

	private static void cliccaAnimaliGNV(WebDriver driver, EsitoSito esito) throws InterruptedException {
		if(esito.getErrori()==null) {
			if (Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()) != 0) {
				for (int i = 0; i < Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()); i++) {
					System.out.println("ANIMALI::::" + Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()));
					Thread.sleep(700);
					Generic.clickByXPath(driver, "//app-counter-wrapper[4]//button[@class='input-number-increment']");
				}
			}
		}
	}

	private static void cliccaCercaGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			try {
				Generic.clickByXPath(driver, "//div[@class='widget-summary-next active'][contains(.,'Cerca')]");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Click cerca non riuscito");
			}
		}
	}

	private static void gestioneVeicoloGNV(WebDriver driver, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null && !esito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			Thread.sleep(1000);
			Generic.clickByXPath(driver, "//div[@class='journey-summary-block no-wizard-active search-active last']");
			if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				Thread.sleep(500);
				List<WebElement> elements = driver.findElements(By.xpath("//div[@class='counter-wrapper orange']//button[@class='input-number-increment']"));
				if (elements != null) {
					System.out.println("lista::::" +  elements.size());
					System.out.println("ECCOMI");
					elements.get(0).click();
					Thread.sleep(1000);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//option[contains(.,'Inferiore a 1,9m')][@class='ng-star-inserted']");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByList(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 1);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//option[contains(.,'Inferiore a  2,9m')][@class='ng-star-inserted']");
					Generic.clickByXPath(driver, "//select[@id='length']");
					Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 5m  e 6m')]");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByList(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 2);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//option[contains(.,'Inferiore a  2,9m')]");
					Generic.clickByXPath(driver, "//select[@id='length']");
					Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 7m  e 8m')]");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByList(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 4);
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}
	}

}
