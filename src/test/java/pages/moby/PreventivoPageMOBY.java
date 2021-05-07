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
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "750");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "200");
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

	private static void selezionaSistemazione(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null) {			
			int passeggeri = Integer.valueOf(data.getPasseggeriAdulti()) + Integer.valueOf(data.getPasseggeriBambini());
			WebElement sistemazionePoltrona = null, cabinaInterna = null, cabinaEsterna = null, cabinaInternaAnimali = null, cabinaEsternaAnimali = null;
			Thread.sleep(2000);
			if (data.getSistemazione().toUpperCase().contains("POLTRON")) {
				try {
					sistemazionePoltrona = driver.findElement(By.xpath("//div[@class='box-componente-quantita poltrona']/div/div"));
				} catch (Exception e) {
					e.printStackTrace();
					esito.setErrori("Poltrona non disponibile.");
				}
				Generic.scrollPage(driver, "1200");
				while (!sistemazionePoltrona.getText().contains(passeggeri + "")) {
					Generic.clickByXPath(driver, "//div[@class='box-componente-quantita poltrona']/div/div/button[@class='button right plus']");
					Thread.sleep(2000);
				}
			} else if (passeggeri > 2) {
				System.out.println("SIAM CAPIONI D'ITALIAAAA");
				if(data.getSistemazione().toUpperCase().contains("CAB. INTERNA")){
					if(Integer.valueOf(data.getPasseggeriAnimali()) != 0) {
						try {
							cabinaInternaAnimali = driver.findElement(By.xpath("//div[@data-id='C4G']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							esito.setErrori("Cabina Quadrupla Interna per Animali non disponibile.");
						}
						Generic.scrollPage(driver, "1200");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C4G']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					}else {
						try {
							cabinaInterna = driver.findElement(By.xpath("//div[@data-id='C4']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							e.printStackTrace();
							esito.setErrori("Cabina Quadrupla Interna non disponibile.");
						}
						Generic.scrollPage(driver, "700");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C4']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					}
				} else if (data.getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
					if(Integer.valueOf(data.getPasseggeriAnimali()) != 0) {
						try {
							cabinaEsternaAnimali = driver.findElement(By.xpath("//div[@data-id='C4EG']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							e.printStackTrace();
							esito.setErrori("Cabina Quadrupla Esterna per Animali non disponibile.");
						}
						Generic.scrollPage(driver, "1200");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C4EG']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					} else {
						try {
							cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='C4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							e.printStackTrace();
							esito.setErrori("Cabina Quadrupla Esterna non disponibile.");
						}
						Generic.scrollPage(driver, "700");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					}
				}
			} else {
				if(data.getSistemazione().toUpperCase().contains("CAB. INTERNA")){
					System.out.println("UNZ UNZ UNZ");

					try {
						cabinaInterna = driver.findElement(By.xpath("//div[@data-id='C2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
					} catch (Exception e) {
						e.printStackTrace();
						esito.setErrori("Cabina Doppia Interna non disponibile.");
					}
					Generic.scrollPage(driver, "700");
					Thread.sleep(1000);
					Generic.clickByXPath(driver, "//div[@data-id='C2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
					Thread.sleep(1000);
				} else if (data.getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
					System.out.println("E LE CICALEEEEEEEEEEEEE");

					try {
						cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='C2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
					} catch (Exception e) {
						e.printStackTrace();
						esito.setErrori("Cabina Doppia Esterna non disponibile.");
					}
					Generic.scrollPage(driver, "700");
					Thread.sleep(1000);
					Generic.clickByXPath(driver, "//div[@data-id='C2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
					Thread.sleep(1000);
				}
			}
		}
		Thread.sleep(3000);
	}

	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(3000);
	}
}
