package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class PrestazioniPageCF {

	public static void automationPaginaPrestazioni(WebDriver driver, CSVData datiSito, EsitoSito esito) {
		gestionePasseggeri(driver, datiSito, esito);
		gestioneVeicoloMoby(driver, datiSito, esito);
	}
	
	private static void gestionePasseggeri(WebDriver driver, CSVData datiSito, EsitoSito esito) {
		int i = 1;
		int numeroPasseggeriAdulti = Integer.valueOf(datiSito.getPasseggeriAdulti());
		while(i > numeroPasseggeriAdulti){
			i++;
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]");
		}
		
		i = 1;
		int numeroPasseggeriBambini = Integer.valueOf(datiSito.getPasseggeriBambini());
		while(i > numeroPasseggeriBambini){
			i++;
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]");
		}
		

		i = 1;
		int numeroPasseggeriAnimali = Integer.valueOf(datiSito.getPasseggeriAnimali());
		if(numeroPasseggeriAnimali!=0) {
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/div/span/span[1]");
		}
		while(i > numeroPasseggeriAnimali){
			i++;
			// + CANI
			Generic.clickByXPath(driver, "//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/ul/li[1]/div[2]/button[2]");
		}
		
	}
	
	private static void gestioneVeicoloMoby(WebDriver driver, CSVData data, EsitoSito esito) {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
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
						System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
						esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
					}
				}

			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
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
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
				}

			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]");
				if (elements!=null) {
					Generic.clickByList(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 1);
					Generic.clickByList(driver, "//label[contains(.,'oltre 5.01m')]//span[@class='checkbox']", 1);
					Generic.sendKeysByList(driver, "//input[@class='car-dimensions-size']", "750", 1);
					Generic.clickByList(driver, "//button[@class='btn btn-main'][contains(.,'Convalidare')]", 1);
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}

			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]");
				if (elements!=null) {
					Generic.clickByList(driver,
							"//button[@class='btn btn-full-yellow add-option'][contains(.,'Scegliere')]", 2);
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}
	}
	
	//Click + "Bici": //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[4]/div[2]/button[2]
	
	
	
	
}
