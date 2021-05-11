package pages.tirrenia;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageTIRRENIA {
	public static void inserimentoSistemazione(WebDriver driver, EsitoSito sito) throws Throwable {

		if(sito.getErrori()==null) {
			gestioneVeicolo(driver, sito);
		}
		if(sito.getErrori()==null) {
			selezionaSistemazione(driver, sito);
		}
		if(sito.getErrori()==null) {
			cliccaContinua(driver);
		}
	}

	//	private static void selezionaSistemazione(WebDriver driver, EsitoSito sito) throws Throwable {	
	//		int passeggeri = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()) + Integer.valueOf(sito.getDatiCsv().getPasseggeriBambini());
	//		WebElement sistemazionePoltrona = null, cabinaInterna = null, cabinaEsterna = null, cabinaInternaAnimali = null, cabinaEsternaAnimali = null;
	//		Thread.sleep(2000);
	//		if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("POLTRON")) {
	//			try {
	//				sistemazionePoltrona = driver.findElement(By.xpath("//div[@class='box-componente-quantita poltrona']/div/div"));
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//				sito.setErrori("Poltrona non disponibile.");
	//			}
	//			Generic.scrollPage(driver, "1900");
	//			while (!sistemazionePoltrona.getText().contains(passeggeri + "")) {
	//				System.out.println("Passeggeri= "+passeggeri);
	//				Generic.clickByXPath(driver, "//div[@class='box-componente-quantita poltrona']/div/div/button[@class='button right plus']");
	//				Thread.sleep(2000);
	//			}
	//		} else if (passeggeri > 2) {
	//			if(sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. INTERNA")){
	//				if(Integer.valueOf(sito.getDatiCsv().getPasseggeriAnimali()) != 0) {
	//					try {
	//						cabinaInternaAnimali = driver.findElement(By.xpath("//div[@data-id='KC4G']//div[@class='box-componente-quantita cabina']/div/div"));
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//						sito.setErrori("Cabina Quadrupla Interna per Animali non disponibile.");
	//					}
	//					Generic.scrollPage(driver, "1200");
	//					Thread.sleep(1000);
	//					Generic.clickByXPath(driver, "//div[@data-id='KC4G']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//					Thread.sleep(1000);
	//				}else {
	//					try {
	//						cabinaInterna = driver.findElement(By.xpath("//div[@data-id='KC4']//div[@class='box-componente-quantita cabina']/div/div"));
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//						sito.setErrori("Cabina Quadrupla Interna non disponibile.");
	//					}
	//					Generic.scrollPage(driver, "700");
	//					Thread.sleep(1000);
	//					Generic.clickByXPath(driver, "//div[@data-id='KC4']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//					Thread.sleep(1000);
	//				}
	//			} else if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
	//				if(Integer.valueOf(sito.getDatiCsv().getPasseggeriAnimali()) != 0) {
	//					try {
	//						cabinaEsternaAnimali = driver.findElement(By.xpath("//div[@data-id='KC4EG']//div[@class='box-componente-quantita cabina']/div/div"));
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//						sito.setErrori("Cabina Quadrupla Esterna per Animali non disponibile.");
	//					}
	//					Generic.scrollPage(driver, "1200");
	//					Thread.sleep(1000);
	//					Generic.clickByXPath(driver, "//div[@data-id='KC4EG']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//					Thread.sleep(1000);
	//				} else {
	//					try {
	//						cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='KC4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//						sito.setErrori("Cabina Quadrupla Esterna non disponibile.");
	//					}
	//					Generic.scrollPage(driver, "700");
	//					Thread.sleep(1000);
	//					Generic.clickByXPath(driver, "//div[@data-id='KC4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//					Thread.sleep(1000);
	//				}
	//			}
	//		} else {
	//			if(sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. INTERNA")){
	//				try {
	//					cabinaInterna = driver.findElement(By.xpath("//div[@data-id='KC2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//					sito.setErrori("Cabina Doppia Interna non disponibile.");
	//				}
	//				Generic.scrollPage(driver, "700");
	//				Thread.sleep(1000);
	//				Generic.clickByXPath(driver, "//div[@data-id='KC2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//				Thread.sleep(1000);
	//			} else if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
	//				try {
	//					cabinaEsterna = driver.findElement(By.xpath("//div[@data-id='KC2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//					sito.setErrori("Cabina Doppia Esterna non disponibile.");
	//				}
	//				Generic.scrollPage(driver, "700");
	//				Thread.sleep(1000);
	//				Generic.clickByXPath(driver, "//div[@data-id='KC2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']");
	//				Thread.sleep(1000);
	//			}
	//		}
	//		Thread.sleep(3000);
	//	}




	private static void selezionaSistemazione(WebDriver driver, EsitoSito sito) throws Throwable {	
		int passeggeri = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()) + Integer.valueOf(sito.getDatiCsv().getPasseggeriBambini());
		WebElement sistemazione = null;
		Thread.sleep(2000);
		if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("POLTRON")) {
			try {
				sistemazione = driver.findElement(By.xpath("//div[@class='box-componente-quantita poltrona']/div/div"));
			} catch (Exception e) {
				e.printStackTrace();
				sito.setErrori("Poltrona non disponibile.");
			}
			Generic.scrollPage(driver, "1900");
			while (!sistemazione.getText().contains(passeggeri + "")) {
				System.out.println("Passeggeri= "+passeggeri);
				Generic.clickByXPath(driver, "//div[@class='box-componente-quantita poltrona']/div/div/button[@class='button right plus']");
				Thread.sleep(2000);
			}
		} else if (passeggeri > 2) {
			if(sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. INTERNA")){
				if(Integer.valueOf(sito.getDatiCsv().getPasseggeriAnimali()) != 0) {
					try {
						sistemazione = driver.findElement(By.xpath("//div[@data-id='KC4G']//div[@class='box-componente-quantita cabina']/div/div"));
					} catch (Exception e) {
						e.printStackTrace();
						sito.setErrori("Cabina Quadrupla Interna per Animali non disponibile.");
					}
				}else {
					try {
						sistemazione = driver.findElement(By.xpath("//div[@data-id='KC4']//div[@class='box-componente-quantita cabina']/div/div"));
					} catch (Exception e) {
						e.printStackTrace();
						sito.setErrori("Cabina Quadrupla Interna non disponibile.");
					}
				}
			} else if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
				if(Integer.valueOf(sito.getDatiCsv().getPasseggeriAnimali()) != 0) {
					try {
						sistemazione = driver.findElement(By.xpath("//div[@data-id='KC4EG']//div[@class='box-componente-quantita cabina']/div/div"));
					} catch (Exception e) {
						e.printStackTrace();
						sito.setErrori("Cabina Quadrupla Esterna per Animali non disponibile.");
					}
				} else {
					try {
						sistemazione = driver.findElement(By.xpath("//div[@data-id='KC4E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
					} catch (Exception e) {
						e.printStackTrace();
						sito.setErrori("Cabina Quadrupla Esterna non disponibile.");
					}
				}
			}
		} else {
			if(sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. INTERNA")){
				try {
					sistemazione = driver.findElement(By.xpath("//div[@data-id='KC2']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
				} catch (Exception e) {
					e.printStackTrace();
					sito.setErrori("Cabina Doppia Interna non disponibile.");
				}
			} else if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("CAB. ESTERNA")){
				try {
					sistemazione = driver.findElement(By.xpath("//div[@data-id='KC2E']//div[@class='box-componente-quantita cabina']/div/div/button[@class='button right plus']"));
				} catch (Exception e) {
					e.printStackTrace();
					sito.setErrori("Cabina Doppia Esterna non disponibile.");
				}
			}
		}
		boolean scrollo=true;
		if(!sito.getDatiCsv().getSistemazione().toUpperCase().contains("POLTRON")&&!sito.getDatiCsv().getSistemazione().toUpperCase().contains("PONTE")) {
			do {
				try {
					sistemazione.click();
					scrollo=false;
				}catch(Exception e) {
					Generic.scrollPage(driver, "400");
				}	
			}while(scrollo);	
		}
		Thread.sleep(3000);
	}




	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(3000);
	}

	private static void gestioneVeicolo(WebDriver driver, EsitoSito sito)throws Throwable{
		if (sito.getErrori() == null && !sito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {

			Generic.clickByXPath(driver, "//div[@class='content-mobile-select']//button");
			if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");
				} else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
								ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
										"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
								if (elements != null) {
									Generic.clickByXPath(driver,
											"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");
								} else {
									System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
									sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
								}
//				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
//						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
//				if (elements != null) {
//					Generic.clickByXPath(driver,
//							"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
//					Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "500");
//					Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "260");
//				} else {
//					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
//					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
//				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloLungAndata']", "750");
					Generic.sendKeysByXPath(driver, "//input[@id='veicoloAltAndata']", "260");
				} else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");
				} else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}

	}
}
