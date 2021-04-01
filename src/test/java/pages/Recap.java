package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.WebData;
import utils.Generic;

public class Recap {
	
	public static void selezionaSistemazione(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1120)");
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@id=\"nav-tabContent\"]/div[3]/div[2]/div[2]/div/app-card-solution/div/div[2]/app-button/button")).click();

		}
	}
	
	public static void cliccaTastoContinua(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[1]/app-accommodation-wizard-step1/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		}
		
	}
	
	public static void cliccaTastoContinuaSenzaServizi(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[2]/app-accommodation-wizard-step2/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		}
		
	}
	
	public static void cliccaTastoContinuaSenzaAssicurazione(WebDriver driver, WebData sito) {
		if(sito.getDisponibilita()==null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[3]/app-accommodation-wizard-step3/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
		}	
	}
	
	public static void switchPage(WebDriver driver,int pagine) throws Throwable {
		String mainWindow = driver.getWindowHandle();
		new WebDriverWait(driver,10).until(ExpectedConditions.numberOfWindowsToBe(pagine));
		
		////////////////
		int i=1;
		for(String existingWindows : driver.getWindowHandles()) {
			if(i==pagine) {
				driver.switchTo().window(existingWindows);
			}else {
				i++;
			}
		}
		
//		for(String existingWindows : driver.getWindowHandles()){
//			Thread.sleep(2000);
//			if(!mainWindow.equals(existingWindows)) {
//				driver.switchTo().window(existingWindows);
//				Thread.sleep(2000);
//				break;
//			}
		
		
	}

}
