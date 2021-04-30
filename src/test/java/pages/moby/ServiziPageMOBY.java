package pages.moby;

import org.openqa.selenium.WebDriver;

import utils.Generic;

public class ServiziPageMOBY {
	
	public static void continuaPopUp(WebDriver driver) throws Throwable {
		cliccaContinua(driver);
		chiudiPopUp(driver);
	}
	
	private static void cliccaContinua(WebDriver driver) throws Throwable {
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}
	
	private static void chiudiPopUp(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "/html/body/div[15]/div[7]/button");
		Thread.sleep(2000);
	}
	
//	private static void chiudiPopUp(WebDriver driver, boolean assicurazione) {
//		if(assicurazione) {
//			Generic.clickByXPath(driver, "/html/body/div[15]/div[7]/div/button");
//		} else {
//			Generic.clickByXPath(driver, "/html/body/div[15]/div[7]/button");
//		}	
//	}

}
