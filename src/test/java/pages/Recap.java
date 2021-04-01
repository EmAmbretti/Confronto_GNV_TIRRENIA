package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.WebData;
import utils.Generic;

public class Recap {
	
	public static void selezionaSistemazione(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
			int i=0;
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,1120)");
			Thread.sleep(4000);
			List<WebElement> listaTesti= driver.findElements(By.xpath("//div/app-card-solution/div/div[1]/div[2]/div[1]/div"));
			List<WebElement> listaSeleziona = driver.findElements(By.xpath("//div/app-card-solution/div/div[2]/app-button"));
			for(WebElement testo:listaTesti) {
				if(testo.getText().equalsIgnoreCase(sito.getSistemazione())) {
					listaSeleziona.get(i).click();
					break;
				}else {
					i++;
				}
				
			}
			if(i==listaTesti.size()) {
				sito.setDisponibilita("la sistemazione \""+sito.getSistemazione()+"\" non è disponibile.");
			}
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
	
	public static void switchPage(WebDriver driver,int pagine, WebData sito) throws Throwable {
		if(sito.getDisponibilita()==null) {
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
			Thread.sleep(5000);
		}
//		for(String existingWindows : driver.getWindowHandles()){
//			Thread.sleep(2000);
//			if(!mainWindow.equals(existingWindows)) {
//				driver.switchTo().window(existingWindows);
//				Thread.sleep(2000);
//				break;
//			}
		
		
	}
	public static void controlloDisponibilitaPoltrona(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			try {
				if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_0")).getText().contains("€")) {
				Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div");
		        Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
				Thread.sleep(3000);
			}else {
				sito.setDisponibilita("La sistemazione scelta non è disponibile (posti mancanti).");
			}
			}catch(org.openqa.selenium.NoSuchElementException e) {
				sito.setDisponibilita("La tratta non è disponibile per questo sito.");
			}
			
		}
	}

}
