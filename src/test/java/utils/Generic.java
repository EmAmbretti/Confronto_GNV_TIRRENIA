package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.CSVData;
import model.EsitoSito;

public class Generic {

	public static void utente_apre_browser(WebDriver driver, String url, String nomeSito, EsitoSito esito) {
		System.out.println("Opening URL: " + nomeSito);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		for(int i = 1; i < 5; i ++) {
			try {
				driver.get(url);
				break;
			} catch (WebDriverException we) {
				System.out.println("URL non caricato.");
				if (i == 4) {
					System.out.println("CIABBè");
					esito.setErrori("Il server non ha risposto.");
					driver.quit();
				}
			}
		}
		if(esito.getErrori() == null) {
			driver.manage().window().maximize();
			waitSeconds(4);
		}	
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

	public static void scrollPage(WebDriver driver, String scroll) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + scroll + ")");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SCROLL VERTICALE: "+scroll);
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
			WebElement element = getElementByXPath(driver, xPath);
			if (element != null) {
				element.click();
				System.out.println("CLICKED: " + xPath);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! clickByXPath: ");
			System.out.print(xPath);
			if (e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": " + e.getLocalizedMessage());
			}
		}
	}

	public static void clickById(WebDriver driver, String id) {
		try {
			WebElement element = getElementById(driver, id);
			if (element != null) {
				element.click();
				System.out.println("CLICKED: " + id);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! ClickById: ");
			System.out.print(id);
			if (e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": " + e.getLocalizedMessage());
			}
		}

	}

	public static void sendKeysByXPath(WebDriver driver, String xpath, String toSend) {
		try {
			WebElement element = getElementByXPath(driver, xpath);
			if (element != null) {
				element.sendKeys(toSend);
				System.out.println("TESTO INSERITO, sendKeysByXPath: "+xpath);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! SendKeysByXPath: ");
			System.out.print(xpath);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": "+e.getLocalizedMessage()+ "\n\n");
			}
		}
	}

	public static WebElement getElementByXPath(WebDriver driver, String xpath) {
		WebElement element = null;
		try {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			element = driver.findElement(By.xpath(xpath));
			if (element != null) {
				System.out.println("getElementByXPath: "+xpath);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("\n\n!ERRORE! getElementByXPath: "+ xpath+". NoSuchElement");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("\n\n!ERRORE! getElementByXPath: "+ xpath+". TimeOutException");
		}catch (Exception e) {
			System.out.println("\n\n!ERRORE! getElementByXPath: "+ xpath+". "+e.getLocalizedMessage());
		}
		return element;
	}

	public static WebElement getElementById(WebDriver driver, String id) {
		WebElement element = null;
		try {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			element = driver.findElement(By.id(id));
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("\n\n!ERRORE! getElementById: "+ id+". NoSuchElement");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("\n\n!ERRORE! getElementById: "+ id+". TimeOutException");
		}catch (Exception e) {
			System.out.println("\n\n!ERRORE! getElementById: "+ id+". "+e.getLocalizedMessage());
		}
		return element;
	}

	public static void sendKeysByList(WebDriver driver, String xpath, String toSend, int index) {
		try {
			List<WebElement> elementList = getElementListByXPath(driver, xpath);
			if (elementList != null) {
				elementList.get(index).sendKeys(toSend);
				System.out.println("TESTO INSERITO, sendKeysByXPath: "+xpath);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! SendKeysByXPath: ");
			System.out.print(xpath);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": "+e.getLocalizedMessage()+ "\n\n");
			}
		}
	}
	public static void sendKeysById(WebDriver driver, String id, String toSend) {
		try {
			WebElement element = getElementById(driver, id);
			if (element != null) {
				element.sendKeys(toSend);
				System.out.println("TESTO INSERITO, sendKeysById: "+id);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! SendKeysById: ");
			System.out.print(id);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": "+e.getLocalizedMessage()+ "\n\n");
			}
		}
	}

	public static void clickByList(WebDriver driver, String xPath, int index) {
		try {
			List<WebElement> elementList = getElementListByXPath(driver, xPath);
			elementList.get(index).click();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("elemento lista non trovato");
		}
	}

	public static ArrayList<WebElement> getElementListByXPath(WebDriver driver, String xPath) {
		ArrayList<WebElement> elementList = null;
		try {
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
			elementList = (ArrayList<WebElement>) driver.findElements(By.xpath(xPath));
			if(elementList!=null && elementList.size()>0) {
				System.out.println("ELEMENTI RECUPERATI: "+xPath+" (getElementListByXPath)");
			} else {
				System.out.println("!ERRORE! getElementListByXPath NULL or VOID: " + xPath);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("\n\n!ERRORE! getElementListByXPath: "+ xPath+". NoSuchElement");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("\n\n!ERRORE! getElementListByXPath: "+ xPath+". TimeOutException");
		}catch (Exception e) {
			System.out.println("\n\n!ERRORE! getElementListByXPath: "+ xPath+". "+e.getLocalizedMessage());
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

	public static String getTextFromXPath(WebDriver driver, String xPath) {
		WebElement element = getElementByXPath(driver, xPath);
		return element.getText();
	}

	public static void switchPage(WebDriver driver, EsitoSito esito) throws Throwable {
		if(esito.getErrori() == null) {
			Thread.sleep(2000);
			int pagine= driver.getWindowHandles().size();
			new WebDriverWait(driver, 10).until(ExpectedConditions.numberOfWindowsToBe(pagine));
			int i = 1;
			for(String existingWindows : driver.getWindowHandles()) {
				if(i==pagine) {
					driver.close();
					driver.switchTo().window(existingWindows);
				}else {
					i ++;
				}
			}
			Thread.sleep(5000);
		}
	}


	public static String controlloFasciaOraria(String orario, EsitoSito esito) {
		String startDiurno = Config.get("inizio_orario_diurno");
		String finishDiurno = Config.get("fine_orario_diurno");
		String startNotturno = Config.get("inizio_orario_notturno");
		String finishNotturno = Config.get("fine_orario_notturno");
		String[] orarioDaControllare = orario.replace("", "").split(":");
		String[] diurno = startDiurno.replace("", "").split(":");
		String[] diurno2 = finishDiurno.replace("", "").split(":");
		String[] notturno = startNotturno.replace("", "").split(":");
		String[] notturno2 = finishNotturno.replace("", "").split(":");
		int orarioInt=Integer.valueOf(orarioDaControllare[0]);
		int controlloInizioDiurno = Integer.valueOf(diurno[0]);
		int controlloFineDiurno = Integer.valueOf(diurno2[0]);
		int controlloInizioNotturno = Integer.valueOf(notturno[0]);
		int controlloFineNotturno = Integer.valueOf(notturno2[0]);
		if(orarioInt>=controlloInizioDiurno && orarioInt<=controlloFineDiurno) {
			return "DIURNO";
		}else if(orarioInt>=controlloInizioNotturno && orarioInt <=controlloFineNotturno) {
			return "NOTTURNO";
		}else {
			esito.setErrori("fascia"+esito.getDatiCsv().getFasciaOraria()+ "non disponibile");
			return "fascia"+esito.getDatiCsv().getFasciaOraria()+ "non disponibile";

		}
	}

	/*public static int controlloStagione(CSVData data) {
		if(data.getStagione().equalsIgnoreCase("Alta")) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data1 = LocalDate.parse(Config.get("inizio_alta_stagione"), formatter);
		LocalDate data2 = LocalDate.parse(Config.get("fine_alta_stagione"), formatter);
		int t = (int) ChronoUnit.DAYS.between(data1,data2);
		return t;
		}
		else if(data.getStagione().equalsIgnoreCase("Bassa")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data1 = LocalDate.parse(Config.get("inizio_bassa_stagione"), formatter);
			LocalDate data2 = LocalDate.parse(Config.get("fine_bassa_stagione"), formatter);
			int t = (int) ChronoUnit.DAYS.between(data1,data2);
			return t;
		}else {
			System.out.println("Campo stagione is null");
			return -1;
		}
	}*/

	public static void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void nowLoadingByXpath(WebDriver driver, String xpath) throws Throwable{
		boolean flag=true;
		while(flag){
			try {
				driver.findElement(By.xpath("//form[@method='POST']"));
				flag=false;
			}catch(Exception e) {
				System.out.println("Now Loading...");
				Thread.sleep(2000);
			}
		}
	}

	public static String changeXPathForChildElement(String xpath) {
		// .//
		/*
				 div
				 /div
				 //div
				 .//div
		 */
		if(xpath.charAt(0) != '.') {
			xpath = "." + xpath;
		}
		if(xpath.charAt(1) != '/') {
			xpath = xpath.charAt(0) + "/" + xpath.substring(1);
		}
		if(xpath.charAt(2) != '/') {
			xpath = xpath.substring(0, 2)+ "/" + xpath.substring(2);
		}
		return xpath;
	}

	public static WebElement getChildElementByXPath(WebDriver driver, WebElement parentElement, String xpath) {
		WebElement element = null;

		xpath = changeXPathForChildElement(xpath);

		try {
			element = parentElement.findElement(By.xpath(xpath));
			if (element != null) {
				System.out.println("getChildElementByXPath: "+xpath);
			}
		} catch (Exception e) {
			System.out.print("\n\n!ERRORE! getChildElementByXPath: ");
			System.out.print(xpath);
			if(e instanceof org.openqa.selenium.NoSuchElementException) {
				System.out.println(": Selenium.NoSuchElementException\n\n");
			} else {
				System.out.println(": "+e.getLocalizedMessage()+ "\n\n");
			}
		}
		return element;
	}

	public static String meseDaInteroAStringa(int mese) {
		switch (mese) {
		case 1:
			return "GENNAIO";
		case 2:
			return "FEBBRAIO";
		case 3:
			return "MARZO";
		case 4:
			return "APRILE";
		case 5:
			return "MAGGIO";
		case 6:
			return "GIUGNO";
		case 7:
			return "LUGLIO";
		case 8:
			return "AGOSTO";
		case 9:
			return "SETTEMBRE";
		case 10:
			return "OTTOBRE";
		case 11:
			return "NOVEMBRE";
		case 12:
			return "DICEMBRE";
		default:
			return null;
		}
	}

	public static void setDataPrenotazioneNelModelCSV(CSVData datiCSV, LocalDate dataPrenotazione) {

		datiCSV.setGiornoAndata(String.valueOf(dataPrenotazione.getDayOfMonth()));
		datiCSV.setMeseAndata(meseDaInteroAStringa(dataPrenotazione.getMonthValue()));
		datiCSV.setAnno(String.valueOf(dataPrenotazione.getYear()));
	}

	public static LocalDate setDataInizio(String stagione) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataInizio = null;
		if (stagione.equalsIgnoreCase("ALTA")) {
			dataInizio =  LocalDate.parse(Config.get("inizio_alta_stagione"), formatter);
		} else if (stagione.equalsIgnoreCase("BASSA")) {
			dataInizio =  LocalDate.parse(Config.get("inizio_bassa_stagione"), formatter);
		}
		return dataInizio;
	}

	public static LocalDate setDataFine(String stagione) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataFine = null;
		if (stagione.equalsIgnoreCase("ALTA")) {
			dataFine =  LocalDate.parse(Config.get("fine_alta_stagione"), formatter);
		} else if (stagione.equalsIgnoreCase("BASSA")) {
			dataFine =  LocalDate.parse(Config.get("fine_bassa_stagione"), formatter);
		}
		return dataFine;}

	public static int controlloStagione(String stagione) {
		if(stagione.equalsIgnoreCase("Alta")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data1 = LocalDate.parse(Config.get("inizio_alta_stagione"), formatter);
			LocalDate data2 = LocalDate.parse(Config.get("fine_alta_stagione"), formatter);
			int t = (int) ChronoUnit.DAYS.between(data1,data2);
			return t;
		}
		else if(stagione.equalsIgnoreCase("Bassa")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data1 = LocalDate.parse(Config.get("inizio_bassa_stagione"), formatter);
			LocalDate data2 = LocalDate.parse(Config.get("fine_bassa_stagione"), formatter);
			int t = (int) ChronoUnit.DAYS.between(data1,data2);
			return t;
		}else {
			System.out.println("Campo stagione is null");
			return -1;
		}
	}

}