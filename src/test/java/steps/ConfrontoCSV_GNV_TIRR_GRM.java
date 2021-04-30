package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.CSVData;
import model.EsitoSito;
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
	CSVData testData=CSVExtractor.getTestDataByOffer("TestCase5", Path.PATH);
	String nomeTest = testData.getTipologia();
	EsitoSito sitoGNV = new EsitoSito("GNV",testData);
	EsitoSito	sitoTIRRENIA = new EsitoSito("TIRRENIA",testData);
	EsitoSito	sitoGRIMALDI = new EsitoSito("GRIMALDI",testData);
	
	@Given("^utente apre browser GNV GNV_TIRR_GRM$")
	public void utente_apre_browser_GNV() throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.gnv.it/it", sitoGNV.getSito());
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
		HomePage.selezionaTrattaGNV(driver, testData, sitoGNV);
		HomePage.cliccaContinua(driver, testData, sitoGNV);
		HomePage.controlloMese(driver, testData, sitoGNV); 
		HomePage.cliccaDataScelta(driver, testData, sitoGNV);
		HomePage.cliccaContinua(driver, testData, sitoGNV);
		HomePage.inserisciPasseggeriGNV(driver, testData, sitoGNV);
		HomePage.cliccaContinua(driver, testData, sitoGNV);
		HomePage.cliccaTastoCerca(driver, testData, sitoGNV);

	}

	@When("^utente seleziona sistemazione GNV_TIRR_GRM$")
	public void utente_seleziona_sistemazione() throws Throwable {
		Recap.selezionaSistemazione(driver, testData, sitoGNV);
		Recap.cliccaTastoContinua(driver, sitoGNV);
		Recap.cliccaTastoContinuaSenzaServizi(driver, sitoGNV);
		Recap.cliccaTastoContinuaSenzaAssicurazione(driver, sitoGNV);
		
	}

	@When("^recupero totale offerta GNV_TIRR_GRM$")
	public void recupero_totale_offerta() throws Throwable {
	    RecuperaImporto.recuperaImportoGNV(driver, sitoGNV);
	    if(sitoGNV.getErrori()==null) {
	    	System.out.println("Prezzo sito GNV: " + sitoGNV.getPrezzo());
	    	prezzoGNVNumerico = Double.valueOf(sitoGNV.getPrezzo().substring(0, sitoGNV.getPrezzo().length()-2).replace(",", "."));
	    }   
	}
	
	@When("^utente apre browser TIRR GNV_TIRR_GRM$")
	public void utente_apre_browser_TIRRENIA() throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.tirrenia-prenotazioni.it", sitoTIRRENIA.getSito());
		
	}
	
	@When("^utente bypassa frame TIRR GNV_TIRR_GRM$")
	public void utente_bypassa_frame() throws Throwable {
		HomePage.bypassFrame(driver);	
	}
	
	@When("^utente inserisce dati viaggio TIRR GNV_TIRR_GRM$")
	public void utente_inserisce_dati_viaggio() throws Throwable {
		HomePage.checkboxTirrenia(driver, testData, sitoTIRRENIA);
		/*
		Metodo per scorrimento tratte => ideato per eliminare la colonna COLLEGAMENTO dal CSV utile solo per TIRRENIA.
		HomePage.scorriCollegamento(driver, sitoTIRRENIA);
		*/
		HomePage.cliccaCollegamentoTirrenia(driver);
		HomePage.controlloCollegamentoTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.cliccaTratteTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.selezionaAndataTirrenia(driver, testData, sitoTIRRENIA);
		// lo snippet di cui sopra è possibile eliminarlo decommentando il metodo precedente ad esso.
		HomePage.cliccaCalendario(driver, testData, sitoTIRRENIA);
		HomePage.selezionaMeseTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.selezionaGiornoTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.selezionaPasseggeriTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.formVeicoloTirrenia(driver, testData, sitoTIRRENIA);
		HomePage.cliccaCercaTirrenia(driver, testData, sitoTIRRENIA);
	}
	
	@When("^recupera prezzo TIRR GNV_TIRR_GRM$")
	public void recupera_prezzo_Tirrenia() throws Throwable {
		Recap.switchPage(driver,sitoTIRRENIA);
		Recap.controlloDisponibilitaFinaleTirrenia(driver, sitoTIRRENIA);
		Recap.controlloDisponibilitaSistemazioneTirrenia(driver, sitoTIRRENIA);
		RecuperaImporto.recuperaImportoTIRRENIA(driver, sitoTIRRENIA);
		if(sitoTIRRENIA.getErrori()==null) {
			System.out.println("Prezzo sito TIRRENIA: " + sitoTIRRENIA.getPrezzo());
			prezzoTirreniaNumerico=Double.parseDouble(sitoTIRRENIA.getPrezzo().substring(0,sitoTIRRENIA.getPrezzo().length()-2).replace(",", "."));
		}
	}
	
	@When("^utente apre browser GRM GNV_TIRR_GRM$")
	public void utente_apre_browser_GRM_GNV_TIRR_GRM() throws Throwable {
		Generic.utente_apre_browser(driver, "https://www.grimaldi-lines.com/it", sitoGRIMALDI.getSito());
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
		HomePage.selezionaAndataGrimaldi(driver, testData, sitoGRIMALDI);	
		HomePage.cliccaSuDataGrimaldi(driver, sitoGRIMALDI);
		HomePage.prenotaOraGrimaldi(driver, sitoGRIMALDI);
		Recap.switchPage(driver,sitoGRIMALDI);
		HomePage.chiudiPopupPag2Grimaldi(driver, sitoGRIMALDI);
		HomePage.scegliDataGrimaldi(driver, testData, sitoGRIMALDI);
		HomePage.aggiungiSistemazioniPasseggeriGrimaldi(driver, testData, sitoGRIMALDI);
		HomePage.selezionaAnimaliGrimaldi(driver, testData, sitoGRIMALDI);
		HomePage.cliccaRicercaGrimaldi(driver, sitoGRIMALDI); 
	}

	@When("^recupera prezzo GRM GNV_TIRR_GRM$")
	public void recupera_prezzo_GRM_GNV_TIRR_GRM() throws Throwable {
		String prezzoGrimaldi=RecuperaImporto.recuperaImportoGrimaldi(driver, testData, sitoGRIMALDI);
		if(sitoGRIMALDI.getErrori() == null) {
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
