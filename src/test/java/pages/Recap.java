package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.CSVData;
import model.EsitoSito;
import utils.Generic;

public class Recap {

	public static void controlloDisponibilitaFinaleTirrenia(WebDriver driver, EsitoSito esito) {
		try{
			driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ascx_andata_div_partenze_non_trovate\"]"));
			esito.setErrori("la tratta per questo sito non è disponibile.");
		}catch(Exception e) {

		}
	}

	public static void selezionaSistemazione(WebDriver driver, CSVData sito, EsitoSito esito) throws Throwable {
		if(esito.getErrori()==null) {
			int i = 0;
			Thread.sleep(3000);
			List<WebElement> listaTesti= driver.findElements(By.xpath("//div/app-card-solution/div/div[1]/div[2]/div[1]/div"));
			List<WebElement> listaSeleziona = driver.findElements(By.xpath("//div/app-card-solution/div/div[2]/app-button"));
			for(WebElement testo:listaTesti) {
				if(testo.getText().substring(0, testo.getText().length()-2).equalsIgnoreCase(sito.getSistemazione().substring(0, sito.getSistemazione().length()-2))) {
					listaSeleziona.get(i).click();
					break;
				}else {
					i ++;
				}

			}
			if(i == listaTesti.size()) {
				esito.setErrori("la sistemazione \"" + sito.getSistemazione() + "\" non è disponibile.");
			}
			Thread.sleep(3000);
		}
	}

	public static void cliccaTastoContinua(WebDriver driver, EsitoSito esito) throws Throwable{
		if(esito.getErrori() == null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[1]/app-accommodation-wizard-step1/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
			Thread.sleep(3000);
		}		
	}

	public static void cliccaTastoContinuaSenzaServizi(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[2]/app-accommodation-wizard-step2/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
			Thread.sleep(3000);
		}
	}

	public static void cliccaTastoContinuaSenzaAssicurazione(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/main/app-root/section/app-accommodation/app-accommodation-wizard/div[2]/app-wizard/div/div[2]/app-wizard-step[3]/app-accommodation-wizard-step3/div/app-accommodation-wizard-footer/div/div/div/app-button/button")).click();
			Thread.sleep(3000);
		}	
	}

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

	public static void controlloDisponibilitaSistemazioneTirrenia(WebDriver driver, EsitoSito esito) throws Throwable{
		if(esito.getErrori() == null) {
			try {
				if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_0")).getText().contains("€")) {
					Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div");
					Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
					Thread.sleep(3000);
				}else {
					esito.setErrori("la sistemazione scelta non è disponibile (posti mancanti).");
				}
			}catch(org.openqa.selenium.NoSuchElementException e) {
				esito.setErrori("la tratta per questo sito non è disponibile.");
			}
		}
	}

}
