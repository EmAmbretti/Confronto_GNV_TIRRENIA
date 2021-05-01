package pages.grimaldi;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageGRIMALDI {

	public static void gestioneVeicoloGrimaldi(WebDriver driver, EsitoSito esito, CSVData data) throws Throwable {
		if (esito.getErrori() == null && !data.getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickById(driver, "carLeg1Select");
			//Mirko
			Thread.sleep(1000);
			//Mirko
			Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[2]/b");
			if (data.getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Thread.sleep(1000);
				Generic.clickByXPath(driver, "//tr[@id=\"setVehLH\"]/td[1]/div[5]/div[1]");
				Thread.sleep(1000);
//				Generic.clickByXPath(driver, "//input[@id='mtlCar']");
				Generic.sendKeysByXPath(driver, "//input[@id='mtlCar']", "550");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + "non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				//Mirko
				Thread.sleep(1000);
				Generic.sendKeysById(driver, "mtlCar", "7");
				Thread.sleep(1000);
				//Mirko
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (data.getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(data.getVeicolo() + " non disponibile per questa tratta");
					esito.setErrori(data.getVeicolo() + " non disponibile per questa tratta");
				}
			} 
		}
	}
	
	public static void bypassFrame(WebDriver driver) throws Throwable {
//		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
//		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//*[@id=\"top\"]/div[3]/div/div[3]/button")).isDisplayed()) {
			Generic.clickByXPath(driver, "//*[@id=\"top\"]/div[3]/div/div[3]/button");
		}
	}
	
	public static void cliccaSoloAndataGrimaldi(WebDriver driver) throws Throwable {
		driver.findElement(By.id("checkReturn")).click();
		Thread.sleep(1000);
	}
	
	public static void selezionaAndataGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		try {
			driver.findElement(By.xpath("//option[contains(text(),'" + sito.getTrattaAndata().toUpperCase() + "')]")).click();
		}catch (Exception e){
			esito.setErrori("la tratta per questo sito non è disponibile.");
		}
		
	}
	
	public static void scegliDataGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickById(driver, "dateLeg1");
			Thread.sleep(1000);
			Generic.clickByXPath(driver, "//*[@id=\"ui-datepicker-div\"]/div/div/select[1]");
			selezionaMeseGrimaldi(driver, sito, esito);			
		}
		if(esito.getErrori() == null) {
			selezionaGiornoGimaldi(driver, sito, esito);
			Thread.sleep(5000);
		}
		
	}
	
	private static void selezionaMeseGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito){
		String meseGrimaldi = sito.getMeseAndata().substring(0, 3);
		for(int i=1;i<=12;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(meseGrimaldi)) {
				try {
					element.click();
					Thread.sleep(1000);
					break;
				}catch(Exception e) {
					System.out.println("Il mese inserito non è valido!");
					esito.setErrori("Il mese inserito non è valido!");
				}

			}
			if(i==12) {
				esito.setErrori("Il mese inserito non è valido!");
				System.out.println("Il mese inserito non è valido!");
			}
		}


	}
	
	private static void selezionaGiornoGimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		boolean controllo=false;
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=7;j++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(element.getText().equals(sito.getGiornoAndata())) {
					try {
						element.click();
						controllo=true;
						break;
					} catch(Exception e) {
						esito.setErrori("Il giorno inserito non è valido!");
						break;
					}
					
				}
			}
			if(controllo) {
				break;
			}
		}
	}
	
	public static void aggiungiSistemazioniPasseggeriGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			selezionaSistemazioneGrimaldi(driver, sito, esito);
		}
		if(esito.getErrori() == null) {
			inseriscoPasseggeriGrimaldi(driver, sito);			
		}
				
	}
	
	private static void selezionaSistemazioneGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		Generic.clickById(driver, "accLeg1Select");
		Thread.sleep(2000);
		try {
			Generic.clickByXPath(driver, "//*[@id=\"accBox\"]/div/div[2]/span");
		} catch (Exception e) {
			esito.setErrori("Nessuna sistemazione disponibile per i criteri selezionati.");
		}
		if(esito.getErrori() == null) {
			try {
				List<WebElement> elementList = driver.findElements(By.xpath("//ul/li[contains(text(),'" + sito.getSistemazione() + "')]"));
				elementList.get(0).click();
			} catch (Exception e) {
				esito.setErrori("la sistemazione \"" + sito.getSistemazione() +"\" non è disponibile.");
			}
		}
		
	}
	
	private static void inseriscoPasseggeriGrimaldi(WebDriver driver, CSVData sito) throws Throwable{
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[1]/div/div[2]/b");
		selezionaPasseggeriAdultiGrimaldi(driver, sito.getPasseggeriAdulti());
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[2]/div/div[2]/b");
		selezionaPassaggeriBambiniGrimaldi(driver, sito.getPasseggeriBambini());
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
		List<WebElement> elementList=driver.findElements(By.xpath("//table[2]/tbody/tr[2]/td[2]/div/div[3]/div/ul/li[contains(text(),'"+bambini+"')]"));
		if(bambini.equals("10")) {
			elementList.get(1).click();
		}else {
			elementList.get(0).click();
		}
	}
	
	public static void selezionaAnimaliGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		if(esito.getErrori() == null) {
			if(!sito.getPasseggeriAnimali().equals("0")) {
				Generic.clickById(driver, "petLeg1Select");
				Thread.sleep(2000);
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[2]/b");
				List<WebElement> elementList = driver.findElements(By.xpath("//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[3]/div/ul/li[contains(text(),'"+sito.getPasseggeriAnimali()+"')]"));
				if(sito.getPasseggeriAnimali().equals("10")) {
					elementList.get(1).click();
				}else {
					elementList.get(0).click();
				}
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/div[2]/input");
			}
		}
			
	}
	
	public static void cliccaRicercaGrimaldi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickById(driver, "searchnow");
			Thread.sleep(3000);
		}
	}
	
	public static void cliccaSuDataGrimaldi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			Generic.clickById(driver, "start-date");
		}
	}
	
	public static void prenotaOraGrimaldi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			Generic.clickById(driver, "confirmRouteForm");
			Thread.sleep(1000);
		}	
	}
	public static void chiudiPopupPag2Grimaldi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			Generic.clickByXPath(driver, "/html/body/div[11]/div/div[3]/button");
		}
	}
}
