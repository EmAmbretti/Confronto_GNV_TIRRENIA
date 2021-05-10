package pages.gnv;


import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.EsitoSito;
import utils.Generic;

public class RecapPageGNV {

	public static void recuperoImportoGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			selezionaSistemazioneGNV(driver, esito);
			scegliServizi(driver, esito);
			scegliAssicurazione(driver, esito);
			recuperaPrezzoGNV(driver, esito);
		}
	}

	private static void selezionaSistemazioneGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			HashMap<String, List<WebElement>> mappa = new HashMap<String, List<WebElement>>();
			int i = 0;
			boolean flag = false;
			int passeggeri = Integer.valueOf(esito.getDatiCsv().getPasseggeriAdulti()) + Integer.valueOf(esito.getDatiCsv().getPasseggeriBambini());
			String sistemazione = "";
			if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("POLTRONA")) {
				sistemazione = "POLTRON";
			} else if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("CAB. INTERNA")) {
				sistemazione = "CABINA INTERNA";
			} else if(esito.getDatiCsv().getSistemazione().equalsIgnoreCase("CAB. ESTERNA")) {
				sistemazione = "CABINA VISTA MARE";
			}
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mappa.put("listaOrari", driver.findElements(By.xpath("//li[@class='list-inline-item departure-time']")));
			mappa.put("listaSistemazioni", driver.findElements(By.xpath("//div[@class='card-solution--title d-inline']")));
			mappa.put("listaPostiLetto", driver.findElements(By.xpath("//span[contains(.,'posti letto')]")));
			mappa.put("listaSeleziona",driver.findElements(By.xpath("//div[@class='card-solution__btn']")));

			try {
				//	WebElement	check = Generic.getElementByXPath(driver, "//*[@id='nav-tabContent']/div[2]/p");
				WebElement check = driver.findElement(By.xpath("//*[@id='nav-tabContent']/div[2]/p"));
				flag = true;
				esito.setErrori(check.getText());
				System.out.println("QUESTO è L'ERRORE NULL??:::::" + check.getText());
			}catch (Exception e) {
				System.out.println("popup non visibile");
			}
			if(esito.getErrori()==null) {
				if(mappa.get("listaOrari")!=null && mappa.get("listaOrari").size()>0) {
				if (Generic.controlloFasciaOraria(mappa.get("listaOrari").get(1).getText(), esito).equalsIgnoreCase(esito.getDatiCsv().getFasciaOraria())) {
					for (WebElement testo : mappa.get("listaSistemazioni")) {
						if(testo.getText().contains(sistemazione)) {
							try {
								mappa.get("listaSeleziona").get(i).click();
								System.out.println("ho cliccato");
								flag=true;
								Thread.sleep(1000);
								break;
							}catch (Exception e) {
								esito.setErrori(sistemazione+"non trovata");
							}
							System.out.println("PAsseggeri sono::" + passeggeri);
							if(passeggeri>=4) {
								System.out.println("Sto a 4");
								if(mappa.get("listaPostiLetto").get(i).getText().contains("4")) {
									System.out.println("posti letto = 4");
									mappa.get("listaSeleziona").get(i).click();
									flag=true;
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								}
							} else if(passeggeri==3) {
								System.out.println("sto a 3");
								if(mappa.get("listaPostiLetto").get(i).getText().contains("3")) {
									System.out.println("Posti letto = 3");
									mappa.get("listaSeleziona").get(i).click();
									flag=true;
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								}else {
									int	x=0;
									for(WebElement appoggio: mappa.get("listaSistemazioni")) {
										System.out.println("sto nel secondo for__77");
										if(appoggio.getText().contains(sistemazione)) {
											System.out.println("rifacio controllo sistemazione");
											if(mappa.get("listaPostiLetto").get(x).getText().contains("4")) {
												System.out.println("sto nel if che contiene 4 posti letto");
												mappa.get("listaSeleziona").get(x).click();
												flag=true;
												try {
													Thread.sleep(1000);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												break;
											}
										}
										x++;
									}
									break;
								}
							} else if(passeggeri<=2) {
								System.out.println("sto a 2");
								if(mappa.get("listaPostiLetto").get(i).getText().contains("2")) {
									System.out.println("Posti letto = 2");
									mappa.get("listaSeleziona").get(i).click();
									flag=true;
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									break;
								}
							} 
						}
						i++;
					}
				}else {
					esito.setErrori(esito.getDatiCsv().getFasciaOraria()+" non disponibile");
					System.out.println(esito.getDatiCsv().getFasciaOraria()+" non disponibile");
				}
				}else {
					System.out.println("N.D");
					esito.setErrori("N.D");
				}
			}
			if(i==mappa.get("listaSeleziona").size()) {
				int x=0;
				for(WebElement appoggio: mappa.get("listaSistemazioni")) {
					System.out.println("sto nel for giu a tutto");
					if(appoggio.getText().contains(sistemazione)) {
						if(mappa.get("listaPostiLetto").get(x).getText().contains("4")) {
							System.out.println("posti letto = 4 parte 2");
							mappa.get("listaSeleziona").get(x).click();
							flag=true;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
					}
				}
				x++;
			}
			if(!flag) {
				esito.setErrori("Itinerario selezionato non disponibile");
				System.out.println("Itinerario selezionato non disponibile");
			}
			if (i == mappa.get("listaSeleziona").size()) {
				esito.setErrori("la sistemazione \"" + esito.getDatiCsv().getSistemazione() + "\" non è disponibile.");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			cliccaContinaGNV(driver, esito);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void scegliServizi(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null)
			cliccaContinaGNV(driver, esito);
	}

	private static void scegliAssicurazione(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null)
			cliccaContinaGNV(driver, esito);
	}

	private static void cliccaContinaGNV(WebDriver driver, EsitoSito esito) {
		if(esito.getErrori()==null) {
			try {
				Generic.clickByXPath(driver,
						"//button[@class='btn btn-lg gnv-btn btn-orange btn-medium booking-footer-btn']//span[@class='gnv-icon ng-star-inserted'][contains(.,'Continua')]");
			} catch (Exception e) {
				e.printStackTrace();
				esito.setErrori("Impossibile cliccare continua");

			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void recuperaPrezzoGNV(WebDriver driver, EsitoSito esito) {
		if (esito.getErrori() == null) {
			try {
				String importo = driver.findElement(By.xpath("//button[@id='cartDropdown']/span[@class='price']"))
						.getText();
				esito.setPrezzo(importo);
				System.out.println("PREZZO GNV: " + importo);
			}catch (Exception e) {
				esito.setErrori("prezzo non recuperato");
			}


		}
	}
}
