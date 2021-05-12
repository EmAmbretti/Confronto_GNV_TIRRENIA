package runner;

import java.time.LocalDate;

import model.CSVData;
import steps.CorsicaFerries;
import steps.Tirrenia;
import utils.CSVExtractor;
import utils.Generic;
import utils.Path;

public class StartSingleStep {

	public static void main(String[] args) {
		/*
		 * testData=CSVExtractor.getTestDataById("TC11", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 * testData=CSVExtractor.getTestDataById("TC12", Path.PATH);
		 * CorsicaFerries.automation(testData);
		 */
		
		LocalDate dataInizio = null;
		LocalDate dataFine = null;
		
		dataInizio = Generic.setDataInizio("ALTA");
		dataFine = Generic.setDataFine("ALTA");

		LocalDate dataPrenotazione = dataInizio;
		CSVData testData = null;
		
		testData = CSVExtractor.getTestDataById("TC9", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData = CSVExtractor.getTestDataById("TC10", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData = CSVExtractor.getTestDataById("TC11", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData = CSVExtractor.getTestDataById("TC12", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData = CSVExtractor.getTestDataById("TC13", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC14", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC15", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC16", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC17", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC18", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		
		testData=CSVExtractor.getTestDataById("TC19", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		CorsicaFerries.automation(testData);
		/*
		
		TreeMap<LocalDate, ArrayList<Differenza>> mappaConfronti = new TreeMap<LocalDate, ArrayList<Differenza>>();
		
		ArrayList<CSVData> datiCSV = CSVExtractor.process(Path.PATH);
		
		LocalDate dataInizio = null;
		LocalDate dataFine = null;
		
		dataInizio = Generic.setDataInizio("ALTA");
		dataFine = Generic.setDataFine("ALTA");

		LocalDate dataPrenotazione = dataInizio;
		for (int i = 0; i < datiCSV.size(); i++) {
			
			int numeroDiGiorniDaLanciare = Generic.controlloStagione(Config.get("stagione"));
			
			ArrayList<Differenza> listaDifferenze = new ArrayList<Differenza>();
			
			int prezzoDaEliminare = 10;
			
			for (int x = 0; x <= numeroDiGiorniDaLanciare; x++) {
				System.out.println("X: "+x+", DATA PRENOTAZIONE: "+dataPrenotazione);
				Generic.setDataPrenotazioneNelModelCSV(datiCSV.get(i), dataPrenotazione);
				
				EsitoSito esitoGrimaldi = new EsitoSito("grimaldi", datiCSV.get(i));
				EsitoSito esitoTirrenia = new EsitoSito("tirrenia", datiCSV.get(i));

				esitoGrimaldi.setPrezzo(String.valueOf(100 + (prezzoDaEliminare * (x+1))));
				esitoTirrenia.setPrezzo(String.valueOf(120 + (prezzoDaEliminare * (x+1))));
				
				Differenza diff = new Differenza(esitoGrimaldi, esitoTirrenia);
				listaDifferenze.add(diff);
			}

			if (!dataPrenotazione.equals(dataFine)) {
				System.out.println("AUMENTO LA DATAAAAAAAAAAAAAAAAAA");
				dataPrenotazione = dataPrenotazione.plusDays(1);
			} else {
				break;
			}

			if(!listaDifferenze.isEmpty()) {
				mappaConfronti.put(dataPrenotazione, listaDifferenze);
			} else {
				System.out.println("LISTA DIFFERENZE VUOTA");
			}
		}
		ExcelMaker.createReport(mappaConfronti, "C:\\Users\\giovanni.sorrentino\\Desktop");*/
	}

}
