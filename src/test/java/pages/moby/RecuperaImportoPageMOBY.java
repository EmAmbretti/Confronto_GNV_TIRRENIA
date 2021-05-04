package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.EsitoSito;

public class RecuperaImportoPageMOBY {
	
	public static double recuperaImportoMoby(WebDriver driver, EsitoSito esito) {
		String priceMoby = null;
		if(esito.getErrori() == null) {
			try {
				priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");		
			} catch (Exception e) {
				esito.setErrori("Non è possibile rilasciare un preventivo!");
				e.printStackTrace();
			}						
		}
		Double prezzoMoby = (Double.parseDouble(priceMoby));
		System.out.println("Prezzo: " + prezzoMoby);	
		return prezzoMoby;
	}
	
}
