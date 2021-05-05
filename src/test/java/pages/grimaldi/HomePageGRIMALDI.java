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
	
	
	public static void compilaHomePageGRIMALDI(WebDriver driver, EsitoSito sito) throws Throwable {
		bypassFrame(driver);
		cliccaSoloAndataGrimaldi(driver);
		cliccaSelezionaAndata(driver);
		selezionaAndataGrimaldi(driver, sito);	
		cliccaSuDataGrimaldi(driver, sito);
		prenotaOraGrimaldi(driver, sito);
	}

	private static void cliccaSelezionaAndata(WebDriver driver) {
		Generic.clickById(driver, "start-route");
	}
	
	private static void bypassFrame(WebDriver driver) throws Throwable {
//		driver.switchTo().frame("tlines").findElement(By.id("ContentPlaceHolder1_motore_ddl_destinazioni")).click();
//		Thread.sleep(3000);
		if(driver.findElement(By.xpath("//*[@id=\"top\"]/div[3]/div/div[3]/button")).isDisplayed()) {
			Generic.clickByXPath(driver, "//*[@id=\"top\"]/div[3]/div/div[3]/button");
		}
	}
	
	private static void cliccaSoloAndataGrimaldi(WebDriver driver) throws Throwable {
		driver.findElement(By.id("checkReturn")).click();
		Thread.sleep(1000);
	}
	
	private static void selezionaAndataGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		try {
			driver.findElement(By.xpath("//option[contains(text(),'" + sito.getDatiCsv().getTrattaAndata().toUpperCase() + "')]")).click();
		}catch (Exception e){
			sito.setErrori("la tratta per questo sito non è disponibile.");
		}
		
	}
	
	private static void cliccaSuDataGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null) {
			Generic.clickById(driver, "start-date");
		}
	}
	
	private static void prenotaOraGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable {
		if(sito.getErrori()==null) {
			Generic.clickById(driver, "confirmRouteForm");
			Thread.sleep(1000);
		}	
	}
}
