package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Recap {
	
	public static void selezionaSistemazione(WebDriver driver) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1120)");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"nav-tabContent\"]/div[3]/div[3]/div[2]/div/app-card-solution/div/div[2]/app-button/button")).click();
		
	}
	
	public static void cliccaTastoContinua(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[1]/app-accommodation-wizard-step1/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		
	}
	
	public static void cliccaTastoContinuaSenzaServizi(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[2]/app-accommodation-wizard-step2/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		
	}
	
	public static void cliccaTastoContinuaSenzaAssicurazione(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[3]/app-accommodation-wizard-step3/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		
	}

}
