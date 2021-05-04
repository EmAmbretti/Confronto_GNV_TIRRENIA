package pages.tirreniaPrenotazioni;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import utils.Generic;

public class RecuperaImportoPageTIRRENIA {
	
	public static void recuperaImportoTIRRENIA(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori()==null) {
			Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder_Header_HeadingBread_Panel_Assicurazione\"]//div[3]/p/label");
			Thread.sleep(1000);
			String importo = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Stp_Riepilogo_Label_PrezzoTotale")).getText();
			sito.setPrezzo(importo);
		}				
	}
}
