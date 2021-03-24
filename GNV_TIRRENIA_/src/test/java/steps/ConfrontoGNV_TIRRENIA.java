package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.Recap;
import pages.RecuperaImporto;
import utils.BeforeAndAfter;
import utils.Generic;


public class ConfrontoGNV_TIRRENIA {
	
	double importoNumerico, prezzoTirreniaNumerico;
	WebDriver driver = BeforeAndAfter.driver;
	
	@Given("^utente apre browser GNV$")
	public void utente_apre_browser_GNV() throws Throwable {
		Generic.utente_apre_browser(driver);
	}

	@When("^utente seleziona viaggio$")
	public void utente_seleziona_destinazioni() throws Throwable {
		if(driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"iubenda-cs-banner\"]/div/div/div/div[2]/div[2]/button[2]")).click();
		}
		Thread.sleep(3000);
	 
	}

	@When("^utente compila campi$")
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
	    HomePage.cliccaFrecciaAvanti(driver);
	    Thread.sleep(1000);
	    HomePage.cliccaFrecciaAvanti(driver);
	    Thread.sleep(1000);
	    HomePage.cliccaFrecciaAvanti(driver);
	    Thread.sleep(1000);
	    HomePage.cliccaFrecciaAvanti(driver);
	    Thread.sleep(1000);
	    HomePage.cliccaDataScelta(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaContinua(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaTastoPiu(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaContinua(driver);
	    Thread.sleep(3000);
	    HomePage.cliccaTastoCerca(driver);
	    Thread.sleep(3000);
	}

	@When("^utente seleziona sistemazione$")
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

	@When("^recupero totale offerta$")
	public void recupero_totale_offerta() throws Throwable {
	    String importo = RecuperaImporto.recuperaImporto(driver);
	    System.out.println("L'importo dell'offerta scelta è: " + importo);
	    importoNumerico = Double.valueOf(importo.substring(0, importo.length()-2).replace(",", "."));
	    System.out.println("L'importo dell'offerta scelta (di tipo double) è: " + importoNumerico);
	}
	
	@When("^utente chiude browser$")
	public void utente_chiude_browser() throws Throwable {
		Thread.sleep(1000);
		
	}
	
	@When("^utente apre browser TIRRENIA$")
	public void utente_apre_browser_TIRRENIA() throws Throwable {
		System.out.println("Opening URL");
		driver.get("https://www.tirrenia-prenotazioni.it/");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
	}
	
	@When("^utente inserisce dati viaggio TIRRENIA$")
	public void utente_inserisce_dati_viaggio() throws Throwable{
		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]")).click();
		driver.findElement(By.id("tratte_andata")).click();
		driver.findElement(By.id("tratte_andata")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("tratte_andata")).sendKeys(Keys.DOWN);
		driver.findElement(By.id("tratte_andata")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("arrival")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"arrival_table\"]/tbody/tr[1]/td[7]/div")).click();
		driver.findElement(By.id("input_NumeroPaxAndata")).click();
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAdultiTipo2Andata\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAdultiTipo2Andata\"]")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"select_NumeroAdultiTipo2Andata\"]")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button")).click();
		driver.findElement(By.id("input_VeicoliAndata")).click();
		driver.findElement(By.xpath("//*[@id=\"a_\"]/div/label")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_motore_motore_verticale\"]/div/div[2]/div[4]/div[17]/button")).click();
		driver.findElement(By.id("ContentPlaceHolder1_motore_Button_Cerca")).click();
	}
	
	@When("^controllo prezzo TIRRENIA$")
	public void controllo_prezzo_Tirrenia() throws Throwable{
		String mainWindow = driver.getWindowHandle();
		System.out.println("Actual Window: "+driver.getWindowHandle());
		new WebDriverWait(driver,10).until(ExpectedConditions.numberOfWindowsToBe(2));
		for(String existingWindows : driver.getWindowHandles()){
			System.out.println("Inspected Window: "+existingWindows);
			Thread.sleep(2000);
			if(!mainWindow.equals(existingWindows)) {
		    driver.switchTo().window(existingWindows);
			System.out.println("Actual Window: "+driver.getWindowHandle());
		    Thread.sleep(2000);
		    break;
			}
		}
		
		driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div")).click();
        driver.findElement(By.id("ContentPlaceHolder1_LinkButton_Avanti")).click();
		Thread.sleep(3000);
		String prezzoTirrenia = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Step_Andata_Panel_PrezzoTotale")).getText();
		System.out.println("Prezzo stringa: "+prezzoTirrenia);
		prezzoTirreniaNumerico=Double.parseDouble(prezzoTirrenia.substring(0,prezzoTirrenia.length()-2).replace(",", "."));
		System.out.println("Prezzo numerico TIRRENIA: "+prezzoTirreniaNumerico);
	}
	
	@Then("^confronto prezzi$")
	public void confrontoPrezzi() {
		if(importoNumerico > prezzoTirreniaNumerico) {
			System.out.println("L'importo minore è: " + prezzoTirreniaNumerico + " del sito TIRRENIA." );
		} else if (prezzoTirreniaNumerico > importoNumerico) {
			System.out.println("L'importo minore è: " + importoNumerico + " del sito GNV.");
		} else {
			System.out.println("Gli importi, dei due siti, sono uguali.");
		}
		
	}

}
