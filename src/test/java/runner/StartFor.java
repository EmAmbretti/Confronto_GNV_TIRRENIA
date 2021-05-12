package runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

import model.CSVData;
import model.Differenza;
import model.EsitoSito;
import utils.CSVExtractor;
import utils.Config;
import utils.ExcelMaker;
import utils.Generic;
import utils.Path;
import utils.Validation;

public class StartFor {

	public static void main(String[] args) {

		args = new String[1];
		args[0] = "C:\\Users\\giovanni.sorrentino\\Desktop\\config.properties";

		if (Validation.propertiesFilePathCheck(args[0])) {
			if (Validation.propertiesFileValuesCheck()) {

				TreeMap<LocalDate, ArrayList<Differenza>> mappaConfronti = new TreeMap<LocalDate, ArrayList<Differenza>>();

				ArrayList<CSVData> datiCSV = CSVExtractor.process(Config.get("path_csv"));

				LocalDate dataInizio = null;
				LocalDate dataFine = null;

				dataInizio = Generic.setDataInizio("ALTA");
				dataFine = Generic.setDataFine("ALTA");

				LocalDate dataPrenotazione = dataInizio;

				int numeroDiGiorniDaLanciare = Generic.controlloStagione(Config.get("stagione"));

				for (int i = 0; i <= numeroDiGiorniDaLanciare; i++) {
					try {
						ArrayList<Differenza> listaDifferenze = new ArrayList<Differenza>();
						int prezzoDaEliminare = 10;
						for (int x = 0; x < datiCSV.size(); x++) {
							try {
								Generic.setDataPrenotazioneNelModelCSV(datiCSV.get(x), dataPrenotazione);
								EsitoSito esitoGrimaldi = new EsitoSito("grimaldi", datiCSV.get(i));
								EsitoSito esitoTirrenia = new EsitoSito("tirrenia", datiCSV.get(i));

								esitoGrimaldi.setPrezzo(String.valueOf(100 + (prezzoDaEliminare * (x + 1))));
								esitoTirrenia.setPrezzo(String.valueOf(120 + (prezzoDaEliminare * (x + 1))));

								Differenza diff = new Differenza(esitoGrimaldi, esitoTirrenia);
								listaDifferenze.add(diff);

//								EsitoSito esitoGrimaldi = null;
//								try {
//									esitoGrimaldi = GrimaldiLines.stepGrimaldi(datiCSV.get(x));
//								} catch (Throwable e) {
//									e.printStackTrace();
//								}
//								EsitoSito esitoCompetitor = null;
//								if (Config.get("competitor").equalsIgnoreCase("GNV")) {
//									try {
//										esitoCompetitor = GNV.allMethods(datiCSV.get(x));
//									} catch (Throwable e) {
//										e.printStackTrace();
//									}
//								} else if (Config.get("competitor").equalsIgnoreCase("TIRRENIA")) {
//									try {
//										esitoCompetitor = Tirrenia.stepTirrenia(datiCSV.get(x));
//									} catch (Throwable e) {
//										e.printStackTrace();
//									}
//								} else if (Config.get("competitor").equalsIgnoreCase("MOBY")) {
//									esitoCompetitor = Moby.allMethods(datiCSV.get(x));
//								} else if (Config.get("competitor").equalsIgnoreCase("CF")
//										|| Config.get("competitor").equalsIgnoreCase("CORSICA FERRIES")) {
//									esitoCompetitor = CorsicaFerries.automation(datiCSV.get(x));
//								} else {
//									System.out.println("Competitor non riconosciuto");
//								}
//
//								Differenza diff = new Differenza(esitoGrimaldi, esitoCompetitor);
//								listaDifferenze.add(diff);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (!listaDifferenze.isEmpty()) {
							mappaConfronti.put(dataPrenotazione, listaDifferenze);
						} else {
							System.out.println("LISTA DIFFERENZE VUOTA");
						}

						if (!dataPrenotazione.equals(dataFine)) {
							dataPrenotazione = dataPrenotazione.plusDays(1);
						} else {
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				ExcelMaker.createReport(mappaConfronti, Config.get("path_cartella_di_destinazione"));
			} else {
				System.out.println("PATH FILE PROPERTIES ERRATO");
			}

		}

	}
}
