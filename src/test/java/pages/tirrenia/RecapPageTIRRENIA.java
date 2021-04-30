package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.WebData;
import utils.Generic;

public class RecapPageTIRRENIA {
	
	public static void controlloDisponibilitaFinaleTirrenia(WebDriver driver, WebData sito) {
		try{
			driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ascx_andata_div_partenze_non_trovate\"]"));
			sito.setDisponibilita("la tratta per questo sito non è disponibile.");
		}catch(Exception e) {

		}
	}
	
	public static void controlloDisponibilitaSistemazioneTirrenia(WebDriver driver, WebData sito) throws Throwable{
		if(sito.getDisponibilita() == null) {
			try {
				if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_0")).getText().contains("€")) {
					Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div");
					Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
					Thread.sleep(3000);
				}else {
					sito.setDisponibilita("la sistemazione scelta non è disponibile (posti mancanti).");
				}
			}catch(org.openqa.selenium.NoSuchElementException e) {
				sito.setDisponibilita("la tratta per questo sito non è disponibile.");
			}
		}
	}
}
