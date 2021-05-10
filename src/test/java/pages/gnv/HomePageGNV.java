package pages.gnv;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class HomePageGNV {

	public static void selezionaItinerarioGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			utenteApreBrowserGNV(driver, esito);
			closePopupGNV(driver, esito);
			selezionaViaggioGNV(driver, esito);
			closePopupGNV(driver, esito);
			scegliTrattaGNV(driver, esito);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closePopupGNV(driver, esito);
			// closePopupGNV(driver, esito);
			controlloMeseGNV(driver, esito);
			closePopupGNV(driver, esito);
			// cliccaDataSceltaGNV(driver, esito);
			cliccaDataSceltaGNV_2(driver, esito);
			// closePopupGNV(driver, esito);
			inserisciPasseggeriGNV(driver, esito);
			gestioneVeicoloGNV(driver, esito);
			cliccaCercaGNV(driver, esito);
		}
	}

	private static void utenteApreBrowserGNV(WebDriver driver, EsitoSito sito) {
		if (sito.getErrori() == null)
			Generic.utente_apre_browser(driver, "https://www.gnv.it/it", sito.getSito(), sito);
	}

	private static void scegliTrattaGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			cliccaSoloAndataGNV(driver, esito);
			selezionaTrattaGNV(driver, esito);
			cliccaContinuaGNV(driver, esito);
		}
	}

	private static void inserisciPasseggeriGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			cliccaAdutiGNV(driver, esito);
			cliccaBambiniGNV(driver, esito);
			cliccaAnimaliGNV(driver, esito);
		}
	}

	private static void closePopupGNV(WebDriver driver, EsitoSito sito) {
		Generic.waitSeconds(5);
		if (sito.getErrori() == null) {
			try {
				driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]"))
						.click();
				Generic.waitSeconds(1);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				// e.printStackTrace();
				System.out.println("POPUP NON TROVATO");
			}
			try {
				driver.findElement(By.xpath("//*[@id=\"closeXButton\"]/span/p/span")).click();
				Generic.waitSeconds(1);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("POPUP NON TROVATO");
			}
		}
	}

	private static void selezionaViaggioGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				driver.findElement(By.xpath(
						"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span"))
						.click();
				Generic.waitSeconds(1);
			} catch (Exception e) {
				esito.setErrori("viaggio non selezionato");
			}
		}
	}

	private static void selezionaTrattaGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			String[] tratte = esito.getDatiCsv().getTrattaAndata().split(" - ");
			String partenza = tratte[0];
			String arrivo = tratte[1];
			try {
				List<WebElement> elementList = driver
						.findElements(By.xpath("//label/span[contains(text(),'" + partenza + "')]"));
				elementList.get(0).click();
				Generic.waitSeconds(1);
				elementList = driver.findElements(By.xpath("//label/span[contains(text(),'" + arrivo + "')]"));
				elementList.get(elementList.size() - 1).click();
				Generic.waitSeconds(1);
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("la tratta per questo sito non è disponibile.");
			}
		}
	}

	private static void cliccaSoloAndataGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				driver.findElement(By.xpath(
						"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[1]/app-booking-wizard-step1/div/div/app-travel-viewer-map/div/div[1]/div[1]/label"))
						.click();
				Generic.waitSeconds(1);
			} catch (Exception e) {
				esito.setErrori("Click solo andata non riuscito");
				e.printStackTrace();
			}
		}
	}

	private static void cliccaContinuaGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				Generic.clickByXPath(driver, "//button[@class='btn btn-lg gnv-btn widget-button next']");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Click continua non riuscito");
			}
		}
	}

	private static void cliccaFrecciaAvantiGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				driver.findElement(By.xpath("//i[@class='gnv-fe-icon-arrow-light-right']")).click();
			} catch (Exception e) {
				esito.setErrori("Freccia avanti non cliccata");
			}
		}
	}

	private static void controlloMeseGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			boolean flag = false;
			do {
				Generic.waitSeconds(1);
				WebElement element = driver.findElement(By.xpath(
						"//*[@id='main-container']/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[1]/div/div[1]/span"));
				if (element.getText().toUpperCase().contains(esito.getDatiCsv().getMeseAndata())) {
					flag = true;
					break;
				} else {
					cliccaFrecciaAvantiGNV(driver, esito);
				}
			} while (flag);
			Generic.waitSeconds(1);
		}
	}

	private static void cliccaDataSceltaGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			boolean flag = false;
			for (int j = 1; j <= 5; j++) {
				for (int i = 1; i <= 7; i++) {
					/*
					 * WebElement element = driver.findElement(By.xpath(
					 * "//*[@id='main-container']/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div["
					 * + j + "]/div[" + i + "]/div"));
					 */
					WebElement element = driver.findElement(By.xpath(
							"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[1]/div/div[2]/div[2]/div["
									+ j + "]/div[" + i + "]/div"));
					if (element.getText().equals(esito.getDatiCsv().getGiornoAndata()) && esito.getErrori() == null) {
						Generic.waitSeconds(1);
						try {
							element.click();
							flag = true;
							break;
						} catch (Exception e) {
							esito.setErrori("Il giorno scelto non è disponibile per questo sito.");
							System.out.println("Il giorno scelto non è disponibile per questo sito.");
							break;
						}
					}
				}
				if (!flag) {
					break;
				}
			}
			cliccaContinuaGNV(driver, esito);
			Generic.waitSeconds(1);
		}
	}

	private static void cliccaDataSceltaGNV_2(WebDriver driver, EsitoSito esito) {
		// //*[@id="main-container"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[1]/div/div[2]/div[2]/div/div/div
		if (esito.getErrori() == null) {
			System.out.println("\nScelta Giorno...");
			// SCELTA GIORNO
			ArrayList<WebElement> calendario = Generic.getElementListByXPath(driver,
					"//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div");
			//// *[@id="main-container"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[1]/div/div[2]/div[2]/div[1]/div[1]/div
			ArrayList<WebElement> listaGiorni = null;
			for (WebElement webElement : calendario) {
				if (Generic.getChildElementByXPath(driver, webElement, "/div/div[@class='monthName']/span").getText()
						.toUpperCase().contains(esito.getDatiCsv().getMeseAndata())) {

					listaGiorni = Generic.getChildElementsByXPath(driver, webElement, "/div/div/div/div/div/div[@class='date-text']");
					if (listaGiorni != null && !listaGiorni.isEmpty()) {
						for (int i = 0; i < listaGiorni.size(); i++) {
							if (!listaGiorni.get(i).getText().equals("1")) {
								listaGiorni.remove(i);
								i--;
							} else {
								break;
							}
						}
						break;
					} else {
						esito.setErrori("ERRORE CF: ERRORE NELLA SCELTA DATA");
					}
				}
			}
			if (esito.getErrori() == null) {
				try {
					listaGiorni.get((Integer.valueOf(esito.getDatiCsv().getGiornoAndata()) - 1)).click();
					cliccaContinuaGNV(driver, esito);
				} catch (Exception e) {
					System.out.println("\nERRORE SCELTA GIORNO");
					esito.setErrori("ERRORE SCELTA GIORNO");
				}
			}
			
		}
		Generic.waitSeconds(1);
	}


	private static void cliccaAdutiGNV(WebDriver driver, EsitoSito sito) {
		if (sito.getErrori() == null) {
			if (Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()) != 0) {
				for (int i = 0; i < Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()); i++) {
					System.out.println("ADULTI:::" + Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()));
					Generic.waitSeconds(1);
					try {
						Generic.clickByXPath(driver,
								"//app-counter-wrapper[1]//button[@class='input-number-increment']");
					} catch (Exception e) {
						sito.setErrori("Click adulti non riuscito");
					}
				}
			}
		}
	}

	private static void cliccaBambiniGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			if (Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()) != 0) {
				for (int i = 0; i < Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()); i++) {
					System.out.println("BAMBINI:::" + Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini()));
					Generic.waitSeconds(1);
					try {
						Generic.clickByXPath(driver,
								"//app-counter-wrapper[2]//button[@class='input-number-increment']");
					} catch (Exception e) {
						esito.setErrori("Click bambini non riuscito");
					}
				}
			}
		}
	}

	private static void cliccaAnimaliGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			if (Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()) != 0) {
				for (int i = 0; i < Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()); i++) {
					System.out.println("ANIMALI::::" + Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali()));
					Generic.waitSeconds(1);
					Generic.clickByXPath(driver, "//app-counter-wrapper[4]//button[@class='input-number-increment']");
				}
			}
		}
	}

	private static void cliccaCercaGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				Generic.clickByXPath(driver, "//div[@class='widget-summary-next active'][contains(.,'Cerca')]");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Click cerca non riuscito");
			}
		}
	}

	private static void gestioneVeicoloGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null && !esito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			Generic.waitSeconds(1);
			Generic.clickByXPath(driver,
					"//div[@class='journey-summary-block search-active last']/i[@class='journey-summary-block--arrow-icon gnv-fe-icon-arrow-light-right']");
			if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				Generic.waitSeconds(1);
				List<WebElement> elements = driver.findElements(
						By.xpath("//div[@class='counter-wrapper orange']//button[@class='input-number-increment']"));
				if (elements != null && elements.size()>0) {
					System.out.println("lista::::" + elements.size());
					System.out.println("ECCOMI");
					elements.get(0).click();
					Generic.waitSeconds(1);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver, "//option[contains(.,'Inferiore a 1,9m')][@class='ng-star-inserted']");
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
					Generic.clickByXPath(driver, "//option[contains(.,'Inferiore a  2,9m')]");
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
