package utils;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class BeforeAndAfter {
    public static WebDriver driver = null;
    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @Before
    public void before(Scenario scenario) throws Throwable {
        //   if (driver == null) {
        System.setProperty("webdriver.chrome.driver", new File(".").getCanonicalFile() + "\\Drivers\\Chrome\\chromedriver.exe");
        //System.out.println("Test webdriver path " + System.getProperty("webdriver.chrome.driver"));
        System.out.println("------------------------------\nNew ChromeDriver..\n------------------------------\n");
        driver = new ChromeDriver();
        //   }
        System.out.println("------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println("------------------------------");
    }


    @After
    public void after(Scenario scenario) throws InterruptedException {
        System.out.println("------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println("------------------------------");
        // Reporter.loadXMLConfig(new File("report.xml"));
        Thread.sleep(7000);

        /*
         * 
         *File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

         try {
            File folder = new File(
                    Config.get("path.folder")+ "_" + dateTime.format(formatterDate));
            if (folder.mkdir() || folder.exists())
                FileUtils.copyFile(src, new File(folder + File.separator + dateTime.format(formatterDateTime)+ "_" +scenario.getName() + ".png"));
            //FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = driver.getCurrentUrl();
        String path = Config.get("path.esito")+"_"+dateTime.format(formatterDate) +".txt";
        Config.savefile(url, scenario.getName(), path);
        //driver.quit();
         * 
         * */
    }
}

