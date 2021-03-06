package utils;

import model.EsitoSito;

public class Translator {
	
	private static String editCity(String comune, EsitoSito sito) {
		String output = null;
		if(sito.getSito().toUpperCase().equals("CORSICA FERRIES")) {
			switch (comune.toUpperCase()) {
			case "OLBIA":
				output = "G.Aranci";
				break;
			default:
				return null;
			}
		}
		return output;
	}

	private static String translateCity(String italiano) {
		switch (italiano.toUpperCase()) {
		case "LIVORNO":
			return "LIVOURNE";
		case "G.ARANCI":
			return "GOLFO ARANCI";
		default:
			return null;
		}
	}
	
	public static void modificaTratta(EsitoSito sito){
		try {
			if(editCity(sito.getDatiCsv().getComunePartenza(), sito)!=null) {
				sito.getDatiCsv().setComunePartenza(editCity(sito.getDatiCsv().getComunePartenza(), sito));
			}
			
			if(editCity(sito.getDatiCsv().getComuneArrivo(), sito)!=null) {
				sito.getDatiCsv().setComuneArrivo(editCity(sito.getDatiCsv().getComuneArrivo(), sito));
			}
		} catch (Exception e) {
			System.out.println("ERRORE NELLA MODIFICA TRATTA PER: "+sito.getDatiCsv().getComunePartenza() + " - "+sito.getDatiCsv().getComuneArrivo());
			e.printStackTrace();
		}
	}
	
	public static void traduciTratta(EsitoSito sito){
		try {
			if(translateCity(sito.getDatiCsv().getComunePartenza())!=null) {
				sito.getDatiCsv().setComunePartenza(translateCity(sito.getDatiCsv().getComunePartenza()));
			} 
	
			if(translateCity(sito.getDatiCsv().getComuneArrivo())!=null) {
				sito.getDatiCsv().setComuneArrivo(translateCity(sito.getDatiCsv().getComuneArrivo()));
			}
		} catch (Exception e) {
			System.out.println("ERRORE NELLA TRADUZIONE TRATTA PER: "+sito.getDatiCsv().getComunePartenza() + " - "+sito.getDatiCsv().getComuneArrivo());
			e.printStackTrace();
		}
	}
	
}
