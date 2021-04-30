package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.EsitoSito;

public class RecuperaImportoPageTIRRENIA {
	
	public static void recuperaImportoTIRRENIA(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			String importo = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Step_Andata_Panel_PrezzoTotale")).getText();
			esito.setPrezzo(importo);
		}				
	}
}
