package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.CSVData;
import model.WebData;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Generic {
	
	public static void utente_apre_browser(WebDriver driver) {
		driver = BeforeAndAfter.driver;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nOpening URL GNV");
		driver.get("https://www.gnv.it/it");
		driver.manage().window().maximize();
		
	}
	
	public static List<WebElement> findElementsByInnerText(WebElement parent, String rex) {
		List<WebElement> childs = parent.findElements(By.cssSelector("*"));
		List<WebElement> found = new ArrayList<>();
		for (int i = 0; i < childs.size(); ++i) {
			WebElement entry = childs.get(i);
			String t = entry.getText();
			if (t.matches(rex))
				found.add(entry);
		}
		return found;
	}

	public static WebElement findElementByInnerText(List<WebElement> l, String rex) {
		for (int i = 0; i < l.size(); ++i) {
			WebElement entry = l.get(i);
			String text = entry.getText();
			if (text.matches(rex))
				return entry;
		}
		return null;
	}

	public static List<WebElement> findElementsByAttribute(WebElement parent, String attr, String rex) {
		List<WebElement> childs = parent.findElements(By.cssSelector("*"));
		List<WebElement> found = new ArrayList<>();
		for (int i = 0; i < childs.size(); ++i) {
			WebElement entry = childs.get(i);
			String a = entry.getAttribute(attr);
			if (attr.matches(rex))
				found.add(entry);
		}
		return found;
	}
	
	public static WebElement findElementByAttribute(List<WebElement> l, String attr, String rex) {
		for (int i = 0; i < l.size(); ++i) {
			WebElement entry = l.get(i);
			String a = entry.getAttribute(attr);
			if (attr.matches(rex))
				return entry;
		}
		return null;
	}
	
	public static void scrollPage(WebDriver driver, String scroll) throws Throwable{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,"+scroll+")");
		Thread.sleep(4000);
	}
	
	public static Double confrontoPrezzi(WebDriver driver, Double importo1,String nomeSitoImp1,Double importo2,String nomeSitoImp2, Double importo3, String nomeSitoImp3) {

		if(importo1 > importo2) {
			if(importo2 > importo3) {
				System.out.println("L'importo minore è: " + importo3 + " € del sito "+nomeSitoImp3+"." );
				return importo3;
			}else {
				System.out.println("L'importo minore è: " + importo2 + " € del sito "+nomeSitoImp2+"." );
				return importo2;
			}
		}else if(importo1 > importo3) {
			System.out.println("L'importo minore è: " + importo3 + " € del sito "+nomeSitoImp3+"." );
			return importo3;
		}else {
			System.out.println("L'importo minore è: " + importo1 + " € del sito "+nomeSitoImp1+".");
			return importo1;
		}
		
	}
	
	public static void clickByXPath(WebDriver driver, String xPath) throws Throwable {
		driver.findElement(By.xpath(xPath)).click();

	}
	
	public static void clickById(WebDriver driver, String id) throws Throwable {
		driver.findElement(By.id(id)).click();

	}
	
	public static void generaFileTxt(String tipologia, String tratta, String mese,String giorno, String passAdulti, String passBambini, String passAnimali, String veicolo,Double prezzoTirrenia, Double prezzoGNV,Double prezzoGrimaldi,Double prezzoMigliore) {
		String path=new File ("reportFiles\\").getAbsolutePath();
		File itinerario = new File(path+"\\" + "ConfrontoPrezzi.txt");
		LocalDate data=LocalDate.now();
		LocalTime time=LocalTime.now();
		String timeStr=String.valueOf(time);
		timeStr=timeStr.substring(0,5);
		try {
			FileWriter fw = new FileWriter(itinerario,true);
			BufferedWriter bw= new BufferedWriter(fw);
			bw.append("DATA: "+data+" ORE: "+timeStr+"\nCASO DI TEST: "+tipologia+"\nTRATTA: "+tratta+ "\nMESE: "+mese+"\nGIORNO: "+giorno+"\nNUMERO PASSEGGERI ADULTI: "+passAdulti+"\nNUMERO PASSEGGERI BAMBINI: "+passBambini+"\nNUMERO PASSEGGERI ANIMALI: "+passAnimali+"\nVEICOLO: "+veicolo
					+"\nPREZZO TIRRENIA: "+prezzoTirrenia+" - PREZZO GNV: "+prezzoGNV+" - PREZZO GRIMALDI: "+prezzoGrimaldi+"\nPREZZO CONVENIENTE: "+prezzoMigliore+"\n\n-------------------------------------------------------------------\n\n");
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
