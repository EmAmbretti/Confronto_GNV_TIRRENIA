package pages.tirrenia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.EsitoSito;
import utils.Generic;

public class RecapPageTIRRENIA {
	
	public static void controlloDisponibilitaFinaleTirrenia(WebDriver driver, EsitoSito esito) {
		try{
			driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ascx_andata_div_partenze_non_trovate\"]"));
			esito.setErrori("la tratta per questo sito non è disponibile.");
		}catch(Exception e) {

		}
	}
	
	public static void controlloDisponibilitaSistemazioneTirrenia(WebDriver driver, EsitoSito esito) throws Throwable{
		if(esito.getErrori() == null) {
			try {
				switch(esito.getDatiCsv().getSistemazione().toUpperCase()) {
				case "POLTRONA":
					if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoPoltrona_0")).getText().contains("€")) {
						Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazionePoltrona_0\"]/div");
						Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
						Thread.sleep(3000);
					}else {
						esito.setErrori("la sistemazione scelta non è disponibile (posti mancanti).");
					}
					break;
				case "PONTE":
					if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoStandard_0")).getText().contains("€")) {
						Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazioneStandard_0\"]/div");
						Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
						Thread.sleep(3000);
					}else {
						esito.setErrori("la sistemazione scelta non è disponibile (posti mancanti).");
					}
					break;
				case "CAB. INTERNA":
				case "CAB. ESTERNA":
					if(driver.findElement(By.id("ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Label_PrezzoCabina_0")).getText().contains("€")) {
						Generic.clickByXPath(driver, "//*[@id=\"ContentPlaceHolder1_ascx_andata_RepeaterPartenze_Panel_SistemazioneCabina_0\"]/div");
						Thread.sleep(1500);
						if(esito.getDatiCsv().getSistemazione().toUpperCase().equals("CAB. ESTERNA")) {
							Generic.clickByXPath(driver, "//div/div[2]/div[2]/ul/li[2]");
						}
						Generic.clickById(driver, "ContentPlaceHolder1_LinkButton_Avanti");
						Thread.sleep(3000);
					}else {
						esito.setErrori("la sistemazione scelta non è disponibile (posti mancanti).");
					}
					break;
				default:
					esito.setErrori("Errrore: testo per la sistemazione non valido");
				}
				
			}catch(org.openqa.selenium.NoSuchElementException e) {
				esito.setErrori("la tratta per questo sito non è disponibile.");
			}
		}
	}
}
