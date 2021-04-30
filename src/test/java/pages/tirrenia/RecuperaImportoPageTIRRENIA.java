package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.WebData;

public class RecuperaImportoPageTIRRENIA {
	
	public static void recuperaImportoTIRRENIA(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			String importo = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Step_Andata_Panel_PrezzoTotale")).getText();
			sito.setPrezzo(importo);
		}				
	}
}
