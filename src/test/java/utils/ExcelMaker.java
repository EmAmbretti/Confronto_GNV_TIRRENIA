package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Differenza;

public class ExcelMaker {

	private static CellStyle errorStyle = null;
	private static CellStyle warningStyle = null;
	private static CellStyle standardStyle = null;
	private static CellStyle tinyStyle = null;
	private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH.mm");
	private static DateTimeFormatter formatterDateTimeFileName = DateTimeFormatter.ofPattern("dd_MM_yyyy-HH_mm");
	
	public static void createReport(TreeMap<LocalDate, ArrayList<Differenza>> mappaConfronti, String path) {
		System.out.println("INIZIO CREAZIONE EXCEL");
		boolean flag = true;
		
		// CONDIZIONI DA AGGIUNGERE
		if (mappaConfronti == null || mappaConfronti.isEmpty()) {
			flag = false;
		}

		if (flag) {
			try {
				LocalDateTime dateTime = LocalDateTime.now();
				String filename = path + File.separator + "Esito_Confronto_Grimaldi_" + dateTime.format(formatterDateTimeFileName);
				
				boolean flagForFile = true;
				int numberForFile = 0;
				String filenameTemp = filename;
				
				while(flagForFile) {
					numberForFile ++;
					File f = new File(filenameTemp+".xlsx");
					try {
						if(f!=null) {
							if(f.exists()) {
								filenameTemp = filename + " ("+numberForFile+")";
							} else {
								filename = filenameTemp;
								flagForFile = false;
							}
						} 
					} catch (Exception e) {
						filename = filenameTemp;
						flagForFile = false;
					}
				}
				
				filename += ".xlsx";
				XSSFWorkbook fileExcel = new XSSFWorkbook();
				XSSFSheet foglio = fileExcel.createSheet("Esito_Confronto_Grimaldi_" + dateTime.format(formatterDateTime));
				//HSSFWorkbook fileExcel = new HSSFWorkbook();
				//HSSFSheet foglio = fileExcel.createSheet("Esito " + dateTime.format(formatterDateTime));
				
				createStyles(fileExcel);
				
				Row riga0 = foglio.createRow((short) 0);
				riga0.createCell(0).setCellValue("Up to: ");
				
				System.out.println(dateTime);
				
				riga0.createCell(2).setCellValue(formatterDateTimeFileName.format(dateTime));
				riga0.createCell(4).setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getGrimaldi().getDatiCsv().getTrattaAndata());
				riga0.createCell(6).setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getGrimaldi().getDatiCsv().getStagione());
				riga0.createCell(8).setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getGrimaldi().getDatiCsv().getFasciaOraria());
				riga0.createCell(10).setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getCompetitor().getSito());
				
				// GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
				
				int x=0;
				for (LocalDate data : mappaConfronti.keySet()) {

					Row rigaIntestazione1 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione1 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione1 = foglio.getRow((short) (x));
					}
					
					// RIGA1 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
					rigaIntestazione1.createCell(1).setCellValue("COMBINAZIONI");
					rigaIntestazione1.createCell(4).setCellValue("PARTENZA");
					rigaIntestazione1.createCell(7).setCellValue("SCONTI");
					rigaIntestazione1.createCell(11).setCellValue("LIV.");
					
					Row rigaIntestazione2 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione2 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione2 = foglio.getRow((short) (x));
					}
					
					// rigaIntestazione2 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
					rigaIntestazione2.createCell(1).setCellValue("Pax");;
					rigaIntestazione2.createCell(2).setCellValue("Veicolo");
					rigaIntestazione2.createCell(3).setCellValue("Sistemazione");
					rigaIntestazione2.createCell(4).setCellValue(data.getDayOfMonth()+"/"+data.getMonthValue()+"/"+data.getYear());
					rigaIntestazione2.createCell(7).setCellValue("Ch");
					rigaIntestazione2.createCell(8).setCellValue("ND");
					rigaIntestazione2.createCell(9).setCellValue("ND");
					rigaIntestazione2.createCell(10).setCellValue("ND");
					rigaIntestazione2.createCell(11).setCellValue("pid");
					
					Row rigaIntestazione3 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione3 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione3 = foglio.getRow((short) (x));
					}
					
					// rigaIntestazione3 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
					rigaIntestazione3.createCell(4).setCellValue("GL Scraping");
					rigaIntestazione3.createCell(5).setCellValue("GL Formula");
					rigaIntestazione3.createCell(6).setCellValue(mappaConfronti.get(data).get(0).getCompetitor().getSito());
					rigaIntestazione3.createCell(7).setCellValue("Δ");
					rigaIntestazione3.createCell(11).setCellValue("Δ%");
					
					x = fillExcelWithDifferences(mappaConfronti.get(data), foglio, x) + 2;
				}
						
				FileOutputStream fileOut = new FileOutputStream(filename);
				fileExcel.write(fileOut);
				fileOut.close();
				fileExcel.close();
				System.out.println("Excel Creato");
			} catch (Exception ex) {
				System.out.println("Errore durante la creazione dell'Excel");
				System.out.println("ERRORE EME002: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	
	}

	private static int fillExcelWithDifferences(ArrayList<Differenza> diff, XSSFSheet foglio, int rowNumber) {
		int index;
		for (index = 0; index < diff.size(); index++) {
			rowNumber++;
			Row riga = null;
			if (foglio.getRow(rowNumber) == null) {
				riga = foglio.createRow((short) (rowNumber));
			} else {
				riga = foglio.getRow((short) (rowNumber));
			}

			try {
				
				// RIGA ITERATA
				Cell passeggeri = riga.createCell(1);
				Cell veicolo = riga.createCell(2);
				Cell sistemazione = riga.createCell(3);
				Cell prezzoGL = riga.createCell(4);
				Cell prezzoCompetitor = riga.createCell(6);
				Cell differenza = riga.createCell(7);
				Cell percentualeDifferenza = riga.createCell(11);

				// CDT ID
				if(diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriBambini()==null) {
					passeggeri.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + " adulti");
				} else {
					passeggeri.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + "ad + "+diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriBambini()+" ch");
				}
				veicolo.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getVeicolo());
				sistemazione.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getSistemazione());
				if(diff.get(index).getGrimaldi().getErrori()!=null) {
					prezzoGL.setCellValue(diff.get(index).getGrimaldi().getPrezzo());
				} else {
					prezzoGL.setCellValue(diff.get(index).getGrimaldi().getErrori());
				}
				if(diff.get(index).getCompetitor().getErrori()!=null) {
					prezzoCompetitor.setCellValue(diff.get(index).getCompetitor().getPrezzo());
				} else {
					prezzoCompetitor.setCellValue(diff.get(index).getCompetitor().getErrori());
				}
				
				if(diff.get(index).getGrimaldi().getErrori()!=null && diff.get(index).getCompetitor().getErrori()!=null) {
					differenza.setCellValue(diff.get(index).getDifferenzaPrezzo());
					percentualeDifferenza.setCellValue(diff.get(index).getDifferenzaPrezzoPercentuale());
				} else {
					differenza.setCellValue("\\");
					percentualeDifferenza.setCellValue("\\");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowNumber;
	}
	
	private static void createStyles(XSSFWorkbook fileExcel) {

		errorStyle = fileExcel.createCellStyle();
		errorStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		errorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(errorStyle);
		// errorStyle.setWrapText(true);

		warningStyle = fileExcel.createCellStyle();
		warningStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		warningStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(warningStyle);

		standardStyle = fileExcel.createCellStyle();
		setBorder(standardStyle);

		tinyStyle = fileExcel.createCellStyle();
		setBorder(tinyStyle);
		Font tiny = fileExcel.createFont();
		tiny.setFontHeightInPoints((short) 8);
		tiny.setItalic(true);
		tinyStyle.setFont(tiny);
	}
	
	private static void setBorder(CellStyle style) {
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		/*
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);*/
	}
	
}
