package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.WebData;

public class RecuperaImportoPageMOBY {
	
	public static double recuperaImportoMoby(WebDriver driver, WebData sito) {
		String priceMoby = null;
		try {
			priceMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Double prezzoMoby = Double.valueOf(priceMoby.replace(",", "."));
		return prezzoMoby;		 
	}

}
