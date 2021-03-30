package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	public static void cliccaDestinazioni(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span")).click();
				
	}
	
	public static void selezionaViaggio(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span")).click();

	}
	
	public static void selezionaPartenza(WebDriver driver) {
		driver.findElement(By.xpath("//span[contains(.,'Napoli (Campania)')]")).click();
		
	}
	
	public static void selezionaDestinazione(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"Palermo (Sicilia)-destination\"]/span")).click();
		
	}
	
	public static void cliccaSoloAndata(WebDriver driver) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[1]/app-booking-wizard-step1/div/div/app-travel-viewer-map/div/div[1]/div[1]/label")).click();
		
	}
	
	public static void cliccaContinua(WebDriver driver) {
		driver.findElement(By.xpath("//button[@class='btn btn-lg gnv-btn widget-button next']/span[contains(.,'Continua')]")).click();
	
	}
	
	public static void cliccaFrecciaAvanti(WebDriver driver) {
		driver.findElement(By.xpath("//i[@class='gnv-fe-icon-arrow-light-right']")).click();
	}
	
	public static void controlloMese(WebDriver driver, String month) {
		do {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[1]/span"));
			if(element.getText().contains(month)) {
				break;
			}else {
				cliccaFrecciaAvanti(driver);
			}
		}while(true);
		
	}
	
	public static void cliccaDataScelta(WebDriver driver, String giorno) throws Throwable {
		for(int j=1;j<=5;j++) {
			for(int i=1;i<=7;i++) {
				WebElement element= driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div["+j+"]/div["+i+"]/div"));
				if(element.getText().equals(giorno)) {
					Thread.sleep(2000);
					element.click();
				}
			}
		}
	}
	
	public static void cliccaTastoPiuAdulti(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[3]/app-booking-wizard-step3/div/div/app-travel-viewer-numbers/div/div[2]/div[1]/app-counter-wrapper[1]/div[1]/app-counter/div/button[2]")).click();
	}
	
	public static void cliccaTastoPiuBambini(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[3]/app-booking-wizard-step3/div/div/app-travel-viewer-numbers/div/div[2]/div[1]/app-counter-wrapper[2]/div/app-counter/div/button[2]")).click();
	}
	
	public static void cliccaTastoPiuAnimali(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[3]/app-booking-wizard-step3/div/div/app-travel-viewer-numbers/div/div[2]/div[1]/app-counter-wrapper[4]/div/app-counter/div/button[2]")).click();
	}
	
	public static void cliccaTastoCerca(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-summary-booking-bar/div/div[2]")).click();
	
	}
	
	public static void bypassFrame(WebDriver driver) {
		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
		
	}
	
	public static void scrollDropListById(WebDriver driver, String id, int ripetizioni) {
		for (int i = 1; i <= ripetizioni; i ++) {
			driver.findElement(By.id(id)).sendKeys(Keys.DOWN);
		}
		
	}
	
	public static void scrollDropListByXPath(WebDriver driver, String xPath, int ripetizioni) {
		for (int i = 1; i <= ripetizioni; i ++) {
			driver.findElement(By.xpath(xPath)).sendKeys(Keys.DOWN);
		}
		
	}
	
	public static void clickEnterDropListById(WebDriver driver, String id) {
		driver.findElement(By.id(id)).sendKeys(Keys.ENTER);
		
	}
	
	public static void clickEnterDropListByXPath(WebDriver driver, String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(Keys.ENTER);
		
	}
	
	public static void inserisciPersone(WebDriver driver, String adulti, String bambini,String animali) {
		for(int i=0;i<Integer.valueOf(adulti);i++) {
			cliccaTastoPiuAdulti(driver);
		}
		for(int i=0;i<Integer.valueOf(bambini);i++) {
			cliccaTastoPiuBambini(driver);
		}
		for(int i=0;i<Integer.valueOf(animali);i++) {
			cliccaTastoPiuAnimali(driver);
		}
		
	}
	
	public static void controlloTratta(WebDriver driver, String tratta) {
		for(int i=1;i<=4;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_ddl_destinazioni\"]/option["+i+"]"));
			if(element.getText().equalsIgnoreCase(tratta)) {
				element.click();
			}
		}
	}
	
	public static void cliccaTratte(WebDriver driver) {
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni"));
	}
}
