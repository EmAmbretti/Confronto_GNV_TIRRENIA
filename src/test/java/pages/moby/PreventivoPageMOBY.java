package pages.moby;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import model.EsitoSito;
import utils.Generic;

public class PreventivoPageMOBY {
	
	public static void inserimentoDatiMoby(WebDriver driver, EsitoSito sito) throws Throwable {
		inserisciPasseggeriMoby(driver, sito);
		gestioneVeicoloMoby(driver, sito);
		selezionaSistemazione(driver, sito);
		cliccaContinua(driver);
	}
	
	private static void inserisciPasseggeriMoby(WebDriver driver, EsitoSito sito) throws Throwable{
		if(sito.getErrori() == null) {
			for(int i = 0; i < Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()); i ++) {
				cliccaTastoPiuAdulti(driver);
			}
			for(int i = 0; i < Integer.valueOf(sito.getDatiCsv().getPasseggeriBambini()); i ++) {
				cliccaTastoPiuBambini(driver);
			}
			for(int i = 0; i < Integer.valueOf(sito.getDatiCsv().getPasseggeriAnimali()); i ++) {
				cliccaTastoPiuAnimali(driver);
			}
			Thread.sleep(3000);
			
		}	
	}
	private static void cliccaTastoPiuAdulti(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"mobyGuid22\"]/div/div/button[2]")).click();
	}
	
	private static void cliccaTastoPiuBambini(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"mobyGuid23\"]/div/div/button[2]")).click();
	}
	
	private static void cliccaTastoPiuAnimali(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id=\"mobyGuid26\"]/div/div/button[2]")).click();
	}

	private static void gestioneVeicoloMoby(WebDriver driver, EsitoSito sito) {
		if (sito.getErrori() == null && !sito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
			Generic.clickByXPath(driver, "//button[@id='customSelectMobyGuid20']");
			if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza fino a 4m')]");

			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Auto con lunghezza da 4,01m a 5m')]");

			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Camper nel garage')]");

			} else if (sito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
				Generic.clickByXPath(driver,
						"//li[@class='option']/a[@class='fg-color'][contains(.,'Moto Fino A 200cc')]");

			} else {
				System.out.println("veicolo non disponibile");
			}
		}

	}
	
	private static void selezionaSistemazione(WebDriver dirver, EsitoSito sito) throws Throwable {
		int passeggeri = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti()) + Integer.valueOf(sito.getDatiCsv().getPasseggeriBambini());
		if (sito.getDatiCsv().getSistemazione().contains("POLTRON")) {
			for(int i = 0; i < passeggeri; i ++) {
				Generic.clickByXPath(dirver, "//*[@id=\"mobyGuid37\"]/div/div/button[2]");
			}			
		}
		Thread.sleep(2000);
	}
	
	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}

}
