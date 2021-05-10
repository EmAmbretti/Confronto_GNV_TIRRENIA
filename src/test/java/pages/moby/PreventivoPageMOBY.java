package pages.moby;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageMOBY {

	public static void inserimentoDatiMoby(WebDriver driver, EsitoSito esito, CSVData data) {
		inserisciPasseggeriMOBY(driver, esito, data);
		gestioneVeicoloMOBY(driver, esito, data);
		selezionaSistemazioneMOBY(driver, esito, data);
		//cliccaContinuaMOBY(driver, esito);
	}

	private static void inserisciPasseggeriMOBY(WebDriver driver, EsitoSito esito, CSVData data) {
		if (esito.getErrori() == null) {
			System.out.println("Start method: inserisciPasseggeriMoby");
			try {
				for (int i = 1; i < Integer.valueOf(data.getPasseggeriAdulti()); i++) {
					cliccaTastoPiuAdultiMOBY(driver, esito);
					Thread.sleep(1000);
				}
				for (int i = 0; i < Integer.valueOf(data.getPasseggeriBambini()); i++) {
					cliccaTastoPiuBambiniMOBY(driver, esito);
					Thread.sleep(1000);
				}
				for (int i = 0; i < Integer.valueOf(data.getPasseggeriAnimali()); i++) {
					cliccaTastoPiuAnimali(driver, esito);
					Thread.sleep(1000);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				esito.setErrori("Numero di passeggeri non valido.");
				System.out.println("ERRORE: inserisciPasseggeriMOBY");
			}
		}
	}

	private static void cliccaTastoPiuAdultiMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaTastoPiuAdultiMOBY");
			try {
				driver.findElement(By.xpath("//*[@id=\"mobyGuid22\"]/div/div/button[2]")).click();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("L'elemento FRECCIA ADULTI non è stato trovato!");
			}
		}
	}

	private static void cliccaTastoPiuBambiniMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaTastoPiuBambiniMOBY");
			try {
				driver.findElement(By.xpath("//*[@id=\"mobyGuid23\"]/div/div/button[2]")).click();

			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("L'elemento FRECCIA BAMBINI non è stato trovato!");
			}
		}
	}

	private static void cliccaTastoPiuAnimali(WebDriver driver, EsitoSito esito) {	
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaTastoPiuAnimaliMOBY");
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
				esito.setErrori("GENERIC ERROR");
				System.out.println("L'elemento FRECCIA ANIMALI non è stato trovato!");
				System.exit(0);
			}
		}
	}

	private static void gestioneVeicoloMOBY(WebDriver driver, EsitoSito esito, CSVData data) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: gestioneVeicoloMOBY");
			try {
				if (!data.getVeicolo().equalsIgnoreCase("no")) {
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
								"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage Cat.9C')]");
						if (elements != null) {
							Generic.clickByXPath(driver,
									"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage Cat.9C')]");
							Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "500");
							Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "260");
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
			} catch (Exception e) {
				esito.setErrori("Veicolo non disponibile.");
				System.out.println("ERRORE: gestioneVeicoloMOBY");
			}
		}
	}

	private static void selezionaSistemazioneMOBY(WebDriver driver, EsitoSito esito, CSVData data) {
		System.out.println("Start method: selezionaSistemazioneMOBY");
		try {
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
								esito.setErrori("Cabina Quadrupla Esterna per Animali non disponibile.");
								System.out.println("Cabina Quadrupla Esterna per Animali non disponibile.");
							}
							Generic.scrollPage(driver, "1200");
							Thread.sleep(1000);
							Generic.clickByXPath(driver, "//div[@data-id='C4EG']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
							Thread.sleep(1000);
						} else {
							try {
								cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='C4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
							} catch (Exception e) {
								esito.setErrori("Cabina Quadrupla Esterna non disponibile.");
								System.out.println("Cabina Quadrupla Esterna non disponibile.");
							}
							Generic.scrollPage(driver, "700");
							Thread.sleep(1000);
							Generic.clickByXPath(driver, "//div[@data-id='C4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
							Thread.sleep(1000);
						}
					}
				} else {
					if(data.getSistemazione().toUpperCase().contains("CAB. INTERNA")){
						try {
							cabinaInterna = driver.findElement(By.xpath("//div[@data-id='C2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							esito.setErrori("Cabina Doppia Interna non disponibile.");
							System.out.println("Cabina Doppia Interna non disponibile.");
						}
						Generic.scrollPage(driver, "700");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					} else if (data.getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
						try {
							cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='C2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
						} catch (Exception e) {
							esito.setErrori("Cabina Doppia Esterna non disponibile.");
							System.out.println("Cabina Doppia Esterna non disponibile.");
						}
						Generic.scrollPage(driver, "700");
						Thread.sleep(1000);
						Generic.clickByXPath(driver, "//div[@data-id='C2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
						Thread.sleep(1000);
					}
				}
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			if (esito.getErrori() == null) {
				esito.setErrori("Sistemazione non disponibile.");
				System.out.println("ERRORE: selezionaSistemazioneMOBY");
			}
		}
	}

	private static void cliccaContinuaMOBY(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("Start method: cliccaContinuaMOBY");
			try {

				Generic.clickById(driver, "buttonNextPage");
				Thread.sleep(3000);	
			} catch (Exception e) {
				esito.setErrori("GENERIC ERROR");
				System.out.println("ERRORE: cliccaContinuaMOBY");
			}
		}
	}
}
