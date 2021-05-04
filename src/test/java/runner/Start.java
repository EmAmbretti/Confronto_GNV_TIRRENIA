package runner;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import model.EsitoSito;
import steps.GNV;
import utils.CSVExtractor;
import utils.Path;

public class Start {

	public static void main(String[] args) {

		CSVData testData=CSVExtractor.getTestDataByOffer("TestCase1", Path.PATH);
		try {
			//GrimaldiLines.stepGrimaldi(driver, testData);
			GNV.allMethods(testData);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
			


		
	}

}
