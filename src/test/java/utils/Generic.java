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
		if(importo1==null) {
			importo1=Double.MAX_VALUE;
		}
		if(importo2==null) {
			importo2=Double.MAX_VALUE;
		}
		if(importo3==null) {
			importo3=Double.MAX_VALUE;
		}
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
	
	public static void generaFileTxt(String tipologia, WebData sito1, WebData sito2, WebData sito3 ,Double prezzoTirrenia, Double prezzoGNV,Double prezzoGrimaldi,Double prezzoMigliore) {
		String path=new File ("reportFiles\\").getAbsolutePath();
		File itinerario = new File(path+"\\" + "ConfrontoPrezzi.txt");
		LocalDate data=LocalDate.now();
		LocalTime time=LocalTime.now();
		String timeStr=String.valueOf(time);
		timeStr=timeStr.substring(0,5);
		try {
			FileWriter fw = new FileWriter(itinerario,true);
			BufferedWriter bw= new BufferedWriter(fw);
			bw.append("DATA: "+data+" ORE: "+timeStr+"\nCASO DI TEST: "+tipologia+"\nTRATTA: "+sito1.getTratta()+ "\nMESE: "+sito1.getMese()+"\nGIORNO: "+sito1.getGiorno()+"\nNUMERO PASSEGGERI ADULTI: "+sito1.getAdulti()+"\nNUMERO PASSEGGERI BAMBINI: "+sito1.getBambini()+"\nNUMERO PASSEGGERI ANIMALI: "+sito1.getAnimali()+"\nVEICOLO: "+sito1.getVeicolo());
			if(sito1.getDisponibilita()==null) {
				bw.append("\nPREZZO GNV: "+prezzoGNV);
			}else {
				bw.append("\nPREZZO GNV: Non è stato possibile rilasciare un preventivo per GNV per il seguente motivo: "+sito1.getDisponibilita());
			}
			if(sito2.getDisponibilita()==null) {
				bw.append("\nPREZZO TIRRENIA: "+prezzoTirrenia);
			}else {
				bw.append("\nPREZZO TIRRENIA: Non è stato possibile rilasciare un preventivo per TIRRENIA per il seguente motivo: "+sito2.getDisponibilita());
			}
			if(sito3.getDisponibilita()==null) {
				bw.append("\nPREZZO GRIMALDI: "+prezzoGrimaldi);
			}else {
				bw.append("\nPREZZO GRIMALDI: Non è stato possibile rilasciare un preventivo per GRIMALDI per il seguente motivo: "+sito3.getDisponibilita());
			}
			bw.append("\nPREZZO MIGLIORE: "+prezzoMigliore+" DEL SITO: ");
			if(prezzoMigliore==prezzoGNV) {
				bw.append(sito1.getSito());
			}else if(prezzoMigliore==prezzoTirrenia) {
				bw.append(sito2.getSito());
			}else {
				bw.append(sito3.getSito());
			}
			
			
			bw.append("\n\n-------------------------------------------------------------------\n\n");
			bw.close();
			
			//impostare la scrittura dei singoli siti controllando la disponibilita (se è null o meno)
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
//*[@id="nav-tabContent"]/div[3]/div[1]/div[3]/div/app-card-solution/div/div[1]/div[2]/div[1]/div
//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[1]/div[2]/div[1]/div
//*[@id="nav-tabContent"]/div[3]/div[3]/div[2]/div/app-card-solution/div/div[1]/div[2]/div[1]/div
//*[@id="nav-tabContent"]/div[3]/div[2]/div[2]/div/app-card-solution/div/div[1]/div[2]/div[1]/div


//div/app-card-solution/div/div[1]/div[2]/div[1]/div   testo
//div/app-card-solution/div/div[2]/app-button    seleziona

//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[2]/app-button
//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[2]/app-button/button

//*[@id="nav-tabContent"]/div[3]/div[1]/div[3]/div/app-card-solution/div/div[2]/app-button
//*[@id="nav-tabContent"]/div[3]/div[1]/div[3]/div/app-card-solution/div/div[2]/app-button/button



//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[1]/div[2]/div[1]/div
//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[2]/app-button
//*[@id="nav-tabContent"]/div[3]/div[1]/div[2]/div/app-card-solution/div/div[2]/app-button/button
