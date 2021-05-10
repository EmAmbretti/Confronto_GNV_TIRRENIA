package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.EsitoSito;

public class RecuperaImportoPageMOBY {

	public static void recuperaImportoMOBY(WebDriver driver, EsitoSito esito) {
		String priceMoby = null;
		if(esito.getErrori() == null) {
			System.out.println("Start method: recuperaImportoMOBY");
			try {
				priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");
				System.out.println("Prezzo: " + priceMoby);
			} catch (Exception e) {
				esito.setErrori("Non è possibile rilasciare un preventivo!");
				System.out.println("ERRORE: recuperaImportoMOBY");
			}							

			String prezzoPerEsitoMoby = priceMoby.replace("€", "").replace(",", ".");
			System.out.println(prezzoPerEsitoMoby);
			esito.setPrezzo(prezzoPerEsitoMoby);
		}
	}

}
