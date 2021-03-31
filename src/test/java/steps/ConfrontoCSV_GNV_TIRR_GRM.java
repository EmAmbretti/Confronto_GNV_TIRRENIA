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


public class ConfrontoCSV_GNV_TIRR_GRM {
	
	Double importoNumerico, prezzoTirreniaNumerico;
	WebDriver driver = BeforeAndAfter.driver;
	CSVData testData;
	
	@Given("^utente apre browser GNV GNV_TIRR_GRM$")
	public void utente_apre_browser_GNV() throws Throwable {
		testData=CSVExtractor.getTestDataByOffer("GNVTIRR1PNAPPAL", Path.PATH);
		Generic.utente_apre_browser(driver);
	}

	@When("^utente chiude popup GNV_TIRR_GRM$") 
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

	@When("^utente compila campi GNV_TIRR_GRM$")
	public void utente_compila_campi() throws Throwable {
		HomePage.selezionaViaggio(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaSoloAndata(driver);
	    Thread.sleep(3000);
//	    HomePage.selezionaPartenza(driver);
//	    Thread.sleep(3000);
//	    HomePage.selezionaDestinazione(driver);
//	    Thread.sleep(3000);
	    
	    HomePage.selezionaTrattaGNV(driver, testData.getTrattaAndata());
	    
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

	@When("^utente seleziona sistemazione GNV_TIRR_GRM$")
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

	@When("^recupero totale offerta GNV_TIRR_GRM$")
	public void recupero_totale_offerta() throws Throwable {
	    String importo = RecuperaImporto.recuperaImporto(driver);
	    System.out.println("Prezzo sito GNV: " + importo);
	    importoNumerico = Double.valueOf(importo.substring(0, importo.length()-2).replace(",", "."));
	    //System.out.println("L'importo dell'offerta scelta (di tipo double) è: " + importoNumerico);
	}
	
	@When("^utente chiude browser GNV_TIRR_GRM$")
	public void utente_chiude_browser() throws Throwable {
		Thread.sleep(1000);
	}
	
	@When("^utente apre browser TIRR GNV_TIRR_GRM$")
	public void utente_apre_browser_TIRRENIA() throws Throwable {
		System.out.println("Opening URL TIRRENIA");
		driver.get("https://www.tirrenia-prenotazioni.it/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
	}
	
	@When("^utente bypassa frame TIRR GNV_TIRR_GRM$")
	public void utente_bypassa_frame() throws Throwable {
		HomePage.bypassFrame(driver);
		Thread.sleep(3000);
	}
	
	@When("^utente inserisce dati viaggio TIRR GNV_TIRR_GRM$")
	public void utente_inserisce_dati_viaggio() throws Throwable {
		HomePage.cliccaTratte(driver);
		Thread.sleep(1000);
		HomePage.controlloTratta(driver, testData.getCollegamento());
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		Generic.clickById(driver, "tratte_andata");
		Thread.sleep(2000);
		HomePage.selezionaAndataTirrenia(driver, testData.getTrattaAndata());
		Thread.sleep(2000);
		Generic.clickById(driver, "arrival");
		Thread.sleep(2000);
		HomePage.selezionaMeseTirrenia(driver, testData.getMeseAndata());
		Thread.sleep(2000);
		HomePage.selezionaGiornoTirrenia(driver, testData.getGiornoAndata());
		Generic.clickById(driver, "input_NumeroPaxAndata");
		Generic.clickByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]");
		Thread.sleep(2000);
		HomePage.selezionaAdultiTirrenia(driver, testData.getPasseggeriAdulti());
		Thread.sleep(2000);
		Generic.clickById(driver, "select_NumeroPasseggeriAndata");
		HomePage.selezionaBambiniTirrenia(driver, testData.getPasseggeriBambini());
		Generic.clickById(driver, "select_NumeroAnimaliAndata");
		Thread.sleep(2000);
		HomePage.selezionaAnimaliTirrenia(driver, testData.getPasseggeriAnimali());
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button");
		Thread.sleep(2000);
		Generic.clickById(driver, "input_VeicoliAndata");
		Thread.sleep(2000);
		HomePage.selezionaVeicoloTirrenia(driver, testData.getVeicolo());
		Thread.sleep(2000); 
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[17]/button");
		Thread.sleep(2000);
		Generic.clickById(driver, "ContentPlaceHolder1_motore_Button_Cerca");
	}
	
	@When("^recupera prezzo TIRR GNV_TIRR_GRM$")
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
	
	@When("^utente apre browser GRM GNV_TIRR_GRM$")
	public void utente_apre_browser_GRM_GNV_TIRR_GRM() throws Throwable {
		////////////////////////////////// DA ELIMINARE RIGO SEGUENTE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
		testData=CSVExtractor.getTestDataByOffer("GNVTIRR1PNAPPAL", Path.PATH);
		System.out.println("Opening URL GRIMALDI");
		driver.get("https://www.grimaldi-lines.com/it/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}

	@When("^utente bypassa frame GRM GNV_TIRR_GRM$")
	public void utente_bypassa_frame_GRM_GNV_TIRR_GRM() throws Throwable {
		if(driver.findElement(By.xpath("//*[@id=\"top\"]/div[3]/div/div[3]/button")).isDisplayed()) {
			Generic.clickByXPath(driver, "//*[@id=\"top\"]/div[3]/div/div[3]/button");
		}
		
	}

	@When("^utente inserisce dati viaggio GRM GNV_TIRR_GRM$")
	public void utente_inserisce_dati_viaggio_GRM_GNV_TIRR_GRM() throws Throwable {
		HomePage.cliccaSoloAndataGrimaldi(driver);
		Generic.clickById(driver, "start-route");
		HomePage.selezionaAndataGrimaldi(driver, testData.getTrattaAndata());
		driver.findElement(By.id("start-date")).click();
		Generic.clickById(driver, "confirmRouteForm");
		Thread.sleep(2000);
		Recap.switchPage(driver);
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "/html/body/div[11]/div/div[3]/button");
		Generic.clickById(driver, "dateLeg1");
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"ui-datepicker-div\"]/div/div/select[1]");
		HomePage.selezionaMeseGrimaldi(driver, testData.getMeseAndata());
		Thread.sleep(1000);
		HomePage.selezionaGiornoGimaldi(driver, testData.getGiornoAndata());
		Thread.sleep(5000);
		Generic.clickById(driver, "accLeg1Select");
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"accBox\"]/div/div[2]/span");
		// il codice di sotto non è dinamico
		HomePage.selezionaSistemazioneGrimaldi(driver);
		HomePage.inseriscoPasseggeriGrimaldi(driver, testData.getPasseggeriAdulti(), testData.getPasseggeriBambini());
		Generic.clickById(driver, "createacc");
		HomePage.selezionaAnimaliGrimaldi(driver, testData.getPasseggeriAnimali());
		Generic.clickById(driver, "searchnow");
		//*[@id="leg1-CVVOLB202108052245"]/a/div[3]
		//*[@id="leg1-CVVOLB202108062245"]/a/div[1]
		//*[@id="leg1-CVVOLB202108062245"]/a/div[3]
	}

	@When("^recupera prezzo GRM GNV_TIRR_GRM$")
	public void recupera_prezzo_GRM_GNV_TIRR_GRM() throws Throwable {
		RecuperaImporto.recuperaImportoGrimaldi(driver, testData.getGiornoAndata(), testData.getMeseAndata());
	}
	
	@Then("^confronto prezzi GNV_TIRR_GRM$")
	public void confrontoPrezzi() {
		Double prezzoMigliore=Generic.confrontoPrezzi(driver, importoNumerico, "GNV", prezzoTirreniaNumerico, "TIRRENIA");
		Generic.generaFileTxt(testData.getTipologia(), testData.getTrattaAndata(), testData.getMeseAndata(), testData.getGiornoAndata(), testData.getPasseggeriAdulti(), testData.getPasseggeriBambini(), testData.getPasseggeriAnimali(), testData.getVeicolo(), prezzoTirreniaNumerico, importoNumerico, prezzoMigliore);
		driver.quit();
		
	}
}
