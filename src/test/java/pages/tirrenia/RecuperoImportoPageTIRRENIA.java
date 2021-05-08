package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.EsitoSito;

public class RecuperoImportoPageTIRRENIA {
	public static void recuperaImportoTIRRENIA(WebDriver driver, EsitoSito esito) {
		String priceTIRRENIA = null;
		Double prezzoTIRRENIA = null;
		if(esito.getErrori() == null) {
			try {
				priceTIRRENIA = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");		
			} catch (Exception e) {
				esito.setErrori("Non è possibile rilasciare un preventivo!");
				e.printStackTrace();
			}
			prezzoTIRRENIA = (Double.parseDouble(priceTIRRENIA));
			System.out.println("Prezzo: " + prezzoTIRRENIA);
			priceTIRRENIA = priceTIRRENIA.replace(".", ",");
			esito.setPrezzo(priceTIRRENIA);
		}else {
			System.out.println("Eventuali errori: "+ esito.getErrori());
		}
		
			
		
		
	}
}
