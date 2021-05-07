package runner;

import java.time.LocalDate;

import model.CSVData;
import steps.Tirrenia;
import utils.CSVExtractor;
import utils.Generic;
import utils.Path;

public class StartSingleStep {

	public static void main(String[] args) {
		LocalDate dataInizio = null;
		LocalDate dataFine = null;
		
		dataInizio = Generic.setDataInizio("ALTA");
		dataFine = Generic.setDataFine("ALTA");

		LocalDate dataPrenotazione = dataInizio;
		CSVData testData = CSVExtractor.getTestDataById("TestCase1", Path.PATH);
		Generic.setDataPrenotazioneNelModelCSV(testData, dataPrenotazione);
		try {
			Tirrenia.stepTirrenia(testData);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
