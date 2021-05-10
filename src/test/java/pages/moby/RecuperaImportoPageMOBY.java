package pages.moby;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class RecuperaImportoPageMOBY {

	public static void recuperaImportoMOBY(WebDriver driver, EsitoSito esito) {
		WebElement prezzoMoby = null;
		if(esito.getErrori() == null) {
			System.out.println("Start method: recuperaImportoMOBY");
			try {
				prezzoMoby = driver.findElement(By.xpath("//*[@id=\"cart-boxes\"]/div[1]/div[4]/div/div[1]/div[2]/div[@class='format-value end']"));
				System.out.println("Prezzo: " + prezzoMoby.getText());
				Generic.clickByXPath(driver, "//*[@id=\"cart-boxes\"]/div[1]/div[4]/div/div[1]/div[2]/div[@class='format-value end']");		
			} catch (Exception e) {
				esito.setErrori("Non è possibile rilasciare un preventivo!");
				System.out.println("ERRORE: recuperaImportoMOBY");
			}							
			String prezzoPerEsitoMoby = driver.findElement(By.id("toolbarTotalePreventivo")).getAttribute("data-totale");
			System.out.println(prezzoPerEsitoMoby);
			esito.setPrezzo(prezzoPerEsitoMoby);
		}
	}

}
