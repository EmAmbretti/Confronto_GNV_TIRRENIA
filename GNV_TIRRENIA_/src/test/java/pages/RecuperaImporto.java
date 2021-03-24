package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecuperaImporto {
	
	public static String recuperaImporto(WebDriver driver) {
		String importo = driver.findElement(By.xpath("//*[@id=\"cartDropdown\"]/span")).getText();
		return importo;
		
	}

}
