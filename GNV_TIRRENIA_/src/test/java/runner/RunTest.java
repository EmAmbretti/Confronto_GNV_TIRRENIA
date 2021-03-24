package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature",
		tags = {"@apreGNV"},
		glue = {"steps", "utils"},
		plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
		"junit:target/cucumber-reports/Cucumber.xml",
		"json:target/cucumber-reports/Cucumber.json" }, 
		dryRun = false, monochrome = false)
public class RunTest {
}