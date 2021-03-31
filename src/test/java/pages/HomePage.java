package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Generic;

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
					try {
						element.click();
						break;
					} catch (Exception e) {
						System.out.println("La data selezionata non è valida!");
						driver.close();
					}
					
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
	
	public static void selezionaAndataTirrenia(WebDriver driver, String text) throws Throwable{
		driver.findElement(By.xpath("//*[@id=\"tratte_andata\"]/optgroup/option[contains(text(),'"+text+"')]")).click();
	}
	
	public static void selezionaMeseTirrenia(WebDriver driver, String text){
		for(int i=1;i<=12;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]/option["+i+"]"));
			if(element.getText().equalsIgnoreCase(text)) {
				try {
					element.click();
					break;
				}catch(Exception e) {
					System.out.println("Il mese inserito non è valido!");
					driver.close();
				}
				
			}
			if(i==12) {
				System.out.println("Il mese inserito non è valido!");
				driver.close();
			}
		}
	}
	
	public static void selezionaGiornoTirrenia(WebDriver driver,String giorno) throws Throwable{
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=7;j++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"arrival_table\"]/tbody/tr["+i+"]/td["+j+"]/div"));
				if(element.getText().equals(giorno)&&(Integer.valueOf(giorno)<20||i>1)) {
					Thread.sleep(2000);
					element.click();
					break;
				}
			}
		}
	}
	
	public static void selezionaAdultiTirrenia(WebDriver driver, String adulti) {
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAdultiTipo2Andata\"]/option["+(Integer.valueOf(adulti)+1)+"]")).click();
	}
	
	public static void selezionaBambiniTirrenia(WebDriver driver,String bambini) throws Throwable{
		driver.findElement(By.xpath("//*[@id=\"select_NumeroPasseggeriAndata\"]/option["+(Integer.valueOf(bambini)+1)+"]")).click();
		for(int i=0;i<Integer.valueOf(bambini);i++) {
			Thread.sleep(1000);
			driver.findElement(By.id("eta_"+i+"_Andata")).sendKeys("10");
		}
	}
	
	public static void selezionaAnimaliTirrenia(WebDriver driver, String animali) {
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAnimaliAndata\"]/option["+(Integer.valueOf(animali)+1)+"]")).click();
	}
	
	public static void selezionaVeicoloTirrenia(WebDriver driver, String veicolo) {
		if(veicolo.equalsIgnoreCase("No")) {
			driver.findElement(By.xpath("//*[@id=\"a_\"]/div")).click();
		}else {
			driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[4]/a[2]/div/label")).click();
		}
	}
	
	public static void selezionaTrattaGNV(WebDriver driver, String tratta) throws Throwable{
		String[] tratte=tratta.split(" - ");
		String partenza=tratte[0];
		String arrivo=tratte[1];
		try {
			List<WebElement> elementList=driver.findElements(By.xpath("//label/span[contains(text(),'"+partenza+"')]"));
		elementList.get(0).click();
		Thread.sleep(2000);
		elementList=driver.findElements(By.xpath("//label/span[contains(text(),'"+arrivo+"')]"));
		elementList.get(elementList.size()-1).click();
		} catch (Exception e) {
			e.printStackTrace();
			String errore = "La tratta scelta non è dispobibile.";
		}
		
	}
	
	public static void cliccaSoloAndataGrimaldi(WebDriver driver) throws Throwable {
		driver.findElement(By.id("checkReturn")).click();
		Thread.sleep(1000);
	}
	
	public static void selezionaAndataGrimaldi(WebDriver driver, String text) throws Throwable {
		driver.findElement(By.xpath("//option[contains(text(),'" + text.toUpperCase() + "')]")).click();
	}
	
	public static void selezionaMeseGrimaldi(WebDriver driver, String text){
		text=text.substring(0, 3);
		for(int i=1;i<=12;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(text)) {
				try {
					element.click();
					break;
				}catch(Exception e) {
					System.out.println("Il mese inserito non è valido!");
					driver.close();
				}
				
			}
			//i<dataOdierna.getMonthOfYear()
			if(i==12) {
				System.out.println("Il mese inserito non è valido!");
			}
		}
	}
	public static void selezionaGiornoGimaldi(WebDriver driver,String giorno) throws Throwable{
		boolean controllo=false;
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=7;j++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(element.getText().equals(giorno)) {
					Thread.sleep(2000);
					element.click();
					controllo=true;
					break;
				}
			}
			if(controllo) {
				break;
			}
		}
	}
	
	public static void selezionaSistemazioneGrimaldi(WebDriver driver) {
		List<WebElement> elementList = driver.findElements(By.xpath("//ul/li[contains(text(),'Poltrona')]"));
		elementList.get(0).click();
	}
	
	public static void inseriscoPasseggeriGrimaldi(WebDriver driver, String adulti, String bambini) throws Throwable{
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[1]/div/div[2]/b");
		selezionaPasseggeriAdultiGrimaldi(driver, adulti);
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[2]/div/div[2]/b");
		selezionaPassaggeriBambiniGrimaldi(driver, bambini);
	}
	
	private static void selezionaPasseggeriAdultiGrimaldi(WebDriver driver, String adulti) {
		List<WebElement> elementList=driver.findElements(By.xpath("//table[2]/tbody/tr[2]/td[1]/div/div[3]/div/ul/li[contains(text(),'"+adulti+"')]"));
		if(adulti.equals("10")) {
			elementList.get(1).click();
		}else {
			elementList.get(0).click();
		}
	}
	private static void selezionaPassaggeriBambiniGrimaldi(WebDriver driver, String bambini) {
		//table[2]/tbody/tr[2]/td[2]/div/div[3]/div/ul/li[contains(text(),'1')]
		List<WebElement> elementList=driver.findElements(By.xpath("//table[2]/tbody/tr[2]/td[2]/div/div[3]/div/ul/li[contains(text(),'"+bambini+"')]"));
		if(bambini.equals("10")) {
			elementList.get(1).click();
		}else {
			elementList.get(0).click();
		}
	}
	public static void selezionaAnimaliGrimaldi(WebDriver driver, String animali) throws Throwable{
		if(!animali.equals("0")) {
			Generic.clickById(driver, "petLeg1Select");
			Thread.sleep(2000);
			Generic.clickByXPath(driver, "//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[2]/b");
			List<WebElement> elementList = driver.findElements(By.xpath("//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[3]/div/ul/li[contains(text(),'"+animali+"')]"));
			if(animali.equals("10")) {
				elementList.get(1).click();
			}else {
				elementList.get(0).click();
			}
			Generic.clickByXPath(driver, "//div[7]/div/div[1]/div[2]/input");
		}	
	}
}
