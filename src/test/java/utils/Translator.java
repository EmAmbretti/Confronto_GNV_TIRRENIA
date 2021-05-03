package utils;

import java.util.HashMap;

import model.EsitoSito;

public class Translator {
	
	private static String editCity(String comune, EsitoSito sito) {
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

	private static String translateCity(String italiano) {
		switch (italiano.toUpperCase()) {
		case "LIVORNO":
			return "LIVOURNE";
		case "OLBIA":
			return "GOLFO ARANCI";
		default:
			return null;
		}
	}
	
	public static HashMap<String, String> modificaTratta(EsitoSito sito){
		HashMap<String, String> mappaComuni = new HashMap<String, String>();
		
		String[] tratta=sito.getDatiCsv().getTrattaAndata().split(" - ");
		String partenza = tratta[0];
		String arrivo = tratta[1];
		
		if(editCity(partenza, sito)!=null) {
			mappaComuni.put("partenza", editCity(partenza, sito));
		} else {
			mappaComuni.put("partenza", partenza);
		}
		
		if(editCity(arrivo, sito)!=null) {
			mappaComuni.put("arrivo", editCity(arrivo, sito));
		} else {
			mappaComuni.put("arrivo", arrivo);
		}
		
		return mappaComuni;
	}
	
	public static HashMap<String, String> traduciTratta(EsitoSito sito){
		HashMap<String, String> mappaComuni = new HashMap<String, String>();
		
		String[] tratta=sito.getDatiCsv().getTrattaAndata().split(" - ");
		String partenza = tratta[0];
		String arrivo = tratta[1];
		
		if(translateCity(partenza)!=null) {
			mappaComuni.put("partenza", translateCity(partenza));
		} else {
			mappaComuni.put("partenza", partenza);
		}
		
		if(translateCity(arrivo)!=null) {
			mappaComuni.put("arrivo", translateCity(arrivo));
		} else {
			mappaComuni.put("arrivo", arrivo);
		}
		
		return mappaComuni;
	}
	
}
