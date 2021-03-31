package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Generic;

public class RecuperaImporto {
	
	public static String recuperaImporto(WebDriver driver) {
		String importo = driver.findElement(By.xpath("//*[@id=\"cartDropdown\"]/span")).getText();
		return importo;
		
	}

	public static String recuperaImportoGrimaldi(WebDriver driver, String giorno, String mese) throws Throwable{
		if(giorno.length()<2) {
			giorno=0+giorno;
		}
		
		String data=giorno + " "+ mese.substring(0,3).toUpperCase();
		Thread.sleep(5000);
		List<WebElement> elementList = driver.findElements(By.xpath("//a/div[1]"));
		for(WebElement element:elementList) {
			System.out.println(element.getText());
			if(element.getText().equalsIgnoreCase(data)) {
				element.click();
				break;
			}
		}
		Thread.sleep(1000);
		Generic.clickById(driver, "nextstep");
		Thread.sleep(5000);
		String prezzoGrimaldi=driver.findElement(By.xpath("//*[@id=\"frm-SPECIAL\"]/div/div[2]/div[2]/div[1]")).getText();
		System.out.println(prezzoGrimaldi);
		return prezzoGrimaldi;
		
	}
}
