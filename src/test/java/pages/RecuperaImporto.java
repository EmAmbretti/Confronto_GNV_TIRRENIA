package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.WebData;
import utils.Generic;

public class RecuperaImporto {
	
	public static void recuperaImportoGNV(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			String importo = driver.findElement(By.xpath("//*[@id=\"cartDropdown\"]/span")).getText();
			sito.setPrezzo(importo);
		}				
	}
	
	public static void recuperaImportoTIRRENIA(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			String importo = driver.findElement(By.id("ContentPlaceHolder_Header_HeadingBread_Step_Andata_Panel_PrezzoTotale")).getText();
			sito.setPrezzo(importo);
		}				
	}

	public static String recuperaImportoGrimaldi(WebDriver driver, WebData sito) throws Throwable{
		String giorno = null;
		boolean flag=false;
		String prezzoGrimaldi= null;
		if(sito.getDisponibilita() == null) {
			do {
				try {
					driver.findElement(By.xpath("//*[@id=\"calendar_wrap\"]/div[1]/h2"));
					Thread.sleep(2000);
					flag=false;
				}catch(org.openqa.selenium.ElementNotInteractableException e) {
					Thread.sleep(3000);
					flag=true;
				}
			}
			while(flag);

			if(sito.getGiorno().length()<2) {
				giorno=0+sito.getGiorno();
			} else {
				giorno =sito.getGiorno();
			}

			String data=giorno + " " + sito.getMese().substring(0,3).toUpperCase();
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
