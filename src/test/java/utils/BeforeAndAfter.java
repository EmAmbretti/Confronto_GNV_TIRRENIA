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
        System.setProperty("webdriver.chrome.driver", new File(".").getCanonicalFile() + "\\Drivers\\Chrome\\chromedriver.exe");
        System.out.println("------------------------------\nNew ChromeDriver..\n------------------------------\n");
        driver = new ChromeDriver();
        System.out.println("------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println("------------------------------");
    }


    @After
    public void after(Scenario scenario) throws InterruptedException {
        System.out.println("------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println("------------------------------");
        Thread.sleep(7000);
    }
}

