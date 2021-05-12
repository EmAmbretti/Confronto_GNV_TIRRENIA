package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validation {

	public static boolean propertiesFilePathCheck(String propertiesFilePath) {
		// Check Properties File
		File f = new File(propertiesFilePath);
		if (!f.exists() || f.isDirectory() || !f.getAbsolutePath().endsWith(".properties")) {
			System.out.println("ERRORE V007: Path file properties errato o inesistente.");
			return false;
		} else {
			System.out.println("Path file properties corretto.");
			Config.setInputStream(propertiesFilePath);
			return true;
		}
	}

	public static boolean propertiesFileValuesCheck() {
		boolean flag = false;

		if (checkCompetitor()) {
			if (checkOrario()) {
				if (checkStagione()) {
					if(csvFilePathCheck()) {
						if(csvHeaderDataCheck()) {
							if (folderDestinationCheck()) {
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	private static boolean checkCompetitor() {
		boolean flag = true;
		String competitor = Config.get("competitor");

		switch (competitor.toUpperCase()) {
		case "MOBY":
		case "CF":
		case "CORSICA FERRIES":
		case "TIRRENIA":
		case "GNV":
		case "GRANDI NAVI VELOCI":
			break;
		default:
			System.out.println("COMPETITOR NON RICONOSCIUTO");
			flag = false;
			break;
		}
		return flag;
	}
	
	private static boolean checkVeicoli(String veicolo) {
		boolean flag = true;
		switch (veicolo.toUpperCase()) {
		case "MOTO":
		case "CAR":
		case "VEI 5 MT":
		case "CMP":
		case "NO":
		case "VEI 7 MT":
			break;
		default:
			System.out.println("VEICOLO NON RICONOSCIUTO");
			flag = false;
			break;
		}
		return flag;
	}
	
	private static boolean checkSistemazione(String sistemazione) {
		boolean flag = true;
		
		sistemazione = sistemazione.toUpperCase();
		if(!sistemazione.equalsIgnoreCase("CAB. INTERNA") && !sistemazione.equalsIgnoreCase("CAB. ESTERNA") && !sistemazione.equalsIgnoreCase("PONTE") && !sistemazione.contains("POLTRON")) {
			flag = false;
		}
		return flag;
	}

	private static boolean checkStagione() {
		String stagione = Config.get("stagione");

		String dataInizioAltaStagione = Config.get("inizio_alta_stagione");
		String dataFineAltaStagione = Config.get("fine_alta_stagione");

		String dataInizioBassaStagione = Config.get("inizio_bassa_stagione");
		String dataFineBassaStagione = Config.get("fine_bassa_stagione");

		boolean flag = true;
		if (stagione.equalsIgnoreCase("ALTA") || stagione.equalsIgnoreCase("BASSA") || stagione.equalsIgnoreCase("ALTO")
				|| stagione.equalsIgnoreCase("BASSO")) {

			String regex = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}";
			Pattern regexPattern = Pattern.compile(regex);
			if (stagione.equalsIgnoreCase("ALTA") || stagione.equalsIgnoreCase("ALTO")) {

				if (!regexPattern.matcher(dataInizioAltaStagione).matches()
						|| !regexPattern.matcher(dataFineAltaStagione).matches()) {
					System.out.println("FORMATO DATA NON VALIDO");
					flag = false;
				}
			} else if (stagione.equalsIgnoreCase("BASSA") || stagione.equalsIgnoreCase("BASSO")) {
				if (!regexPattern.matcher(dataInizioBassaStagione).matches()
						|| !regexPattern.matcher(dataFineBassaStagione).matches()) {
					System.out.println("FORMATO DATA NON VALIDO");
					flag = false;
				}
			}
		} else {
			System.out.println("STAGIONE NON RICONOSCIUTA");
			flag = false;
		}
		return flag;
	}

	private static boolean checkOrario() {
		boolean flag = true;

		String fasciaOraria = Config.get("fasciaOraria");

		String inizioOrarioDiurno = Config.get("inizio_orario_diurno");
		String fineOrarioDiurno = Config.get("fine_orario_diurno");

		String inizioOrarioNotturno = Config.get("inizio_orario_notturno");
		String fineOrarioNotturno = Config.get("fine_orario_notturno");

		String regex = "[0-9]{1,2}:[0-9]{2}";
		Pattern regexPattern = Pattern.compile(regex);
		if (fasciaOraria.equalsIgnoreCase("DIURNO") || fasciaOraria.equalsIgnoreCase("DIURNA")) {
			if (!regexPattern.matcher(inizioOrarioDiurno).matches()
					|| !regexPattern.matcher(fineOrarioDiurno).matches()) {
				System.out.println("FORMATO ORARIO NON VALIDO");
				flag = false;
			}
		} else if (fasciaOraria.equalsIgnoreCase("NOTTURNO") || fasciaOraria.equalsIgnoreCase("NOTTURNA")) {
			if (!regexPattern.matcher(inizioOrarioNotturno).matches()
					|| !regexPattern.matcher(fineOrarioNotturno).matches()) {
				System.out.println("FORMATO ORARIO NON VALIDO");
				flag = false;
			}
		} else {
			System.out.println("FASCIA ORARIA NON RICONOSCIUTA");
			flag = false;
		}

		return flag;
	}

	private static boolean folderDestinationCheck() {
		// Check folder destination path
		String folderDestinationPath = Config.get("path_cartella_di_destinazione");
		boolean flag = false;
		File f = new File(folderDestinationPath);
		if (!f.exists() || !f.isDirectory()) {
			System.out.println("ERRORE V008: Path cartella di salvataggio file errata o inesistente.");
		} else {
			System.out.println("Path cartella di salvataggio file inserita correttamente");
			flag = true;
		}
		return flag;
	}

	private static boolean csvFilePathCheck() {
		String csvPath = Config.get("path_csv");
		// Check CSV File
		File f = new File(csvPath);
		if (!f.exists() || f.isDirectory() || !f.getAbsolutePath().endsWith(".csv")) {
			System.out.println("ERRORE V006: PATH CSV errato o inesistente.");
			return false;
		} else {
			System.out.println("PATH CSV corretto.");
			return true;
		}
	}
	
	private static boolean csvHeaderDataCheck() {
		String csvPath = Config.get("path_csv");
		try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
			List<String[]> list = new ArrayList<>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] array = line.split(",");
				list.add(array);
			}

			try {
				boolean flagCheckHeader = true;
				if (list.get(0).length == 6) {
					// CONTROLLO A CASCATA DELL'HEADER
					if (list.get(0)[0].contains("ID")) {
						if (list.get(0)[1].equalsIgnoreCase("PASSADULTI")) {
							if (list.get(0)[2].equalsIgnoreCase("PASSBAMBINI")) {
								if (list.get(0)[3].equalsIgnoreCase("PASSANIMALI")) {
									if (list.get(0)[4].equalsIgnoreCase("VEICOLI")) {
										if (list.get(0)[5].equalsIgnoreCase("SISTEMAZIONE")) {
											System.out.println("HEADER CSV CORRETTO");
										} else {
											flagCheckHeader = false;
											System.out.println(
													"ERRORE V004: ELEMENTO 6 DELL'HEADER DIVERSO DA SISTEMAZIONE");
										}
									} else {
										flagCheckHeader = false;
										System.out.println("ERRORE V004: ELEMENTO 5 DELL'HEADER DIVERSO DA VEICOLI");
									}
								} else {
									flagCheckHeader = false;
									System.out.println("ERRORE V004: ELEMENTO 4 DELL'HEADER DIVERSO DA PASSANIMALI");
								}
							} else {
								flagCheckHeader = false;
								System.out.println("ERRORE V004: ELEMENTO 3 DELL'HEADER DIVERSO DA PASSBAMBINI");
							}
						} else {
							flagCheckHeader = false;
							System.out.println("ERRORE V004: ELEMENTO 2 DELL'HEADER DIVERSO DA PASSADULTI");
						}
					} else {
						flagCheckHeader = false;
						System.out.println("ERRORE V004: ELEMENTO 1 DELL'HEADER DIVERSO DA ID");
					}
				} else {
					flagCheckHeader = false;
					System.out.println("ERRORE V003: NUMERO COLONNE CSV ERRATO");
				}

				return flagCheckHeader;
			} catch (Exception e) {
				System.out.println("ERRORE VE002: " + e);
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			System.out.println("ERRORE VE001: " + e);
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean csvRowCheck(String[] rigaCsv, int numeroRiga) {
		boolean flag = false;
		try {
					if (rigaCsv[0] != null && !rigaCsv[0].isEmpty()) {
						if (rigaCsv[1] != null && !rigaCsv[1].isEmpty()) {
							if(!Pattern.compile("[0-9]{1}").matcher(rigaCsv[1]).matches()) {
								System.out.println("RIGA CSV num."+numeroRiga+" ERRATA: PassAdulti inserito in modo errato");
								return false;
							}
							if (rigaCsv[2] != null && !rigaCsv[2].isEmpty()) {
								if(!Pattern.compile("[0-9]{1}").matcher(rigaCsv[2]).matches()) {
									System.out.println("RIGA CSV num."+numeroRiga+" ERRATA: PassBambini inserito in modo errato");
									return false;
								}
								if (rigaCsv[3] != null && !rigaCsv[3].isEmpty()) {
									if(!Pattern.compile("[0-9]{1}").matcher(rigaCsv[3]).matches()) {
										System.out.println("RIGA CSV num."+numeroRiga+" ERRATA: PassAnimali inserito in modo errato");
										return false;
									}
									if (rigaCsv[4] != null && !rigaCsv[4].isEmpty()) {
										if(!checkVeicoli(rigaCsv[4])) {
											System.out.println("RIGA CSV num."+numeroRiga+" ERRATA: Veicolo inserito in modo errato");
											return false;
										}
										if (rigaCsv[5] != null && !rigaCsv[5].isEmpty()) {
											if(!checkSistemazione(rigaCsv[5])) {
												System.out.println("RIGA CSV num."+numeroRiga+" ERRATA: Sistemazione inserita in modo errato");
												return false;
											} else {
												System.out.println("RIGA CSV num."+numeroRiga+" CORRETTA");
												flag = true;
											}
										} else {
											System.out.println(
													"ERRORE V002: Sistemazione non presente o vuoto in riga "
															+ numeroRiga);
										}
									} else {
										System.out.println("ERRORE V002: Veicoli non presente o vuoto in riga "
												+ numeroRiga);
									}
								} else {
									System.out.println(
											"ERRORE V002: PassAnimali non presente o vuoto in riga "
													+ numeroRiga);
								}
							} else {
								System.out.println(
										"ERRORE V002: PassBambini non presente o vuoto in riga " + numeroRiga);
							}
						} else {
							System.out.println("ERRORE V002: PassAdulti non presente o vuoto in riga " + numeroRiga);
						}
					} else {
						System.out.println("ERRORE V002: ID non presente o vuoto in riga " + numeroRiga);
					}
		} catch (
		Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
