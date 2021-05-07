package pages.grimaldi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class RecuperaImportoPageGRIMALDI {
	public static void recuperaImportoGrimaldi(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori() == null) {
			String giorno = null;
			boolean flag=false;
			String prezzoGrimaldi= null;
			try {
				driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/span/div[1]"));
				sito.setErrori(driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/span/div[1]")).getText()+".");
			}catch(org.openqa.selenium.NoSuchElementException e) {
				System.out.println("Nessun errore trovato.");
			}
			if(sito.getErrori() == null) {
				do {
					try {
						driver.findElement(By.xpath("//*[@id=\"calendar_wrap\"]/div[1]/h2"));
						Thread.sleep(1000);
						flag=false;
					}catch(Exception e) {
						Thread.sleep(2000);
						flag=true;
					}
				}
				while(flag);

				if(sito.getDatiCsv().getGiornoAndata().length()<2) {
					giorno=0+sito.getDatiCsv().getGiornoAndata();
				} else {
					giorno =sito.getDatiCsv().getGiornoAndata();
				}

				String data=giorno + " " + sito.getDatiCsv().getMeseAndata().substring(0,3).toUpperCase();
				Thread.sleep(3000);
				List<WebElement> dateList = driver.findElements(By.xpath("//a/div[1]"));
				List<WebElement> orarioList = driver.findElements(By.xpath("//div/a/div/span"));
				int i=0;
				for(WebElement element:dateList) {
					if(element.getText().equalsIgnoreCase(data)) {
						if(Generic.controlloFasciaOraria(orarioList.get(i).getText(),sito).equalsIgnoreCase(sito.getDatiCsv().getFasciaOraria())) {
							element.click();
							break;
						}
					}
					i++;
					if(i>=dateList.size()) {
						sito.setErrori("Errore: tratta non disponibile per questo sito!");
					}
				}
				if(sito.getErrori()==null) {
					
					Thread.sleep(1000);
					Generic.clickById(driver, "nextstep");
					Thread.sleep(2000);
					Generic.nowLoadingByXpath(driver, "//form[@method='POST']");
					try {
						prezzoGrimaldi=driver.findElement(By.xpath("//*[@id=\"frm-SPECIAL\"]/div/div[2]/div[2]/div[1]")).getText();
					}catch(org.openqa.selenium.NoSuchElementException e) {
						prezzoGrimaldi=driver.findElement(By.xpath("//*[@id=\"frm-STANDARD\"]/div/div[2]/div[2]/div[1]")).getText();
					}

					System.out.println(prezzoGrimaldi);
					sito.setPrezzo(prezzoGrimaldi);
				}

			}
		}
	}
}
