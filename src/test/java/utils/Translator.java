package utils;

import model.EsitoSito;

public class Translator {
	
	public static String modificaComune(String comune, EsitoSito sito) {
		String output = null;
		if(sito.getSito().toUpperCase().equals("CORSICA FERRIES")) {
			switch (comune.toUpperCase()) {
			case "OLBIA":
				output = "G.Aranci";
			default:
				return null;
			}
		}
		return output;
	}

	public static String translate(String italiano) {
		switch (italiano.toUpperCase()) {
		case "LIVORNO":
			return "LIVOURNE";
		case "OLBIA":
			return "GOLFO ARANCI";
		default:
			return null;
		}
	}
	
}
