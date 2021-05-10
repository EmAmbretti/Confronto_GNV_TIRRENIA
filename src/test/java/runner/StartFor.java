package runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

import model.CSVData;
import model.Differenza;
import model.EsitoSito;
import steps.CorsicaFerries;
import steps.GrimaldiLines;
import steps.Tirrenia;
import utils.CSVExtractor;
import utils.Config;
import utils.ExcelMaker;
import utils.Generic;
import utils.Path;

public class StartFor {

	public static void main(String[] args) {
		//CSVData testData = null;
		/*
		 * testData=CSVExtractor.getTestDataById("TC11", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC12", Path.PATH);
		 * CorsicaFerries.automation(testData);

		testData = CSVExtractor.getTestDataById("TC13", Path.PATH);
		CorsicaFerries.automation(testData);*/
		/*
		 * testData=CSVExtractor.getTestDataById("TC14", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC15", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC16", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC17", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC18", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC19", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 */
		TreeMap<LocalDate, ArrayList<Differenza>> mappaConfronti = new TreeMap<LocalDate, ArrayList<Differenza>>();

		ArrayList<CSVData> datiCSV = CSVExtractor.process(Path.PATH);

		LocalDate dataInizio = null;
		LocalDate dataFine = null;

		dataInizio = Generic.setDataInizio("ALTA");
		dataFine = Generic.setDataFine("ALTA");

		LocalDate dataPrenotazione = dataInizio;
		
		int numeroDiGiorniDaLanciare = Generic.controlloStagione(Config.get("stagione"));
		
		for (int i = 0; i <= numeroDiGiorniDaLanciare; i++) {
			System.out.println("i: "+i);

			System.out.println(dataPrenotazione);

			ArrayList<Differenza> listaDifferenze = new ArrayList<Differenza>();

			int prezzoDaEliminare = 10;
			for (int x = 0; x < datiCSV.size(); x++) {
				System.out.println("X: "+x+", DATA PRENOTAZIONE: "+dataPrenotazione);
				Generic.setDataPrenotazioneNelModelCSV(datiCSV.get(x), dataPrenotazione);
				/*
				EsitoSito esitoGrimaldi = null;
				EsitoSito esitoTirrenia = null;
				try {
					esitoGrimaldi = GrimaldiLines.stepGrimaldi(datiCSV.get(i));
				} catch (Throwable e) {
					e.printStackTrace();
				}

				try {
					esitoTirrenia = Tirrenia.stepTirrenia(datiCSV.get(i));
				} catch (Throwable e) {
					e.printStackTrace();
				}
				 */
				//EsitoSito esitoGrimaldi = new EsitoSito("grimaldi", datiCSV.get(x));
				//EsitoSito esitoTirrenia = new EsitoSito("tirrenia", datiCSV.get(x));
				
				
				
				///////////////////test con step
				
				EsitoSito esitoGrimaldi = null;
				try {
					esitoGrimaldi = GrimaldiLines.stepGrimaldi(datiCSV.get(x));
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				EsitoSito esitoTirrenia = null;
//				try {
//					esitoTirrenia = Tirrenia.stepTirrenia(datiCSV.get(x));
//				} catch (Throwable e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				EsitoSito esitoCorsicaFerries= null;
				try {
					esitoCorsicaFerries = CorsicaFerries.automation(datiCSV.get(x));
				}catch(Throwable e) {
					e.printStackTrace();
				}
				
				//////////////////////fine test con step
				
				
				
				
				//////////////////// test senza step
				
//				EsitoSito esitoGrimaldi = new EsitoSito("GRIMALDI", datiCSV.get(0));
//				EsitoSito esitoTirrenia = new EsitoSito("TIRRENIA", datiCSV.get(0));
//				esitoGrimaldi.setPrezzo(String.valueOf(100 + (prezzoDaEliminare * (x+1))));
//				esitoTirrenia.setPrezzo(String.valueOf(120 + (prezzoDaEliminare * (x+1))));
				
				
				/////////////////////fine test senza step

				
				

				Differenza diff = new Differenza(esitoGrimaldi, esitoCorsicaFerries);
				listaDifferenze.add(diff);
				
			}

			if(!listaDifferenze.isEmpty()) {
				mappaConfronti.put(dataPrenotazione, listaDifferenze);
			} else {
				System.out.println("LISTA DIFFERENZE VUOTA");
			}
			
			if (!dataPrenotazione.equals(dataFine)) {
				System.out.println("AUMENTO LA DATAAAAAAAAAAAAAAAAAA");
				dataPrenotazione = dataPrenotazione.plusDays(1);
			} else {
				break;
			}

			
		}
		ExcelMaker.createReport(mappaConfronti, "C:\\Users\\mirko.terracciano\\Desktop\\ReportTest");
	}
}
