package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.WebData;
import utils.Generic;

public class HomePage {
	
	public static void cliccaDestinazioni(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span")).click();
				
	}
	
	public static void selezionaViaggio(WebDriver driver) throws Throwable{
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div/app-summary-booking-bar/div/div[1]/div/div[1]/app-summary-bar-block/div/div/span")).click();
		Thread.sleep(3000);
	}
	
	public static void selezionaPartenza(WebDriver driver) {
		driver.findElement(By.xpath("//span[contains(.,'Napoli (Campania)')]")).click();
		
	}
	
	public static void selezionaDestinazione(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"Palermo (Sicilia)-destination\"]/span")).click();
		
	}
	
	public static void cliccaSoloAndata(WebDriver driver) throws Throwable {	
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[1]/app-booking-wizard-step1/div/div/app-travel-viewer-map/div/div[1]/div[1]/label")).click();
		Thread.sleep(3000);
	}
	
	public static void cliccaContinua(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			driver.findElement(By.xpath("//button[@class='btn btn-lg gnv-btn widget-button next']/span[contains(.,'Continua')]")).click();
			Thread.sleep(3000);
		}	
	}
	
	public static void cliccaFrecciaAvanti(WebDriver driver) {
		driver.findElement(By.xpath("//i[@class='gnv-fe-icon-arrow-light-right']")).click();
	}
	
	public static void controlloMese(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			do {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[1]/span"));
				if(element.getText().contains(sito.getMese())) {
					break;
				}else {
					cliccaFrecciaAvanti(driver);
				}
			}while(true);
			Thread.sleep(2000);
		}
	}
	
	public static void cliccaDataScelta(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			for(int j=1;j<=5;j++) {
				for(int i=1;i<=7;i++) {
					WebElement element= driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-wizard/div/app-wizard-step[2]/app-booking-wizard-step2/div/div/app-travel-viewer-dates/div/div[2]/app-calendar/div/div[2]/div[2]/div/div[2]/div[2]/div["+j+"]/div["+i+"]/div"));
					if(element.getText().equals(sito.getGiorno())) {
						Thread.sleep(2000);
						try {
							element.click();
							break;
						} catch (Exception e) {
							sito.setDisponibilita("Il giorno scelto non è disponibile per questo sito.");
						}

					}
				}
			}
			Thread.sleep(3000);
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
	
	public static void cliccaTastoCerca(WebDriver driver,WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/div[1]/div[2]/div/div[1]/app-root/section/app-booking-widget/div[1]/app-summary-booking-bar/div/div[2]")).click();
			Thread.sleep(8000);
		}
		
	}
	
	public static void bypassFrame(WebDriver driver) throws Throwable {
		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
		Thread.sleep(3000);
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
	
	public static void inserisciPasseggeriGNV(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			for(int i=0;i<Integer.valueOf(sito.getAdulti());i++) {
				cliccaTastoPiuAdulti(driver);
			}
			for(int i=0;i<Integer.valueOf(sito.getBambini());i++) {
				cliccaTastoPiuBambini(driver);
			}
			for(int i=0;i<Integer.valueOf(sito.getAnimali());i++) {
				cliccaTastoPiuAnimali(driver);
			}
			Thread.sleep(3000);
		}	
	}
	
	public static void controlloCollegamentoTirrenia(WebDriver driver, WebData sito) throws Throwable {
		for(int i=1;i<=4;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_ddl_destinazioni\"]/option["+i+"]"));
			if(element.getText().equalsIgnoreCase(sito.getCollegamento())) {
				element.click();
				Thread.sleep(2000);
				break;				
			}
			if(i==4) {
				sito.setDisponibilita("Collegamento non disponibile per questo sito.");
			}
		}
	}
	
	public static void checkboxTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		}
	}
	
	public static void cliccaTratteTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "tratte_andata");
			Thread.sleep(2000);
		}
	}
	
	public static void cliccaCollegamentoTirrenia(WebDriver driver) throws Throwable {
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni"));
		Thread.sleep(1000);
	}
	
	public static void selezionaAndataTirrenia(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			try {
				driver.findElement(By.xpath("//*[@id=\"tratte_andata\"]/optgroup/option[contains(text(),'"+sito.getTratta()+"')]")).click();
			}catch(Exception e) {
				sito.setDisponibilita("la tratta per questo sito non è disponibile.");
			}
			Thread.sleep(2000);
		}
	}
	
	public static void cliccaCalendario(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			Generic.clickById(driver, "arrival");
			Thread.sleep(2000);
		}
	}
	
	public static void selezionaMeseTirrenia(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			for(int i=1;i<=12;i++) {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]/option["+i+"]"));
				if(element.getText().equalsIgnoreCase(sito.getMese())) {
					try {
						element.click();
					}catch(Exception e) {
						sito.setDisponibilita("Il mese inserito non è valido.");
						System.out.println("Il mese inserito non è valido!");
					}
					break;
				}
				if(i==12) {
					sito.setDisponibilita("Il mese inserito non è valido.");
					System.out.println("Il mese inserito non è valido!");
				}
			}
			Thread.sleep(2000);
		}
	}
	
	public static void selezionaGiornoTirrenia(WebDriver driver,WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			boolean controllo=false;
			for(int i=1;i<=6;i++) {
				for(int j=1;j<=7;j++) {
					WebElement element=driver.findElement(By.xpath("//*[@id=\"arrival_table\"]/tbody/tr["+i+"]/td["+j+"]/div"));
					if(element.getText().equals(sito.getGiorno())&&(Integer.valueOf(sito.getGiorno())<20||i>1)) {
						Thread.sleep(2000);
						try {
							controllo=true;
							element.click();
						}catch(Exception e) {
							sito.setDisponibilita("Il giorno inserito non è disponibile per questo sito.");
						}
						break;	
					}
				}
				if(controllo) {
					break;
				}
			}
			Thread.sleep(2000);
		}
	}
	
	public static void selezionaPasseggeriTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "input_NumeroPaxAndata");
			Generic.clickByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]");
			Thread.sleep(2000);
			selezionaAdultiTirrenia(driver, sito.getAdulti());
			Thread.sleep(2000);
			Generic.clickById(driver, "select_NumeroPasseggeriAndata");
			selezionaBambiniTirrenia(driver, sito.getBambini());
			Generic.clickById(driver, "select_NumeroAnimaliAndata");
			Thread.sleep(2000);
			selezionaAnimaliTirrenia(driver, sito.getAnimali());
			Thread.sleep(2000);
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button");
			Thread.sleep(2000);
		}
		
	}
	
	private static void selezionaAdultiTirrenia(WebDriver driver, String adulti) {
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAdultiTipo2Andata\"]/option["+(Integer.valueOf(adulti)+1)+"]")).click();
	}
	
	private static void selezionaBambiniTirrenia(WebDriver driver,String bambini) throws Throwable{
		driver.findElement(By.xpath("//*[@id=\"select_NumeroPasseggeriAndata\"]/option["+(Integer.valueOf(bambini)+1)+"]")).click();
		for(int i=0;i<Integer.valueOf(bambini);i++) {
			Thread.sleep(1000);
			driver.findElement(By.id("eta_"+i+"_Andata")).sendKeys("10");
		}
	}
	
	private static void selezionaAnimaliTirrenia(WebDriver driver, String animali) {
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAnimaliAndata\"]/option["+(Integer.valueOf(animali)+1)+"]")).click();
	}
	
	public static void formVeicoloTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "input_VeicoliAndata");
			Thread.sleep(2000);
			selezionaVeicoloTirrenia(driver, sito.getVeicolo());
			Thread.sleep(2000); 
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[17]/button");
			Thread.sleep(2000);
		}
		
	}
	
	private static void selezionaVeicoloTirrenia(WebDriver driver, String veicolo) {
		if(veicolo.equalsIgnoreCase("No")) {
			driver.findElement(By.xpath("//*[@id=\"a_\"]/div")).click();
		}else {
			driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[4]/a[2]/div/label")).click();
		}
	}
	
	public static void cliccaCercaTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if (sito.getDisponibilita() == null) {
			Generic.clickById(driver, "ContentPlaceHolder1_motore_Button_Cerca");

		}
		
	}
	
	public static void selezionaTrattaGNV(WebDriver driver, WebData sito) throws Throwable{
		String[] tratte=sito.getTratta().split(" - ");
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
			sito.setDisponibilita("La tratta non è disponibile per questo sito.");
		}
		
	}
	
	public static void cliccaSoloAndataGrimaldi(WebDriver driver) throws Throwable {
		driver.findElement(By.id("checkReturn")).click();
		Thread.sleep(1000);
	}
	
	public static void selezionaAndataGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		try {
			driver.findElement(By.xpath("//option[contains(text(),'" + sito.getTratta().toUpperCase() + "')]")).click();
		}catch (Exception e){
			sito.setDisponibilita("la tratta per questo sito non è disponibile.");
		}
		
	}
	
	public static void scegliDataGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "dateLeg1");
			Thread.sleep(1000);
			Generic.clickByXPath(driver, "//*[@id=\"ui-datepicker-div\"]/div/div/select[1]");
			selezionaMeseGrimaldi(driver, sito);			
		}
		if(sito.getDisponibilita() == null) {
			selezionaGiornoGimaldi(driver, sito);
			Thread.sleep(5000);
		}
		
	}
	
	private static void selezionaMeseGrimaldi(WebDriver driver, WebData sito){
		String meseGrimaldi = sito.getMese().substring(0, 3);
		for(int i=1;i<=12;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(meseGrimaldi)) {
				try {
					element.click();
					Thread.sleep(1000);
					break;
				}catch(Exception e) {
					System.out.println("Il mese inserito non è valido!");
					sito.setDisponibilita("Il mese inserito non è valido!");
				}

			}
			if(i==12) {
				sito.setDisponibilita("Il mese inserito non è valido!");
				System.out.println("Il mese inserito non è valido!");
			}
		}


	}
	
	private static void selezionaGiornoGimaldi(WebDriver driver, WebData sito ) throws Throwable{
		boolean controllo=false;
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=7;j++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(element.getText().equals(sito.getGiorno())) {
					try {
						element.click();
						controllo=true;
						break;
					} catch(Exception e) {
						sito.setDisponibilita("Il giorno inserito non è valido!");
						break;
					}
					
				}
			}
			if(controllo) {
				break;
			}
		}
	}
	
	public static void aggiungiSistemazioniPasseggeriGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			selezionaSistemazioneGrimaldi(driver, sito);
		}
		if(sito.getDisponibilita() == null) {
			inseriscoPasseggeriGrimaldi(driver, sito);			
		}
				
	}
	
	private static void selezionaSistemazioneGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		Generic.clickById(driver, "accLeg1Select");
		Thread.sleep(2000);
		try {
			Generic.clickByXPath(driver, "//*[@id=\"accBox\"]/div/div[2]/span");
		} catch (Exception e) {
			sito.setDisponibilita("Nessuna sistemazione disponibile per i criteri selezionati.");
		}
		if(sito.getDisponibilita() == null) {
			try {
				List<WebElement> elementList = driver.findElements(By.xpath("//ul/li[contains(text(),'" + sito.getSistemazione() + "')]"));
				elementList.get(0).click();
			} catch (Exception e) {
				sito.setDisponibilita("la sistemazione \"" + sito.getSistemazione() +"\" non è disponibile.");
			}
		}
		
	}
	
	private static void inseriscoPasseggeriGrimaldi(WebDriver driver, WebData sito) throws Throwable{
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[1]/div/div[2]/b");
		selezionaPasseggeriAdultiGrimaldi(driver, sito.getAdulti());
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[2]/div/div[2]/b");
		selezionaPassaggeriBambiniGrimaldi(driver, sito.getBambini());
		Thread.sleep(2000);
		Generic.clickById(driver, "createacc");
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
	
	public static void selezionaAnimaliGrimaldi(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita() == null) {
			if(!sito.getAnimali().equals("0")) {
				Generic.clickById(driver, "petLeg1Select");
				Thread.sleep(2000);
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[2]/b");
				List<WebElement> elementList = driver.findElements(By.xpath("//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[3]/div/ul/li[contains(text(),'"+sito.getAnimali()+"')]"));
				if(sito.getAnimali().equals("10")) {
					elementList.get(1).click();
				}else {
					elementList.get(0).click();
				}
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/div[2]/input");
			}
		}
			
	}
	
	public static void cliccaRicercaGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "searchnow");
			Thread.sleep(3000);
		}
	}
	
	public static void cliccaSuDataGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			Generic.clickById(driver, "start-date");
		}
	}
	
	public static void prenotaOraGrimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			Generic.clickById(driver, "confirmRouteForm");
			Thread.sleep(1000);
		}	
	}
	public static void chiudiPopupPag2Grimaldi(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			Generic.clickByXPath(driver, "/html/body/div[11]/div/div[3]/button");
		}
	}
}
