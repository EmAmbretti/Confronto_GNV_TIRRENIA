package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.WebData;
import utils.Generic;

public class HomePageTIRRENIA {

	public static void inserisciDatiTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			///////
			System.out.println("Inizio inserimento dati tirrenia");
			//////
			controlloCollegamentoTirrenia(driver, sito);
			checkboxTirrenia(driver, sito);
			cliccaTratteTirrenia(driver, sito);
			cliccaCollegamentoTirrenia(driver);
			selezionaAndataTirrenia(driver, sito);
			cliccaCalendario(driver, sito);
			selezionaMeseTirrenia(driver, sito);
			selezionaGiornoTirrenia(driver, sito);
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
			gestioneVeicolo(driver, sito.getVeicolo());
			Thread.sleep(2000);	
			cliccaCercaTirrenia(driver, sito);
		}	
	}
	
	private static void controlloCollegamentoTirrenia(WebDriver driver, WebData sito) throws Throwable {
		for(int i = 1; i <= 4; i ++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_ddl_destinazioni\"]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(sito.getCollegamento())) {
				element.click();
				Thread.sleep(2000);
				break;				
			}
			if(i == 4) {
				sito.setDisponibilita("Collegamento non disponibile per questo sito.");
			}
		}
	}
	
	private static void checkboxTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		}
	}
	
	private static void cliccaTratteTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "tratte_andata");
			Thread.sleep(2000);
		}
	}
	
	private static void cliccaCollegamentoTirrenia(WebDriver driver) throws Throwable {
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni"));
		Thread.sleep(1000);
	}
	
	private static void selezionaAndataTirrenia(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita() == null) {
			try {
				driver.findElement(By.xpath("//*[@id=\"tratte_andata\"]/optgroup/option[contains(text(),'" + sito.getTratta() + "')]")).click();
			}catch(Exception e) {
				sito.setDisponibilita("la tratta per questo sito non è disponibile.");
			}
			Thread.sleep(2000);
		}
	}
	
	private static void cliccaCalendario(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "arrival");
			Thread.sleep(2000);
		}
	}
	
	private static void selezionaMeseTirrenia(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita() == null) {
			for(int i = 1; i <= 12; i ++) {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]/option[" + i + "]"));
				if(element.getText().equalsIgnoreCase(sito.getMese())) {
					try {
						element.click();
					}catch(Exception e) {
						sito.setDisponibilita("Il mese inserito non è valido.");
						System.out.println("Il mese inserito non è valido!");
					}
					break;
				}
				if(i == 12) {
					sito.setDisponibilita("Il mese inserito non è valido.");
					System.out.println("Il mese inserito non è valido!");
				}
			}
			Thread.sleep(2000);
		}
	}
	
	private static void selezionaGiornoTirrenia(WebDriver driver,WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			boolean controllo = false;
			for(int i = 1; i <= 6; i ++) {
				for(int j = 1; j <= 7; j ++) {
					WebElement element=driver.findElement(By.xpath("//*[@id=\"arrival_table\"]/tbody/tr[" + i + "]/td[" + j + "]/div"));
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
	
	private static void gestioneVeicolo(WebDriver driver, String veicolo)throws Throwable{
		
			Generic.clickById(driver, "input_VeicoliAndata");
			if(veicolo.equalsIgnoreCase("No")) {
				Generic.clickByXPath(driver, "//a[1]/div/label");
			}else if(veicolo.equalsIgnoreCase("Car")||veicolo.equalsIgnoreCase("Car5m")) {
				Generic.clickByXPath(driver, "//a[2]/div/label");
				Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]");
				Thread.sleep(1000);
				if(veicolo.equalsIgnoreCase("Car")){
					//driver.findElement(By.xpath("//*[@id=\\\"select_MarcaVeicoloAndata\\\"]")).sendKeys("f");
					Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]/option[contains(text(),'fiat')]");
					Generic.clickById(driver, "select_ModelloVeicoloAndata");
					//driver.findElement(By.xpath("//*[@id=\"select_ModelloVeicoloAndata\"]")).sendKeys("p");
					Generic.clickByXPath(driver, "//*[@id=\"select_ModelloVeicoloAndata\"]/option[contains(text(),'panda (2013-)')]");
				}else if(veicolo.equalsIgnoreCase("Car5m")) {
					//driver.findElement(By.xpath("//*[@id=\\\"select_MarcaVeicoloAndata\\\"]")).sendKeys("a");
					Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]/option[contains(text(),'audi')]");
					Generic.clickById(driver, "select_ModelloVeicoloAndata");
					//driver.findElement(By.xpath("//*[@id=\"select_ModelloVeicoloAndata\"]")).sendKeys("q");
					Generic.clickByXPath(driver, "//*[@id=\"select_ModelloVeicoloAndata\"]/option[contains(text(),'q7 (2018-)')]");
				}
			}else if(veicolo.equalsIgnoreCase("Moto")){
				Generic.clickByXPath(driver, "//a[6]/div/label");
				Generic.clickById(driver, "select_QuantitaVeicoloAndata");
				Generic.clickByXPath(driver, "//*[@id=\"select_QuantitaVeicoloAndata\"]/option[2]");
			}else if(veicolo.equalsIgnoreCase("CMP")) {
				Generic.clickByXPath(driver, "//a[3]/div/label");
				driver.findElement(By.id("input_LunghezzaVeicoloAndata")).sendKeys("750");
				driver.findElement(By.id("input_AltezzaVeicoloAndata")).sendKeys("350");
			}
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div/button");
		}
	
	public static void cliccaCercaTirrenia(WebDriver driver, WebData sito) throws Throwable {
		if (sito.getDisponibilita() == null) {
			Generic.clickById(driver, "ContentPlaceHolder1_motore_Button_Cerca");
		}	
	}
}
