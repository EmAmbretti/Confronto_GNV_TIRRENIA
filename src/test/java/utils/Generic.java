package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;

public class Generic {

	public static void utente_apre_browser(WebDriver driver, String url, String nomeSito) throws Throwable {
		driver = BeforeAndAfter.driver;
		System.out.println("Opening URL: " + nomeSito);
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
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

	public static void scrollPage(WebDriver driver, String scroll) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + scroll + ")");
		Thread.sleep(4000);
	}

	public static Double confrontoPrezzi(WebDriver driver, Double importo1, String nomeSitoImp1, Double importo2,
			String nomeSitoImp2, Double importo3, String nomeSitoImp3) {
		if (importo1 == null) {
			importo1 = Double.MAX_VALUE;
		}
		if (importo2 == null) {
			importo2 = Double.MAX_VALUE;
		}
		if (importo3 == null) {
			importo3 = Double.MAX_VALUE;
		}
		if (importo1 > importo2) {
			if (importo2 > importo3) {
				return importo3;
			} else {
				return importo2;
			}
		} else if (importo1 > importo3) {
			return importo3;
		} else {
			return importo1;
		}

	}

	public static void clickByXPath(WebDriver driver, String xPath) {
		try {
			WebElement element = driver.findElement(By.xpath(xPath));
			if (element != null) {
				element.click();
				System.out.println("CLICKED: " + xPath);
			}
		} catch (Exception e) {
			System.out.print("!ERRORE! clickByXPath: ");
			System.out.print(xPath);
			if (e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException");
			} else {
				System.out.println(": " + e.getLocalizedMessage());
			}
		}
	}

	public static void clickById(WebDriver driver, String id) throws Throwable {
		try {
			WebElement element = driver.findElement(By.xpath(id));
			if (element != null) {
				element.click();
				System.out.println("CLICKED: " + id);
			}
		} catch (Exception e) {
			System.out.print("!ERRORE! ClickById: ");
			System.out.print(id);
			if (e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException");
			} else {
				System.out.println(": " + e.getLocalizedMessage());
			}
		}

	}

	public static void sendKeysByXPath(WebDriver driver, String xpath, String toSend) {
		try {
				WebElement element = driver.findElement(By.xpath(xpath));
			if (element != null) {
				element.sendKeys(toSend);
				System.out.println("TESTO INSERITO, sendKeysByXPath: "+xpath);
			}
		} catch (Exception e) {
			System.out.print("!ERRORE! SendKeysByXPath: ");
			System.out.print(xpath);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException");
			} else {
				System.out.println(": "+e.getLocalizedMessage());
			}
		}
	}

	public static void sendKeysById(WebDriver driver, String id, String toSend) {
		try {
			WebElement element = driver.findElement(By.id(id));
			if (element != null) {
				element.sendKeys(toSend);
				System.out.println("TESTO INSERITO, sendKeysById: "+id);
			}
		} catch (Exception e) {
			System.out.print("!ERRORE! SendKeysById: ");
			System.out.print(id);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException");
			} else {
				System.out.println(": "+e.getLocalizedMessage());
			}
		}
	}

	public static void clickByList(WebDriver driver, String xPath, int index) {
		List<WebElement> elementList = driver.findElements(By.xpath(xPath));
		elementList.get(index).click();
	}
	
	public static ArrayList<WebElement> getElementListByXPath(WebDriver driver, String xPath) {
		ArrayList<WebElement> elementList = null;
		try {
		elementList = (ArrayList<WebElement>) driver.findElements(By.xpath(xPath));
		System.out.println("getElementListByXPath: Elementi recuperati con successo -> "+xPath);
		} catch (Exception e) {
			System.out.print("!ERRORE! getElementListByXPath: ");
			System.out.print(xPath);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException");
			} else {
				System.out.println(": "+e.getLocalizedMessage());
			}
		}
		return elementList;
	}

	public static void generaFileTxt(String tipologia, EsitoSito sito1, EsitoSito sito2, EsitoSito sito3,
			Double prezzoTirrenia, Double prezzoGNV, Double prezzoGrimaldi, Double prezzoMigliore) {
		String path = new File("reportFiles\\").getAbsolutePath();
		File itinerario = new File(path + "\\" + Path.CONFRONTO);
		LocalDate data = LocalDate.now();
		LocalTime time = LocalTime.now();
		String timeStr = String.valueOf(time);
		timeStr = timeStr.substring(0, 5);
		try {
			FileWriter fw = new FileWriter(itinerario, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append("DATA: " + data + " ORE: " + timeStr + "\nCASO DI TEST: " + tipologia + "\nTRATTA: "
					+ sito1.getDatiCsv().getTrattaAndata() + "\nMESE: " + sito1.getDatiCsv().getMeseAndata() + "\nGIORNO: " + sito1.getDatiCsv().getGiornoAndata()
					+ "\nNUMERO PASSEGGERI ADULTI: " + sito1.getDatiCsv().getPasseggeriAdulti() + "\nNUMERO PASSEGGERI BAMBINI: "
					+ sito1.getDatiCsv().getPasseggeriBambini() + "\nNUMERO PASSEGGERI ANIMALI: " + sito1.getDatiCsv().getPasseggeriAnimali() + "\nVEICOLO: "
					+ sito1.getDatiCsv().getVeicolo());
			if (sito1.getErrori() == null) {
				bw.append("\nPREZZO GNV: " + prezzoGNV);
			} else {
				bw.append(
						"\nPREZZO GNV: Non è stato possibile rilasciare un preventivo per GNV per il seguente motivo: "
								+ sito1.getErrori());
			}
			if (sito2.getErrori() == null) {
				bw.append("\nPREZZO TIRRENIA: " + prezzoTirrenia);
			} else {
				bw.append(
						"\nPREZZO TIRRENIA: Non è stato possibile rilasciare un preventivo per TIRRENIA per il seguente motivo: "
								+ sito2.getErrori());
			}
			if (sito3.getErrori() == null) {
				bw.append("\nPREZZO GRIMALDI: " + prezzoGrimaldi);
			} else {
				bw.append(
						"\nPREZZO GRIMALDI: Non è stato possibile rilasciare un preventivo per GRIMALDI per il seguente motivo: "
								+ sito3.getErrori());
			}
			if (prezzoMigliore != Double.MAX_VALUE) {
				bw.append("\nPREZZO MIGLIORE: " + prezzoMigliore + " DEL SITO: ");
				if (prezzoMigliore == prezzoGNV) {
					bw.append(sito1.getSito());
				} else if (prezzoMigliore == prezzoTirrenia) {
					bw.append(sito2.getSito());
				} else {
					bw.append(sito3.getSito());
				}
			} else {
				bw.append("\nNon è stato possibile rilasciare un preventivo.");
			}
			bw.append("\n\n-------------------------------------------------------------------\n\n");
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}