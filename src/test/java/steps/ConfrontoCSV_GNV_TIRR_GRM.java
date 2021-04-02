package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.CSVData;
import model.WebData;
import pages.HomePage;
import pages.Recap;
import pages.RecuperaImporto;
import utils.BeforeAndAfter;
import utils.CSVExtractor;
import utils.Generic;
import utils.Path;


public class ConfrontoCSV_GNV_TIRR_GRM {
	
	Double prezzoGNVNumerico, prezzoTirreniaNumerico, prezzoGrimaldiNumerico;
	WebDriver driver = BeforeAndAfter.driver;
	CSVData testData;
	String nomeTest = CSVExtractor.getTestDataByOffer("TestCase1", Path.PATH).getTipologia();
	WebData sitoGNV = new WebData("GNV");
	WebData	sitoTIRRENIA = new WebData("TIRRENIA");
	WebData	sitoGRIMALDI = new WebData("GRIMALDI");
	
	@Given("^utente apre browser GNV GNV_TIRR_GRM$")
	public void utente_apre_browser_GNV() throws Throwable {
		Generic.utente_apre_browser(driver);
	}

	@When("^utente chiude popup GNV_TIRR_GRM$") 
	public void utente_seleziona_destinazioni() throws Throwable {
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).click();
			Thread.sleep(5000);
		}catch(org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
		try {
			driver.findElement(By.xpath("//*[@id=\"closeXButton\"]/span/p/span")).click();
			Thread.sleep(5000);
		}catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.println("AMBIENTE NON TROVATO");
		}
	 
	}

	@When("^utente compila campi GNV_TIRR_GRM$")
	public void utente_compila_campi() throws Throwable {
		HomePage.selezionaViaggio(driver);
	    
	    HomePage.cliccaSoloAndata(driver);
	    
	    HomePage.selezionaTrattaGNV(driver, sitoGNV);
	    HomePage.cliccaContinua(driver, sitoGNV);

	    HomePage.controlloMese(driver, sitoGNV); 
 
	    HomePage.cliccaDataScelta(driver,sitoGNV);

	    HomePage.cliccaContinua(driver, sitoGNV);
 
	    HomePage.inserisciPasseggeriGNV(driver, sitoGNV);

	    HomePage.cliccaContinua(driver, sitoGNV);

	    HomePage.cliccaTastoCerca(driver, sitoGNV);

	}

	@When("^utente seleziona sistemazione GNV_TIRR_GRM$")
	public void utente_seleziona_sistemazione() throws Throwable {

		Recap.selezionaSistemazione(driver, sitoGNV);
		
		Recap.cliccaTastoContinua(driver, sitoGNV);
		
		Recap.cliccaTastoContinuaSenzaServizi(driver, sitoGNV);
		
		Recap.cliccaTastoContinuaSenzaAssicurazione(driver, sitoGNV);
		
		
	}

	@When("^recupero totale offerta GNV_TIRR_GRM$")
	public void recupero_totale_offerta() throws Throwable {
	    RecuperaImporto.recuperaImportoGNV(driver, sitoGNV);
	    if(sitoGNV.getDisponibilita()==null) {
	    	System.out.println("Prezzo sito GNV: " + sitoGNV.getPrezzo());
	    	prezzoGNVNumerico = Double.valueOf(sitoGNV.getPrezzo().substring(0, sitoGNV.getPrezzo().length()-2).replace(",", "."));
	    }   
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
		HomePage.controlloTratta(driver, sitoTIRRENIA);
		Thread.sleep(2000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		Generic.clickById(driver, "tratte_andata");
		Thread.sleep(2000);
		HomePage.selezionaAndataTirrenia(driver, sitoTIRRENIA);
		Thread.sleep(2000);
		Generic.clickById(driver, "arrival");
		Thread.sleep(2000);
		HomePage.selezionaMeseTirrenia(driver, sitoTIRRENIA);
		Thread.sleep(2000);		
		HomePage.selezionaGiornoTirrenia(driver, sitoTIRRENIA);
		Thread.sleep(2000);
		HomePage.selezionaPasseggeriTirrenia(driver, sitoTIRRENIA);
		HomePage.formVeicoloTirrenia(driver, sitoTIRRENIA);
		HomePage.cliccaCercaTirrenia(driver, sitoTIRRENIA);
	}
	
	@When("^recupera prezzo TIRR GNV_TIRR_GRM$")
	public void recupera_prezzo_Tirrenia() throws Throwable{
		Recap.switchPage(driver,sitoTIRRENIA);
		Recap.controlloDisponibilitaPoltrona(driver, sitoTIRRENIA);
		RecuperaImporto.recuperaImportoTIRRENIA(driver, sitoTIRRENIA);
		if(sitoTIRRENIA.getDisponibilita()==null) {
			System.out.println("Prezzo sito TIRRENIA: " + sitoTIRRENIA.getPrezzo());
			prezzoTirreniaNumerico=Double.parseDouble(sitoTIRRENIA.getPrezzo().substring(0,sitoTIRRENIA.getPrezzo().length()-2).replace(",", "."));
		}
	}
	
	@When("^utente apre browser GRM GNV_TIRR_GRM$")
	public void utente_apre_browser_GRM_GNV_TIRR_GRM() throws Throwable {
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
		HomePage.selezionaAndataGrimaldi(driver, sitoGRIMALDI);	
		HomePage.cliccaSuDataGrimaldi(driver, sitoGRIMALDI);
		HomePage.prenotaOraGrimaldi(driver, sitoGRIMALDI);
		Recap.switchPage(driver,sitoGRIMALDI);
		HomePage.chiudiPopupPag2Grimaldi(driver, sitoGRIMALDI);
		HomePage.scegliDataGrimaldi(driver, sitoGRIMALDI);
		HomePage.aggiungiSistemazioniPasseggeriGrimaldi(driver, sitoGRIMALDI);
		HomePage.selezionaAnimaliGrimaldi(driver, sitoGRIMALDI);
		HomePage.cliccaRicercaGrimaldi(driver, sitoGRIMALDI);
	}

	@When("^recupera prezzo GRM GNV_TIRR_GRM$")
	public void recupera_prezzo_GRM_GNV_TIRR_GRM() throws Throwable {
		String prezzoGrimaldi=RecuperaImporto.recuperaImportoGrimaldi(driver, sitoGRIMALDI);
		if(sitoGRIMALDI.getDisponibilita() == null) {
			System.out.println("Prezzo sito GRIMALDI: " + prezzoGrimaldi);
			prezzoGrimaldiNumerico=Double.parseDouble(prezzoGrimaldi.substring(1).replace(",", "."));
		}
		
	}
	
	@Then("^confronto prezzi GNV_TIRR_GRM$")
	public void confrontoPrezzi() {
		Double prezzoMigliore=Generic.confrontoPrezzi(driver, prezzoGNVNumerico, sitoGNV.getSito(), prezzoTirreniaNumerico, sitoTIRRENIA.getSito(), prezzoGrimaldiNumerico, sitoGRIMALDI.getSito());
		Generic.generaFileTxt(nomeTest,sitoGNV, sitoTIRRENIA, sitoGRIMALDI, prezzoTirreniaNumerico, prezzoGNVNumerico,prezzoGrimaldiNumerico, prezzoMigliore);
		driver.quit();
	}
}
