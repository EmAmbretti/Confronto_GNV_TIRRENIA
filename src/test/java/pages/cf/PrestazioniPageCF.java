package pages.cf;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import model.EsitoSito;
import utils.Generic;

public class PrestazioniPageCF {

	public static void automationPaginaPrestazioni(WebDriver driver, EsitoSito sito) {
		if (sito.getErrori() == null) {
			System.out.println("\nAutomationPaginaPrestazioni");
			gestionePasseggeri(driver, sito);
			gestioneVeicoloCF(driver, sito);
			gestioneSistemazione(driver, sito);
			sceltaTariffa(driver, sito);
		} else {
			System.out.println("ERRORE: " + sito.getErrori());
		}
	}

	private static void gestionePasseggeri(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori() == null) {
			System.out.println("\nMetodo gestionePasseggeri");
			try {
				Generic.waitSeconds(3);
	
				int i = 1;
				int numeroPasseggeriAdulti = Integer.valueOf(esito.getDatiCsv().getPasseggeriAdulti());
				while (i < numeroPasseggeriAdulti) {
					i++;
	
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]");
					Generic.waitSeconds(2);
				} // //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[1]/div[2]/button[2]
					// //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]
				i = 0;
				int numeroPasseggeriBambini = Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini());
				while (i < numeroPasseggeriBambini) {
					i++;
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/ul/li[2]/div[2]/button[2]");
					Generic.waitSeconds(2);
				}
	
				i = 1;
				int numeroPasseggeriAnimali = Integer.valueOf(esito.getDatiCsv().getPasseggeriAnimali());
				if (numeroPasseggeriAnimali != 0) {
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/div/span/span[1]");
					Generic.waitSeconds(2);
				}
				while (i < numeroPasseggeriAnimali) {
					i++;
					// + CANI
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[2]/div[4]/div/div[1]/div[2]/div/ul/li[1]/div[2]/button[2]");
					Generic.waitSeconds(2);
				}
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("ERRORE SCELTA NUMERO PASSEGGERI");
			}
		}
	}

	private static void gestioneVeicoloCF(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				System.out.println("\nMetodo gestioneVeicoloCF");
				Generic.scrollPage(driver, "750");
				Generic.clickByXPath(driver,
						"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[5]/span/span[1]");

				if (!esito.getDatiCsv().getVeicolo().equalsIgnoreCase("no")) {
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[5]/span/span[1]");

					ArrayList<WebElement> listaDivVeicoli = Generic.getElementListByXPath(driver,
							"//div[@class='travel-go']/div/div[@class='choice']/ul/li");
					// //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[3]/div/div
					for (int i = 0; i < listaDivVeicoli.size(); i++) {
						if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CAR")) {
							System.out.println("\nSCELTA CAR");
							// ALTEZZA : NON LO SO, LUNGHEZZA: NON MENO DI 4, LARGHEZZA: NON LO SO
							if (Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), ".//div/span").getText()
									.toUpperCase().equals("AUTO")) {
								
								if (clicca_Scegliere_Modificare_Veicolo(driver, listaDivVeicoli.get(i))) {
									Generic.waitSeconds(2);
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i),
											"/div[2]/div[2]/div[1]/div[2]/label").click();
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), "/div[2]/button[2]")
											.click();
									break;
								}
							}

						} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("VEI 5 mt")) {
							System.out.println("\nSCELTA VEI 5 MT");
							// ALTEZZA : NON LO SO, LUNGHEZZA: DAI 5 A 6, LARGHEZZA: NON LO SO
							if (Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), ".//div/span").getText()
									.toUpperCase().contains("FURGONE")) {
								
								if (clicca_Scegliere_Modificare_Veicolo(driver, listaDivVeicoli.get(i))) {
									Generic.waitSeconds(2);
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i),
											"div[2]/div/div[1]/div[2]/label").click();
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i),
											"div[2]/div/div[1]/div[2]/div/input").sendKeys("550");
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), "/div[2]/button[2]")
											.click();
									break;
								}
							}

						} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("CMP")) {
							System.out.println("\nSCELTA CMP");
							// ALTEZZA : NON LO SO, LUNGHEZZA: OLTRE 7 , LARGHEZZA: NON LO SO
							if (Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), ".//div/span").getText()
									.toUpperCase().contains("CAMPER")) {
								
								if (clicca_Scegliere_Modificare_Veicolo(driver, listaDivVeicoli.get(i))) {
									Generic.waitSeconds(2);
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i),
											"div[2]/div/div[1]/div[2]").click();
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i),
											"div[2]/div/div[1]/div[2]/div/input").sendKeys("750");
									Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), "/div[2]/button[2]")
											.click();
									break;
								}

							}

						} else if (esito.getDatiCsv().getVeicolo().equalsIgnoreCase("MOTO")) {
							System.out.println("\nSCELTA MOTO");
							if (Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), ".//div/span").getText()
									.toUpperCase().contains("MOTO")) {
								if(!clicca_Scegliere_Modificare_Veicolo(driver, listaDivVeicoli.get(i))) {
									
									if(Generic.getChildElementByXPath(driver, listaDivVeicoli.get(i), "/div/div").getText().toLowerCase().contains("questo servizio non è compatibile con le vostre prestazioni")) {
										esito.setErrori("MOTO NON DISPONIBILE");
										break;
									}
								}
								break;
							} 
							
						}
					}
					Generic.waitSeconds(2);
					System.out.println("\n");

				} else {
					Generic.clickByXPath(driver,
							"//*[@id=\"content\"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[5]/span/span[1]");
					System.out.println("Non ci sono veicoli da inserire.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("ERRORE DURANTE SCELTA VEICOLO");
			}
		}
	}

	private static boolean clicca_Scegliere_Modificare_Veicolo(WebDriver driver, WebElement div_Veicolo) {
		boolean flag = false;
		try {
			if (Generic.getChildElementByXPath(driver, div_Veicolo, ".//button[2]/span").getText().toUpperCase()
					.contains("MODIFICARE")) {
				Generic.getChildElementByXPath(driver, div_Veicolo, ".//button[2]/span").click();
				flag = true;
			}
			if (Generic.getChildElementByXPath(driver, div_Veicolo, ".//button[1]").getText().toUpperCase()
					.contains("SCEGLIERE")) {
				Generic.getChildElementByXPath(driver, div_Veicolo, ".//button[1]").click();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRORE NELLA RICERCA DEGLI SPAN 'MODIFICARE' / 'SCEGLIERE'");
		}
		return flag;
	}

	// Click + "Bici":
	// //*[@id="content"]/div/div[1]/section/div[2]/div[2]/div[1]/div[3]/div[4]/div/div[1]/div[2]/div[1]/ul/li[4]/div[2]/button[2]

	private static void gestioneSistemazione(WebDriver driver, EsitoSito sito) {
		if(sito.getErrori()==null) {
			try {
				System.out.println("\nMetodo gestioneSistemazione");
				Generic.waitSeconds(3);
				// LISTA CONTENENTE TUTTE LE SISTEMAZIONI VISIBILI A FRONT END
				ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver,
						"//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[4]/div[4]/div/div[1]/div[2]/ul/li[@class='choice-item choice-item--withHead']/div[2]");
				
				if(elementList!=null && !elementList.isEmpty()) {
						for (int i = 0; i < elementList.size(); i++) {
							if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("POLTRON")) {
								if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText()
										.toUpperCase().contains("POLTRON")) {
									int whilev = 0;
									int numeroPasseggeriAdulti = Integer.valueOf(sito.getDatiCsv().getPasseggeriAdulti());
									while (whilev < numeroPasseggeriAdulti) {
										whilev++;
										// CLICK BUTTON +
										elementList.get(i).findElement(By.xpath(".//div//div[@class='quantity']/button[2]")).click();
									}
								}
							}
			
						if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("ESTERNA")) {
							if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText()
									.toLowerCase().contains("cabina esclusiva con oblò")) {
								// CLICK SCEGLIERE
								elementList.get(i).findElement(By.xpath(".//div/button[1]")).click();
								Generic.waitSeconds(2);
								// CLICK BUTTON +
								elementList.get(i).findElement(By.xpath(".//div[2]/div/div[1]/div/div/div/button[2]")).click();
								Generic.waitSeconds(2);
								// CLICK CLOSE
								elementList.get(i).findElement(By.xpath(".//div[2]/div/button[1]")).click();
								break;
							}
						}
			
						if (sito.getDatiCsv().getSistemazione().toUpperCase().contains("INTERNA")) {
							if (elementList.get(i).findElement(By.xpath(".//div[@class='item-title']/span[1]")).getText()
									.toLowerCase().contains("cabina esclusiva senza oblò")) {
								// CLICK SCEGLIERE
								elementList.get(i).findElement(By.xpath(".//div[2]/button[1]")).click();
								Generic.waitSeconds(2);
								// CLICK BUTTON +
								elementList.get(i).findElement(By.xpath(".//div[2]/div/div[1]/div/div/div/button[2]")).click();
								Generic.waitSeconds(2);
								// CLICK CLOSE
								elementList.get(i).findElement(By.xpath(".//div[2]/div/button[1]")).click();
								break;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				sito.setErrori("ERRORE NELLA SCELTA SISTEMAZIONE");
			}
		} 

	}

	private static void sceltaTariffa(WebDriver driver, EsitoSito sito) {
		if (sito.getErrori()== null) {
			try {
				System.out.println("\nMetodo sceltaTariffa");
		
				Generic.waitSeconds(4);
		
				ArrayList<WebElement> elementList = Generic.getElementListByXPath(driver,
						"//*[@id='content']/div/div[1]/section/div[2]/div[2]/div[1]/div[8]/div[2]/div/div[@class='BookingRateSummary-item']");
				if(elementList!=null && !elementList.isEmpty()) {
					
					for (int i = 0; i < elementList.size(); i++) {
						if (elementList.get(i).findElement(By.xpath(".//div[1]/span[@class='price-name']")).getText()
								.contains("STANDARD")) {
							elementList.get(i).findElement(By.xpath(".//div[2]")).click();
							break;
						}
					}
				
					Generic.scrollPage(driver, "document.body.scrollHeight");
			
					Generic.waitSeconds(3);
			
					sito.setPrezzo(
							Generic.getElementByXPath(driver, "//*[@id=\"content\"]/div/div[2]/div/div/div[2]/div[2]/div/div[2]")
									.getText().replace(",", ".").replace(" ", "").replace("€", ""));
					System.out.println("PREZZO RECUPERATO: " + sito.getPrezzo());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRORE SCELTA TARIFFA");
				sito.setErrori("ERRORE SCELTA TARIFFA");
			}
		}
	}

}
