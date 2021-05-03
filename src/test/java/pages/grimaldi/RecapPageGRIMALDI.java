package pages.grimaldi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.EsitoSito;

public class RecapPageGRIMALDI {
	public static void switchPage(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Thread.sleep(2000);
			String mainWindow = driver.getWindowHandle();
			int pagine= driver.getWindowHandles().size();
			new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(pagine));
			int i = 1;
			for(String existingWindows : driver.getWindowHandles()) {
				if(i==pagine) {
					driver.switchTo().window(existingWindows);
				}else {
					i ++;
				}
			}
			Thread.sleep(5000);
		}
	}
}
