package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.EsitoSito;

public class RecuperaImportoPageMOBY {

	public static double recuperaImportoMoby(WebDriver driver, EsitoSito sito) {
		String priceMoby = null;
		if(sito.getErrori() == null) {
			try {
				priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getText();
			} catch (Exception e) {
				sito.setErrori("Non è possibile rilasciare un preventivo!");
				e.printStackTrace();
			}						
		}
		Double prezzoMoby = Double.valueOf(priceMoby.replace(",", "."));	
		return prezzoMoby;
	}

}
