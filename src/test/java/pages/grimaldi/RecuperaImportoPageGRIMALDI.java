package pages.grimaldi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class RecuperaImportoPageGRIMALDI {
	public static String recuperaImportoGrimaldi(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable{
		String giorno = null;
		boolean flag=false;
		String prezzoGrimaldi= null;
		try {
			driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/span/div[1]"));
			esito.setErrori(driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/span/div[1]")).getText()+".");
		}catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Nessun errore trovato.");
		}
		if(esito.getErrori() == null) {
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"calendar_wrap\"]/div[1]/h2"));
					Thread.sleep(2000);
					flag=false;
				}catch(Exception e) {
					Thread.sleep(3000);
					flag=true;
				}
			}
			while(flag);

			if(sito.getGiornoAndata().length()<2) {
				giorno=0+sito.getGiornoAndata();
			} else {
				giorno =sito.getGiornoAndata();
			}

			String data=giorno + " " + sito.getMeseAndata().substring(0,3).toUpperCase();
			Thread.sleep(5000);
			List<WebElement> elementList = driver.findElements(By.xpath("//a/div[1]"));
			for(WebElement element:elementList) {
				if(element.getText().equalsIgnoreCase(data)) {
					element.click();
					break;
				}
			}
			Thread.sleep(1000);
			Generic.clickById(driver, "nextstep");
			Thread.sleep(5000);
			try {
				prezzoGrimaldi=driver.findElement(By.xpath("//*[@id=\"frm-SPECIAL\"]/div/div[2]/div[2]/div[1]")).getText();
			}catch(org.openqa.selenium.NoSuchElementException e) {
				prezzoGrimaldi=driver.findElement(By.xpath("//*[@id=\"frm-STANDARD\"]/div/div[2]/div[2]/div[1]")).getText();
			}
			
			System.out.println(prezzoGrimaldi);
			return prezzoGrimaldi;
		} else {
			return null;
		}
		
	}
}
