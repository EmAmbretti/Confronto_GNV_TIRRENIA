package pages.grimaldi;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class BookingPageGRIMALDI {
	
	public static void compilaDatiBookingGRIMALDI(WebDriver driver, EsitoSito sito) throws Throwable {
		chiudiPopupPag2Grimaldi(driver, sito);
		scegliDataGrimaldi(driver, sito);
		aggiungiSistemazioniPasseggeriGrimaldi(driver, sito);
		selezionaAnimaliGrimaldi(driver, sito);
		gestioneVeicoloGrimaldi(driver, sito);
		cliccaRicercaGrimaldi(driver, sito);
	}
	
	private static void scegliDataGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			Generic.clickById(driver, "dateLeg1");
			Thread.sleep(1000);
			Generic.clickByXPath(driver, "//*[@id=\"ui-datepicker-div\"]/div/div/select[1]");
			selezionaMeseGrimaldi(driver, sito);			
		}
		if(sito.getErrori() == null) {
			selezionaGiornoGimaldi(driver, sito);
			Thread.sleep(1000);
		}	
	}
	
	private static void selezionaMeseGrimaldi(WebDriver driver, EsitoSito sito){
		String meseGrimaldi = sito.getDatiCsv().getMeseAndata().substring(0, 3);
		for(int i=1;i<=12;i++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(meseGrimaldi)) {
				try {
					element.click();
					Thread.sleep(1000);
					break;
				}catch(Exception e) {
					System.out.println("Il mese inserito non è valido!");
					sito.setErrori("Il mese inserito non è valido!");
				}
			}
			if(i==12) {
				sito.setErrori("Il mese inserito non è valido!");
				System.out.println("Il mese inserito non è valido!");
			}
		}
	}
	
	private static void selezionaGiornoGimaldi(WebDriver driver, EsitoSito sito) throws Throwable{
		boolean controllo=false;
		for(int i=1;i<=6;i++) {
			for(int j=1;j<=7;j++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(element.getText().equals(sito.getDatiCsv().getGiornoAndata())) {
					try {
						element.click();
						controllo=true;
						break;
					} catch(Exception e) {
						sito.setErrori("Il giorno inserito non è valido!");
						break;
					}
					
				}
			}
			if(controllo) {
				break;
			}
		}
	}
	
	private static void aggiungiSistemazioniPasseggeriGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			while(driver.findElement(By.id("btAccLeg1")).getAttribute("style").contains("none")) {
				System.out.println("attendo caricamento sistemazioni...");
				Thread.sleep(1500);
			}
			selezionaSistemazioneGrimaldi(driver, sito);
		}
		if(sito.getErrori() == null) {
			inseriscoPasseggeriGrimaldi(driver, sito);			
		}
				
	}
	
	private static void selezionaSistemazioneGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {		
		Generic.clickById(driver, "accLeg1Select");
		Thread.sleep(2000);
		try {
			Generic.clickByXPath(driver, "//*[@id=\"accBox\"]/div/div[2]/span");
		} catch (Exception e) {
			sito.setErrori("Nessuna sistemazione disponibile per i criteri selezionati.");
		}
		if(sito.getErrori() == null) {
			
				String sistemazione=setSistemazioneString(sito.getDatiCsv());
				int i=(Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()))+(Integer.valueOf(sito.getDatiCsv().getPasseggeriBambini()));
				boolean flag=false;
				List<WebElement> elementList = driver.findElements(By.xpath("//*[@id=\"accBox\"]/div/div[3]/div/ul/li"));
				while(i<=4) {
					for(WebElement element:elementList) {
						if(element.getText().equalsIgnoreCase(sistemazione)) {
							System.out.println("trovato");
							element.click();
							flag=true;
							break;
						}
					}				
					if(flag) {
						sito.appendNote("Sistemazione richiesta non disponibile, setto nuova sistemazione: "+sistemazione);
						break;
					}
					i++;
					sistemazione=sistemazione.replace((i-1)+"", i+"");
				}	
			if(i>4&&!sistemazione.equalsIgnoreCase("Poltrona")&&!sistemazione.equalsIgnoreCase("Ponte")) {
				sito.setErrori("la sistemazione \"" + sistemazione +"\" non è disponibile.");
			}
		}
	}
	
	private static String setSistemazioneString(CSVData sito) {
		if(sito.getSistemazione().equalsIgnoreCase("cab. interna")) {
			if((Integer.valueOf(sito.getPasseggeriAdulti()))+(Integer.valueOf(sito.getPasseggeriBambini()))<=2) {
				return "Cabina Interna Uso Esclusivo (max 2 persone)";
			}else if((Integer.valueOf(sito.getPasseggeriAdulti()))+(Integer.valueOf(sito.getPasseggeriBambini()))==3) {
				return "Cabina Interna Uso Esclusivo (max 3 persone)";
			}else {
				return "Cabina Interna Uso Esclusivo (max 4 persone)";
			}
			
		}else if(sito.getSistemazione().equalsIgnoreCase("cab. esterna")) {
			if((Integer.valueOf(sito.getPasseggeriAdulti()))+(Integer.valueOf(sito.getPasseggeriBambini()))<=2) {
				return "Cabina Esterna Uso Esclusivo (max 2 persone)";
			}else if((Integer.valueOf(sito.getPasseggeriAdulti()))+(Integer.valueOf(sito.getPasseggeriBambini()))==3) {
				return "Cabina Esterna Uso Esclusivo (max 3 persone)";
			}else {
				return "Cabina Esterna Uso Esclusivo (max 4 persone)";
			}
		}else if(sito.getSistemazione().equalsIgnoreCase("Ponte")){
			return "Passaggio Ponte";
		}else {
			return sito.getSistemazione();
		}	
	}
	
	private static void inseriscoPasseggeriGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable{
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[1]/div/div[2]/b");
		selezionaPasseggeriAdultiGrimaldi(driver, sito.getDatiCsv().getPasseggeriAdulti());
		Generic.clickByXPath(driver, "//*[@id=\"boxTopAcc\"]/table[2]/tbody/tr[2]/td[2]/div/div[2]/b");
		selezionaPassaggeriBambiniGrimaldi(driver, sito.getDatiCsv().getPasseggeriBambini());
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
	
	private static void selezionaAnimaliGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori() == null) {
			if(!sito.getDatiCsv().getPasseggeriAnimali().equals("0")) {
				Generic.clickById(driver, "petLeg1Select");
				Thread.sleep(2000);
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[2]/b");
				List<WebElement> elementList = driver.findElements(By.xpath("//div[7]/div/div[1]/table/tbody/tr[2]/td[2]/div/div/div[3]/div/ul/li[contains(text(),'"+sito.getDatiCsv().getPasseggeriAnimali()+"')]"));
				if(sito.getDatiCsv().getPasseggeriAnimali().equals("10")) {
					elementList.get(1).click();
				}else {
					elementList.get(0).click();
				}
				Generic.clickByXPath(driver, "//div[7]/div/div[1]/div[2]/input");
			}
		}		
	}
	
	private static void cliccaRicercaGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori() == null) {
			Generic.clickById(driver, "searchnow");
			Thread.sleep(3000);
		}
	}
	
	private static void chiudiPopupPag2Grimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null) {
			Generic.clickByXPath(driver, "/html/body/div[11]/div/div[3]/button");
		}
	}
	
	private static void gestioneVeicoloGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if (sito.getErrori() == null && !sito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickById(driver, "carLeg1Select");
			//Mirko
			Thread.sleep(1000);
			//Mirko
			Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[2]/b");
			if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
//				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
//				if(elements!=null) {
//				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Veicolo')]");
//				Thread.sleep(1000);
//				Generic.clickByXPath(driver, "//tr[@id=\"setVehLH\"]/td[1]/div[5]/div[1]");
//				Thread.sleep(1000);
////				Generic.clickByXPath(driver, "//input[@id='mtlCar']");
//				Generic.sendKeysByXPath(driver, "//input[@id='mtlCar']", "550");
//				Generic.clickById(driver, "createcar");
//				}else {
//					System.out.println(sito.getDatiCsv().getVeicolo() + "non disponibile per questa tratta");
//					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
//				}
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				//Mirko
				Thread.sleep(1000);
				Generic.sendKeysById(driver, "mtlCar", "501");
				Thread.sleep(1000);
				//Mirko
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Camper')]");
				//Mirko
				Thread.sleep(1000);
				Generic.sendKeysById(driver, "mtlCar", "75");
				Thread.sleep(1000);
				//Mirko
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
				ArrayList<WebElement> elements = Generic.getElementListByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				if(elements!=null) {
				Generic.clickByXPath(driver, "//div[@id='carBox']/div/div[3]/div/ul/li[contains(text(),'Moto')]");
				Generic.clickById(driver, "createcar");
				}else {
					System.out.println(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
					sito.setErrori(sito.getDatiCsv().getVeicolo() + " non disponibile per questa tratta");
				}
			} 
		}
	}
}
