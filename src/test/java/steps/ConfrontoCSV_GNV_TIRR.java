package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.Recap;
import pages.RecuperaImporto;
import utils.BeforeAndAfter;
import utils.CSVData;
import utils.CSVExtractor;
import utils.Generic;
import utils.Path;


public class ConfrontoCSV_GNV_TIRR {
	
	Double importoNumerico, prezzoTirreniaNumerico;
	WebDriver driver = BeforeAndAfter.driver;
	CSVData testData;
	
	@Given("^utente apre browser GNV GNV_TIRR$")
	public void utente_apre_browser_GNV() throws Throwable {
		testData=CSVExtractor.getTestDataByOffer("GNVTIRR1PNAPPAL", Path.PATH);
		Generic.utente_apre_browser(driver);
	}

	@When("^utente chiude popup GNV_TIRR$") 
	public void utente_seleziona_destinazioni() throws Throwable {
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).click();
			Thread.sleep(5000);
		}
		if(driver.findElement(By.xpath("//*[@id=\"closeXButton\"]/span/p/span")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"closeXButton\"]/span/p/span")).click();
			Thread.sleep(5000);
		}
	 
	}

	@When("^utente compila campi GNV_TIRR$")
	public void utente_compila_campi() throws Throwable {
		HomePage.selezionaViaggio(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaSoloAndata(driver);
	    Thread.sleep(3000);
	    HomePage.selezionaPartenza(driver);
	    Thread.sleep(3000);
	    HomePage.selezionaDestinazione(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaContinua(driver);
	    Thread.sleep(3000);
	    HomePage.controlloMese(driver, testData.getMeseAndata()); 
	    Thread.sleep(3000); 
	    HomePage.cliccaDataScelta(driver,testData.getGiornoAndata());
	    Thread.sleep(3000);
	    HomePage.cliccaContinua(driver);
	    Thread.sleep(3000); 
	    HomePage.inserisciPersone(driver, testData.getPasseggeriAdulti(), testData.getPasseggeriBambini(), testData.getPasseggeriAnimali());
	    Thread.sleep(3000);
	    HomePage.cliccaContinua(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaTastoCerca(driver);
	    Thread.sleep(3000);
	}

	@When("^utente seleziona sistemazione GNV_TIRR$")
	public void utente_seleziona_sistemazione() throws Throwable {
		Thread.sleep(5000);
		Recap.selezionaSistemazione(driver);
		Thread.sleep(3000);
		Recap.cliccaTastoContinua(driver);
		Thread.sleep(3000);
		Recap.cliccaTastoContinuaSenzaServizi(driver);
		Thread.sleep(3000);
		Recap.cliccaTastoContinuaSenzaAssicurazione(driver);
		Thread.sleep(3000);
		
	}

	@When("^recupero totale offerta GNV_TIRR$")
	public void recupero_totale_offerta() throws Throwable {
	    String importo = RecuperaImporto.recuperaImporto(driver);
	    System.out.println("Prezzo sito GNV: " + importo);
	    importoNumerico = Double.valueOf(importo.substring(0, importo.length()-2).replace(",", "."));
	    //System.out.println("L'importo dell'offerta scelta (di tipo double) è: " + importoNumerico);
	}
	
	@When("^utente chiude browser GNV_TIRR$")
	public void utente_chiude_browser() throws Throwable {
		Thread.sleep(1000);
	}
	
	@When("^utente apre browser TIRR GNV_TIRR$")
	public void utente_apre_browser_TIRRENIA() throws Throwable {
		System.out.println("Opening URL TIRRENIA");
		driver.get("https://www.tirrenia-prenotazioni.it/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
	}
	
	@When("^utente bypassa frame GNV_TIRR$")
	public void utente_bypassa_frame() throws Throwable {
		HomePage.bypassFrame(driver);
		Thread.sleep(3000);
	}
	
	@When("^utente inserisce dati viaggio GNV_TIRR$")
	public void utente_inserisce_dati_viaggio() throws Throwable {
		//HomePage.scrollDropListById(driver, "ContentPlaceHolder1_motore_ddl_destinazioni", 3);
		//HomePage.clickEnterDropListById(driver, "ContentPlaceHolder1_motore_ddl_destinazioni");
		
		HomePage.cliccaTratte(driver);
		Thread.sleep(1000);
		HomePage.controlloTratta(driver, testData.getCollegamento());
		Thread.sleep(2000);
		
		/////////////////////////////////////////////////////////////////////////////////////////
		
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		Generic.clickById(driver, "tratte_andata");
		Thread.sleep(2000);
		HomePage.selezionaAndataTirrenia(driver, testData.getTrattaAndata());
		Thread.sleep(2000);
		Generic.clickById(driver, "arrival");
		Thread.sleep(2000);
		HomePage.selezionaMeseTirrenia(driver, testData.getMeseAndata());
		Thread.sleep(2000);
		
		//Generic.clickByXPath(driver, "//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]");
		//*[@id="arrival_root"]/div/div/div/div/div[1]/select[2]/option[1]
		//*[@id="arrival_root"]/div/div/div/div/div[1]/select[2]/option[2]
		
		
		HomePage.scrollDropListByXPath(driver, "//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]", 5);
		HomePage.clickEnterDropListByXPath(driver, "//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]");
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"arrival_table\"]/tbody/tr[1]/td[7]/div");
		Generic.clickById(driver, "input_NumeroPaxAndata");
		Generic.clickByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]");
		Thread.sleep(2000);
		HomePage.scrollDropListByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]", 1);
		HomePage.clickEnterDropListByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]");
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button");
		Generic.clickById(driver, "input_VeicoliAndata");
		Generic.clickByXPath(driver, "//*[@id=\"a_\"]/div/label");
		Thread.sleep(2000); 
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[17]/button");
		Generic.clickById(driver, "ContentPlaceHolder1_motore_Button_Cerca");
	}
	
	@When("^recupera prezzo GNV_TIRR$")
	public void recupera_prezzo_Tirrenia() throws Throwable{
		Recap.switchPage(driver);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div");
        Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
		Thread.sleep(3000);
		String prezzoTirrenia = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Step_Andata_Panel_PrezzoTotale")).getText();
		System.out.println("Prezzo sito TIRRENIA: " + prezzoTirrenia);
		prezzoTirreniaNumerico=Double.parseDouble(prezzoTirrenia.substring(0,prezzoTirrenia.length()-2).replace(",", "."));
		//System.out.println("Prezzo numerico TIRRENIA: "+prezzoTirreniaNumerico);
	}
	
	@Then("^confronto prezzi GNV_TIRR$")
	public void confrontoPrezzi() {
		Generic.confrontoPrezzi(driver, importoNumerico, "GNV", prezzoTirreniaNumerico, "TIRRENIA");
		driver.quit();
		
	}
	//*[@id="eta_0_Andata"]
	//*[@id="eta_1_Andata"]
	//*[@id="ContentPlaceHolder1_motore_ddl_destinazioni"]/option[1]
	//*[@id="ContentPlaceHolder1_motore_ddl_destinazioni"]/option[2]
}
