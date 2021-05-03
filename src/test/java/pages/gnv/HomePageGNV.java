package pages.gnv;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageGNV {
	
	public static void closePopupGNV(WebDriver driver, EsitoSito sito) {
		try {
		if(Generic.getElementByXPath(driver, "//div[@id='closeXButton']//span[@class='close-icon']").isDisplayed())
		Generic.clickByXPath(driver, "//div[@id='closeXButton']//span[@class='close-icon']");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void selezionaTrattaGNV(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		String[] tratte=sito.getTrattaAndata().split(" - ");
		String partenza=tratte[0];
		String arrivo=tratte[1];
		try {
			List<WebElement> elementList=driver.findElements(By.xpath("//label/span[contains(text(),'"+partenza+"')]"));
		elementList.get(0).click();
		Thread.sleep(1000);
		elementList=driver.findElements(By.xpath("//label/span[contains(text(),'"+arrivo+"')]"));
		elementList.get(elementList.size()-1).click();
		Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			esito.setErrori("la tratta per questo sito non è disponibile.");
		}
		
	}
	
	public static void cliccaSoloAndataGNV(WebDriver driver) throws Throwable {	
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[1]/app-booking-wizard-step1/div/div/app-travel-viewer-map/div/div[1]/div[1]/label")).click();
		Thread.sleep(3000);
	}
	
	public static void cliccaContinuaGNV(WebDriver driver) {
		try {
			Generic.clickByXPath(driver, "//button[@class='btn btn-lg gnv-btn widget-button next']");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cliccaFrecciaAvantiGNV(WebDriver driver) {
		driver.findElement(By.xpath("//i[@class='gnv-fe-icon-arrow-light-right']")).click();
	}
	
	public static void controlloMeseGNV(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		if(esito.getErrori()==null) {
			do {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[1]/span"));
				if(element.getText().contains(sito.getMeseAndata())) {
					break;
				}else {
					cliccaFrecciaAvantiGNV(driver);
				}
			} while(true);
			Thread.sleep(2000);
		}
	}
	
	public static void cliccaDataSceltaGNV(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			for(int j = 1; j <= 5; j ++) {
				for(int i = 1; i <= 7; i ++) {
					WebElement element= driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div["+j+"]/div["+i+"]/div"));
					if(element.getText().equals(sito.getGiornoAndata())) {
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
			Thread.sleep(3000);
		}
	}
	
	public static void cliccaAdutiGNV(WebDriver driver, CSVData data) {
		if(Integer.valueOf(data.getPasseggeriAdulti())!=0) {
		for(int i=0; i<=Integer.valueOf(data.getPasseggeriAdulti()); i++) {
			Generic.clickByList(driver, "//i[@class='gnv-fe-icon-plus']", 0);
		}
		}
	}
	
	public static void cliccaBambinoGNV(WebDriver driver, CSVData data) {
		if(Integer.valueOf(data.getPasseggeriBambini())!=0) {
		for(int i=0; i<=Integer.valueOf(data.getPasseggeriBambini()); i++) {
			Generic.clickByList(driver, "//i[@class='gnv-fe-icon-plus']", 1);
		}
		}
	}
	
	public static void cliccaAnimaliGNV(WebDriver driver, CSVData data) {
		if(Integer.valueOf(data.getPasseggeriAnimali())!=0) {
			for(int i=0; i<=Integer.valueOf(data.getPasseggeriAnimali()); i++) {
				Generic.clickByList(driver, "//i[@class='gnv-fe-icon-plus']", 3);
			}
		}
	}
	
	public static void cliccaCercaGNV(WebDriver driver) {
		try {
		Generic.clickByXPath(driver, "//div[@class='widget-summary-next active'][contains(.,'Cerca')]");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gestioneVeicoloGNV(WebDriver driver, CSVData data, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//div[@class='journey-summary-block active search-active last']");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByXPath(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a 1,9m')]");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByList(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 1);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a  2,9m')]");
					Generic.clickByXPath(driver, "//select[@id='length']");
					Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 5m  e 6m')]");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if (elements != null) {
					Generic.clickByList(driver,
							"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 2);
					Generic.clickByXPath(driver, "//select[@id='height']");
					Generic.clickByXPath(driver,
							"//select[@class='custom-select custom-select-lg ng-valid ng-touched ng-dirty']/option[contains(.,'Inferiore a  2,9m')]");
					Generic.clickByXPath(driver, "//select[@id='length']");
					Generic.clickByXPath(driver, "//select[@id='length']/option[contains(.,'Tra 7m  e 8m')]");
					Generic.clickByXPath(driver, "//span[@class='gnv-icon icon-right ng-star-inserted']");
				} else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@class='counter-wrapper orange']//button[@class='input-number-increment']");
				if(elements!=null) {
				Generic.clickByList(driver,
						"//div[@class='counter-wrapper orange']//button[@class='input-number-increment']", 4);
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			}
		}
	}
}
