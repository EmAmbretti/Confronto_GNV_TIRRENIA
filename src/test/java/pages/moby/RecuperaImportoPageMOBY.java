package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.EsitoSito;

public class RecuperaImportoPageMOBY {

	public static double recuperaImportoMOBY(WebDriver driver, EsitoSito esito) {
		String priceMoby = null;
		Double prezzoMoby = null;
		if(esito.getErrori() == null) {
			System.out.println("Start method: recuperaImportoMOBY");
			try {

				try {
					priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");		
				} catch (Exception e) {
					esito.setErrori("Non è possibile rilasciare un preventivo!");
					e.printStackTrace();
				}						
				prezzoMoby = (Double.parseDouble(priceMoby));
				System.out.println("Prezzo: " + prezzoMoby);	
			} catch(Exception e) {
				esito.setErrori("Preventivo non disponibile.");
				System.out.println("ERRORE: recuperaImportoMOBY");
			}		
		}
		if(prezzoMoby == null) {
				prezzoMoby = 0.0;
			}
		return prezzoMoby;
	}

}
