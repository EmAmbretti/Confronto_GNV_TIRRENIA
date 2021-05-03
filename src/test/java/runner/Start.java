package runner;

import org.openqa.selenium.WebDriver;

import model.CSVData;
import steps.GrimaldiLines;
import steps.Tirrenia;
import utils.BeforeAndAfter;
import utils.CSVExtractor;
import utils.Path;

public class Start {

	public static void main(String[] args) {
		WebDriver driver;
		CSVData testData=CSVExtractor.getTestDataByOffer("TestCase1", Path.PATH);
		try {
			
			driver = BeforeAndAfter.before("TIRRENIA");
			//GrimaldiLines.stepGrimaldi(driver, testData);
			Tirrenia.stepTirrenia(driver, testData);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
			


		
	}

}
