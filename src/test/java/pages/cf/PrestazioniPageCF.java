package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class PrestazioniPageCF {

	public static void automationPaginaPrestazioni(WebDriver driver, EsitoSito sito) {
		System.out.println("\nAutomationPaginaPrestazioni");
		if(sito.getErrori()==null) {
			gestionePasseggeri(driver, sito);
			gestioneVeicoloCF(driver, sito);
			gestioneSistemazione(driver, sito);
			sceltaTariffa(driver, sito);
		} else {
			System.out.println("ERRORE: "+sito.getErrori());
		}
	}
	
	private static void gestionePasseggeri(WebDriver driver, EsitoSito esito) {
		System.out.println("\nMetodo gestionePasseggeri");
		int i = 1;
		int numeroPasseggeriAdulti = Integer.valueOf(esito.getDatiCsv().getPasseggeriAdulti());
		while(i < numeroPasseggeriAdulti){
			i++;
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]");
		} //							  //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]
		//								  //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]
		i = 1;
		int numeroPasseggeriBambini = Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini());
		while(i < numeroPasseggeriBambini){
			i++;
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]");
		}
		

		i = 1;
		int numeroPasseggeriAnimali = Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali());
		if(numeroPasseggeriAnimali!=0) {
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/div/span/span[1]");
		}
		while(i < numeroPasseggeriAnimali){
			i++;
			// + CANI
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/ul/li[1]/div[2]/button[2]");
		}
		
	}
	
	private static void gestioneVeicoloCF(WebDriver driver, EsitoSito esito) {
		System.out.println("\nMetodo gestioneVeicoloCF");
		if (esito.getErrori() == null && !esito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				WebElement element = null;
				try {
					element = driver.findElement(By
							.xpath("//button[@class='change-option']//span[@class='label'][contains(.,'Modificare')]"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (element == null) {
					ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]");
					if (elements != null) {
						Generic.clickByList(driver,
								"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 0);
						Generic.clickByXPath(driver, "//label[contains(.,'fino a 4.00m')]//span[@class='checkbox']");
						Generic.clickByList(driver, "//button[@class='btn btn-main'][contains(.,'Convalidare')]", 0);
					} else {
						System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
						esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					}
				}

			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//button[@class='change-option']//span[@class='label'][contains(.,'Modificare')]");
				if (elements!=null) {
					Generic.clickByList(driver,
							"//button[@class='change-option']//span[@class='label'][contains(.,'Modificare')]", 0);
					Generic.clickByXPath(driver, "//label[contains(.,'oltre 5.01m')]//span[@class='checkbox']");
					Generic.sendKeysByXPath(driver, "//input[@class='car-dimensions-size']", "550");
					Generic.clickByList(driver, "//button[@class='btn btn-main'][contains(.,'Convalidare')]", 0);
				}else {
					ArrayList<WebElement> elementsList = Generic.getElementListByXPath(driver, "//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]"); 
						if (elementsList!=null) {
					Generic.clickByList(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 0);
					Generic.clickByXPath(driver, "//label[contains(.,'oltre 5.01m')]//span[@class='checkbox']");
					Generic.sendKeysByXPath(driver, "//input[@class='car-dimensions-size']", "550");
					Generic.clickByList(driver, "//button[@class='btn btn-main'][contains(.,'Convalidare')]", 0);
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
				}

			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]");
				if (elements!=null) {
					Generic.clickByList(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 1);
					Generic.clickByList(driver, "//label[contains(.,'oltre 5.01m')]//span[@class='checkbox']", 1);
					Generic.sendKeysByList(driver, "//input[@class='car-dimensions-size']", "750", 1);
					Generic.clickByList(driver, "//button[@class='btn btn-main'][contains(.,'Convalidare')]", 1);
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}

			} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]");
				if (elements!=null) {
					Generic.clickByList(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 2);
				} else {
					System.out.println(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(esito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			}
		} else {
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[5]/span/span[1]");
			System.out.println("Non ci sono veicoli da inserire.");
		}
	}
	
	//Click + "Bici": //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[4]/div[2]/button[2]
	
	private static void gestioneSistemazione(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo gestioneSistemazione");
		// LISTA CONTENENTE TUTTE LE SISTEMAZIONI VISIBILI A FRONT END
		ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver, "//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[4]/div[4]/div/div[1]/div[2]/ul/li[@class='choice-item choice-item--withHead']/div[2]");

		for(int i=0; i<elementList.size(); i++) {
			if(sito.getDatiCsv().getSistemazione().contains("POLTRON")) {
				if(elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().contains("Poltron")) {
					int whilev = 0;
					int numeroPasseggeriAdulti = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti());
					while(whilev > numeroPasseggeriAdulti){
						whilev++;
						elementList.get(i).findElement(By.xpath(".//div//div[@class='quantity']/button[2]")).click();
					}
				}
			}
			
			if(sito.getDatiCsv().getSistemazione().contains("CABINA ESTERNA")) {
				if(elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().contains("Cabina esclusiva con oblò")) {
					elementList.get(i).findElement(By.xpath(".//div/button[1]")).click(); 
					elementList.get(i).findElement(By.xpath(".//div/div/div/div/div/div[@class='quantity']/button[2]")).click();
					elementList.get(i).findElement(By.xpath(".//div[2]/div/button[2]")).click();
					break;
				}
			}
			
			if(sito.getDatiCsv().getSistemazione().contains("CABINA INTERNA")) {
				if(elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText().contains("Cabina esclusiva senza oblò")) {
					elementList.get(i).findElement(By.xpath(".//div/button[1]")).click(); 
					elementList.get(i).findElement(By.xpath(".//div/div/div/div/div/div[@class='quantity']/button[2]")).click();
					elementList.get(i).findElement(By.xpath(".//div[2]/div/button[2]")).click();
					break;
				}
			}
		}
		
	}
	
	private static void sceltaTariffa(WebDriver driver, EsitoSito sito) {
		System.out.println("\nMetodo sceltaTariffa");
		ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver, "//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[8]/div[2]/div/div[@class='BookingRateSummary-item']");
		
		for(int i=0; i<elementList.size(); i++) {
			
			if(elementList.get(i).findElement(By.xpath(".//div[1]/span[@class='price-name']") ).getText().contains("STANDARD") ) {
				elementList.get(i).findElement(By.xpath(".//div[2]")).click();
				break;
			}
		}
		
		Generic.scrollPage(driver, "document.body.scrollHeight");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sito.setPrezzo(Generic.getElementByXPath(driver, "//*[@id=\"content\"]/div/div[2]/div/div/div[2]/div[2]/div/div[2]").getText());
		System.out.println("PREZZO RECUPERATO: "+sito.getPrezzo());
	}
	
	
}
