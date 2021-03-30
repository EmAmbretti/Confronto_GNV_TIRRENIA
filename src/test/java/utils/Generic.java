package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
	
	public static Double confrontoPrezzi(WebDriver driver, Double importo1,String nomeSitoImp1,Double importo2,String nomeSitoImp2) {
		if(importo1!=null&&importo2!=null) {
			if(importo1 > importo2) {
				System.out.println("L'importo minore è: " + importo2 + " € del sito "+nomeSitoImp2+"." );
				return importo2;
			} else if (importo2 > importo1) {
				System.out.println("L'importo minore è: " + importo1 + " € del sito "+nomeSitoImp1+".");
				return importo1;
			} else {
				System.out.println("Gli importi, dei due siti, sono uguali.");
				return importo1;
			}
		}else {
			System.out.println("Impossibile trovare prezzo!");
			return null;
		}
	}
	
	public static void clickByXPath(WebDriver driver, String xPath) throws Throwable {
		driver.findElement(By.xpath(xPath)).click();

	}
	
	public static void clickById(WebDriver driver, String id) throws Throwable {
		driver.findElement(By.id(id)).click();

	}
	
	public static void generaFileTxt(String tipologia, String tratta, String mese,String giorno, String passAdulti, String passBambini, String passAnimali, String veicolo,String prezzoTirrenia, String prezzoGNV,String prezzoMigliore) {
		int numero=1;
		boolean flag=false;
		String path=new File ("automationFiles\\"+"Report").getAbsolutePath();
		File itinerario = new File(path+"\\" + "File" + numero + ".txt");
		try {
			//File myObj = new File(path);
			FileWriter fw = new FileWriter(itinerario,true);
			//BufferedWriter bw= new BufferedWriter(new FileWriter("nuovoFile"));
			fw.write("ITINERARIO: "+tipologia+"\nTRATTA: "+tratta+ "\nMESE: "+mese+"\nGIORNO: "+giorno+"\nNUMERO PASSEGGERI ADULTI: "+passAdulti+"\nNUMERO PASSEGGERI BAMBINI: "+passBambini+"\nNUMERO PASSEGGERI ANIMALI: "+passAnimali+"\nVEICOLO: "+veicolo
			+"\nPREZZO TIRRENIA: "+prezzoTirrenia+" - PREZZO GNV: "+prezzoGNV+"\nPREZZO CONVENIENTE: "+prezzoMigliore);
			
			do {	
				if (itinerario.exists()) {
					numero ++;					
					itinerario = new File(path+"\\" + "File" + numero + ".txt");
					flag=false;
				} else {
					flag = true;
					
					FileUtils.copyFile(itinerario, new File(itinerario.toString()));
					System.out.println(itinerario.toString());
				}
			}while(flag);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
