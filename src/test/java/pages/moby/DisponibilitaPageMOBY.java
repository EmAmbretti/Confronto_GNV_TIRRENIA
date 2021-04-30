package pages.moby;

import org.openqa.selenium.WebDriver;
import utils.Generic;

public class DisponibilitaPageMOBY {
	
	public static void selezionaCorsa(WebDriver driver) throws Throwable {
		Generic.clickByXPath(driver, "//*[@id=\"bookingAndata\"]/div[1]/div/div[1]/div/div[1]/div");
		Thread.sleep(1000);
		Generic.clickById(driver, "buttonNextPage");
		Thread.sleep(2000);
	}

}
