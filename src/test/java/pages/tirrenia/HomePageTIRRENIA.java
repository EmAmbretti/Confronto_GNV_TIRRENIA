package pages.tirrenia;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class HomePageTIRRENIA {


	public static void inserisciDatiTirrenia(WebDriver driver, EsitoSito esito, CSVData sito) throws Throwable {
		if(esito.getErrori() == null) {
			///////
			System.out.println("Inizio inserimento dati tirrenia");
			//////
			Thread.sleep(2000);

			//scorriCollegamento(driver, sito, esito);
			checkboxTirrenia(driver, esito);
			controlloCollegamentoTirrenia(driver, sito, esito);
			selezionaAndataTirrenia(driver, esito, sito);
			cliccaCalendario(driver, esito);
			selezionaMeseTirrenia(driver, sito, esito);
			selezionaGiornoTirrenia(driver, sito, esito);

			selezionaPasseggeriTirrenia(driver, sito, esito);

			gestioneVeicolo(driver, sito.getVeicolo());
			Thread.sleep(2000);	
			cliccaCercaTirrenia(driver, sito, esito);
		}	
	}

	private static void controlloCollegamentoTirrenia(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		for(int i = 1; i <= 4; i ++) {
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_ddl_destinazioni\"]/option[" + i + "]"));
			if(element.getText().equalsIgnoreCase(sito.getCollegamento())) {
				element.click();
				Thread.sleep(2000);
				break;				
			}
			if(i == 4) {
				esito.setErrori("Collegamento non disponibile per questo sito.");
			}
		}
		Thread.sleep(1000);
	}

	private static void checkboxTirrenia(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
			Thread.sleep(1000);
		}
	}

	private static void cliccaTratteTirrenia(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickById(driver, "tratte_andata");
			Thread.sleep(2000);
		}
	}

	private static void cliccaCollegamentoTirrenia(WebDriver driver) throws Throwable {
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni"));
		Thread.sleep(1000);
	}

	private static void selezionaAndataTirrenia(WebDriver driver, EsitoSito esito, CSVData sito) throws Throwable{
		if(esito.getErrori() == null) {
			try {
				driver.findElement(By.xpath("//*[@id=\"tratte_andata\"]/optgroup/option[contains(text(),'" + sito.getTrattaAndata() + "')]")).click();
			}catch(Exception e) {
				esito.setErrori("la tratta per questo sito non è disponibile.");
			}
			Thread.sleep(2000);
		}
	}

	private static void cliccaCalendario(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			//Generic.clickById(driver, "arrival");
			Generic.clickByXPath(driver, "//*[@id=\"arrival\"]");
			Thread.sleep(2000);
		}
	}

	private static void selezionaMeseTirrenia(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		if(esito.getErrori() == null) {
			for(int i = 1; i <= 12; i ++) {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]/option[" + i + "]"));
				if(element.getText().equalsIgnoreCase(sito.getMeseAndata())) {
					try {
						element.click();
					}catch(Exception e) {
						esito.setErrori("Il mese inserito non è valido.");
						System.out.println("Il mese inserito non è valido!");
					}
					break;
				}
				if(i == 12) {
					esito.setErrori("Il mese inserito non è valido.");
					System.out.println("Il mese inserito non è valido!");
				}
			}
			Thread.sleep(2000);
		}
	}

	private static void selezionaGiornoTirrenia(WebDriver driver,CSVData sito, EsitoSito esito) throws Throwable{
		if(esito.getErrori()==null) {
			boolean controllo = false;
			for(int i = 1; i <= 6; i ++) {
				for(int j = 1; j <= 7; j ++) {
					WebElement element=driver.findElement(By.xpath("//*[@id=\"arrival_table\"]/tbody/tr[" + i + "]/td[" + j + "]/div"));
					if(element.getText().equals(sito.getGiornoAndata())&&(Integer.valueOf(sito.getGiornoAndata())<20||i>1)) {
						Thread.sleep(2000);
						try {
							controllo=true;
							element.click();
						}catch(Exception e) {
							esito.setErrori("Il giorno inserito non è disponibile per questo sito.");
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

		Generic.clickByXPath(driver, "//*[@id=\"input_VeicoliAndata\"]");
		if(veicolo.equalsIgnoreCase("No")) {
			Generic.clickByXPath(driver, "//a[1]/div/label");
		}else if(veicolo.equalsIgnoreCase("Car")||veicolo.equalsIgnoreCase("Car5m")) {
			Generic.clickByXPath(driver, "//a[2]/div/label");
			Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]");
			Thread.sleep(1000);
			if(veicolo.equalsIgnoreCase("Car")){
				Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]/option[contains(text(),'fiat')]");
				Generic.clickById(driver, "select_ModelloVeicoloAndata");
				Generic.clickByXPath(driver, "//*[@id=\"select_ModelloVeicoloAndata\"]/option[contains(text(),'panda (2013-)')]");
			}else if(veicolo.equalsIgnoreCase("Car5m")) {
				Generic.clickByXPath(driver, "//*[@id=\"select_MarcaVeicoloAndata\"]/option[contains(text(),'audi')]");
				Generic.clickById(driver, "select_ModelloVeicoloAndata");
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

	public static void cliccaCercaTirrenia(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if (esito.getErrori() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_Button_Cerca\"]");
		}	
	}

	private static void scorriCollegamento(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		List<WebElement> listaCollegamento = driver.findElements(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_ddl_destinazioni\"]/option"));
		cliccaCollegamentoTirrenia(driver);
		for (int i = 1; i < listaCollegamento.size(); i ++) {
			listaCollegamento.get(i).click();
			cliccaTratteTirrenia(driver, sito, esito);
			try {
				driver.findElement(By.xpath("//*[@id=\"tratte_andata\"]/optgroup/option[contains(text(),'"+sito.getTrattaAndata()+"')]")).click();
			}catch(Exception e) {
				if (i == listaCollegamento.size() - 1) {
					esito.setErrori("la tratta per questo sito non è disponibile.");
				}	
			}
			Thread.sleep(500);	
		}
	}

	private static void cliccaTratteTirrenia(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickById(driver, "tratte_andata");
			Thread.sleep(2000);
		}
	}

	public static void selezionaPasseggeriTirrenia(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Generic.clickByXPath(driver, "//*[@id=\"input_NumeroPaxAndata\"]");
			Generic.clickByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]");
			Thread.sleep(2000);
			selezionaAdultiTirrenia(driver, sito.getPasseggeriAdulti());
			Thread.sleep(2000);
			Generic.clickById(driver, "select_NumeroPasseggeriAndata");
			selezionaBambiniTirrenia(driver, sito.getPasseggeriBambini());
			Generic.clickById(driver, "select_NumeroAnimaliAndata");
			Thread.sleep(2000);
			selezionaAnimaliTirrenia(driver, sito.getPasseggeriAnimali());
			Thread.sleep(2000);
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button");
			Thread.sleep(2000);
		}	
	}

	public static void bypassFrame(WebDriver driver) throws Throwable {
		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
		Thread.sleep(3000);
	}
}
