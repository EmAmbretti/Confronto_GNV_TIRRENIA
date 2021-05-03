package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.vecchio.HomePage;
import utils.BeforeAndAfter;
import utils.Generic;

public class TraghettiLines {
	
	WebDriver driver = BeforeAndAfter.driver;
	int prezzoGNV, prezzoTIRRENIA;
	int ripetizioni=0;
	int controlloPersone=0;
	
	@Given("^utente apre browser TRAGHETTILINES$")
	public void utente_apre_browser_TRAGHETTILINES() throws Throwable{
		///////////////////////
		System.out.println("Test numero: "+(ripetizioni+1));
		/////////////////////////
		driver.get("https://www.traghettilines.it");
		driver.manage().window().maximize();
	}
	@When("^utente inserisce dati viaggio TRAGHETTILINES$")
	public void utente_inserisce_dati_viaggio_TRAGHETTILINES() throws Throwable{
		Generic.clickById(driver, "ContentPlaceHolder1_bookingmapConfronto_motore_ddl_destinazioni");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_bookingmapConfronto_motore_ddl_destinazioni\"]/option[34]");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"div_CookiePolicy\"]/div/button");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_bookingmapConfronto_motore_motore_verticale\"]/div/div[2]/div[1]/div[2]/div[1]/label[2]");
		Thread.sleep(1000);
		Generic.clickById(driver, "tratte_andata");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"tratte_andata\"]/optgroup[1]/option[8]");
		Thread.sleep(1000);
		Generic.clickById(driver, "arrival");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"arrival_root\"]/div/div/div/div/div[1]/select[2]/option[8]");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"arrival_table\"]/tbody/tr[1]/td[7]/div");
		Thread.sleep(1000);
		Generic.clickById(driver, "input_NumeroPaxAndata");
		Thread.sleep(1000);
		Generic.clickById(driver, "select_NumeroAdultiTipo2Andata");
		Thread.sleep(1000);
		
		//////////////////////////
		if(controlloPersone<1) {
			HomePage.scrollDropListById(driver, "select_NumeroAdultiTipo2Andata", 1);
			controlloPersone++;
		}
		////////////////////////////
		Generic.clickByXPath(driver, "//*[@id=\"select_NumeroAdultiTipo2Andata\"]/option[2]");
		
		Thread.sleep(1000);
		HomePage.clickEnterDropListById(driver, "select_NumeroAdultiTipo2Andata");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_bookingmapConfronto_motore_motore_verticale\"]/div/div[2]/div[2]/div[3]/button");
		Thread.sleep(1000);
		Generic.clickById(driver, "input_VeicoliAndata");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"a_\"]/div/label");
		Thread.sleep(1000);
		Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_bookingmapConfronto_motore_motore_verticale\"]/div/div[2]/div[4]/div[18]/button");
		Thread.sleep(1000);
		Generic.clickById(driver, "ContentPlaceHolder1_bookingmapConfronto_motore_Button_Cerca");
		Thread.sleep(5000);
		
	}
	@When("^recupero importi TRAGHETTILINES$")
	public void recupero_importi_TRAGHETTILINES() throws Throwable{
		Generic.scrollPage(driver, "200");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		prezzoGNV=Integer.valueOf(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_0")).getText().substring(2));
		System.out.println("L'importo dell'offerta GNV è: " + prezzoGNV + " €");
		prezzoTIRRENIA=Integer.valueOf(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_1")).getText().substring(2));
		System.out.println("L'importo dell'offerta TIRRENIA è: " + prezzoTIRRENIA + " €");
		
	}
	
	@Then("^confronto prezzi TRAGHETTILINES$")
	public void confronto_prezzi_TRAGHETTILINES() throws Throwable {
		Thread.sleep(5000);
		Double imp1=Double.valueOf(prezzoGNV);
		Double imp2=Double.valueOf(prezzoTIRRENIA);
		//Generic.confrontoPrezzi(driver, imp1, "GNV", imp2, "TIRRENIA");
		
		while(ripetizioni<2) {
			ripetizioni++;
			driver=BeforeAndAfter.driver;
			driver.manage().deleteAllCookies();
			Thread.sleep(3000);
			utente_apre_browser_TRAGHETTILINES();
			Thread.sleep(3000);
			utente_inserisce_dati_viaggio_TRAGHETTILINES();
			Thread.sleep(3000);
			confronto_prezzi_TRAGHETTILINES();
		}
		
		System.out.println(ripetizioni+" test compleati.");
		driver.quit();
	}
}