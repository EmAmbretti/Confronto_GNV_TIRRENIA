package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public static void cliccaDataScelta(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div")).click();

	}
	
	public static void cliccaTastoPiu(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[3]/app-booking-wizard-step3/div/div/app-travel-viewer-numbers/div/div[2]/div[1]/app-counter-wrapper[1]/div[1]/app-counter/div/button[2]")).click();
	
	}
	
	public static void cliccaTastoCerca(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-summary-booking-bar/div/div[2]")).click();
	
	}

}
