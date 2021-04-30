package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.WebData;
import utils.Generic;

public class HomePageMOBY {
	
	public static void selezionaItinerarioMOBY(WebDriver driver, WebData sito) throws Throwable {
		selezionaSoloAndataMOBY(driver);
		selezionaTrattaMOBY(driver, sito);
		scegliDataMOBY(driver, sito);
		cliccaCerca(driver);
	}
	
	private static void selezionaSoloAndataMOBY(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/label[2]/span[2]");
		Thread.sleep(2000);
	}
	
	private static void selezionaTrattaMOBY(WebDriver driver, WebData sito) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/div[1]/div/div[1]/div/span/span[1]/span");
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("/html/body/div[12]/div/span/span/ul/li/span/ul/li[contains(., '" + sito.getTratta() + "')]")).click();
		}catch (Exception e){
			sito.setDisponibilita("la tratta per questo sito non è disponibile.");
		}
		Thread.sleep(2000);
	}
	
	private static void scegliDataMOBY(WebDriver driver, WebData sito) throws Throwable {
		if(sito.getDisponibilita() == null) {
			Generic.clickById(driver, "//*[@id=\"dateFrom\"]");
			Thread.sleep(1000);
			controlloMeseMOBY(driver, sito);			
		}
		if(sito.getDisponibilita() == null) {
			selezionaGiornoMOBY(driver, sito);
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
		
	}
	
	private static void controlloMeseMOBY(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita()==null) {
			do {
				WebElement element = driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[2]/text()"));
				if(element.getText().contains(sito.getMese())) {
					break;
				}else {
					cliccaFrecciaAvanti(driver);
				}
			} while(true);
			Thread.sleep(2000);
		}
	}
	
	private static void cliccaFrecciaAvanti(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/thead/tr[1]/th[3]/span")).click();
	}
	
	private static void selezionaGiornoMOBY(WebDriver driver, WebData sito ) throws Throwable{
		boolean controllo = false;
		for(int i = 1; i <= 6; i ++) {
			for(int j = 1; j <= 7; j ++) {
				WebElement element=driver.findElement(By.xpath("//*[@id=\"customGuid1\"]/div/ul/li[1]/div/div[1]/table/tbody/tr[" + i + "]/td[" + j + "]"));
				if(element.getText().equals(sito.getGiorno())) {
					try {
						element.click();
						controllo = true;
						break;
					} catch(Exception e) {
						sito.setDisponibilita("Il giorno inserito non è valido!");
						break;
					}				
				}
			}
			if(controllo) {
				break;
			}
		}
	}
	
	private static void cliccaCerca(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"widget-home\"]/form/div[2]/input");
		Thread.sleep(2000);
	}

}
