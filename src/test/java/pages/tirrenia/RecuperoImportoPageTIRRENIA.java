package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.EsitoSito;

public class RecuperoImportoPageTIRRENIA {
	public static double recuperaImportoTIRRENIA(WebDriver driver, EsitoSito esito) {
		String priceMoby = null;
		Double prezzoMoby = null;
		if(esito.getErrori() == null) {
			try {
				priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");		
			} catch (Exception e) {
				esito.setErrori("Non è possibile rilasciare un preventivo!");
				e.printStackTrace();
			}
			prezzoMoby = (Double.parseDouble(priceMoby));
			System.out.println("Prezzo: " + prezzoMoby);
			return prezzoMoby;
		}else {
			System.out.println("Eventuali errori: "+ esito.getErrori());
			return 0;
		}
		
			
		
		
	}
}
