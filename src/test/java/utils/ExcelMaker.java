package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
	
	public static void createReport(ArrayList<Differenza> confronti, String path) {
		System.out.println("INIZIO CREAZIONE EXCEL");
		boolean flag = true;
		
		// CONDIZIONI DA AGGIUNGERE
		if (confronti == null || confronti.isEmpty()) {
			flag = false;
		}

		if (flag) {
			try {
				LocalDateTime dateTime = LocalDateTime.now();
				String filename = path + File.separator + "Esito_Confronto_Grimaldi" + dateTime.format(formatterDateTimeFileName);
				
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
				XSSFSheet foglio = fileExcel.createSheet("Esito_Confronto_Grimaldi" + dateTime.format(formatterDateTime));
				//HSSFWorkbook fileExcel = new HSSFWorkbook();
				//HSSFSheet foglio = fileExcel.createSheet("Esito " + dateTime.format(formatterDateTime));
				
				createStyles(fileExcel);

				Row riga0 = foglio.createRow((short) 0);
				riga0.createCell(0).setCellValue("Up to: ");
				riga0.createCell(2).setCellValue("COSA DEVO INSERIRE ?");
				riga0.createCell(4).setCellValue(confronti.get(0).getGrimaldi().getDatiCsv().getTrattaAndata());
				riga0.createCell(6).setCellValue(confronti.get(0).getGrimaldi().getDatiCsv().getStagione());
				riga0.createCell(8).setCellValue(confronti.get(0).getGrimaldi().getDatiCsv().getFasciaOraria());
				riga0.createCell(10).setCellValue(confronti.get(0).getCompetitor().getSito());
				
				// GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
				int i = 0;
				for (i = 0; i < confronti.size(); i++) {
					
					Row riga = null;
					if (foglio.getRow(i + 1) == null) {
						riga = foglio.createRow((short) (i + 1));
					} else {
						riga = foglio.getRow((short) (i + 1));
					}

					try {
						
						// RIGA1 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
						riga.createCell(1).setCellValue("COMBINAZIONI");
						riga.createCell(4).setCellValue("PARTENZA");
						riga.createCell(6).setCellValue("SCONTI");
						riga.createCell(10).setCellValue("LIV.");
						
						// RIGA2 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
						riga.createCell(1).setCellValue("Pax");;
						riga.createCell(2).setCellValue("Veicolo");
						riga.createCell(3).setCellValue("Sistemazione");
						riga.createCell(4).setCellValue(confronti.get(i).getGrimaldi().getData());
						riga.createCell(6).setCellValue("Ch");
						riga.createCell(7).setCellValue("ND");
						riga.createCell(8).setCellValue("ND");
						riga.createCell(9).setCellValue("ND");
						riga.createCell(10).setCellValue("pid");
						
						// RIGA3 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ? 
						riga.createCell(4).setCellValue("GL");
						riga.createCell(5).setCellValue(confronti.get(i).getCompetitor().getSito());
						riga.createCell(6).setCellValue("Δ");
						riga.createCell(10).setCellValue("Δ%");
						
						// RIGA ITERATA
						Cell passeggeri = riga.createCell(1);
						Cell veicolo = riga.createCell(2);
						Cell sistemazione = riga.createCell(3);
						Cell prezzoGL = riga.createCell(4);
						Cell prezzoCompetitor = riga.createCell(5);
						Cell differenza = riga.createCell(6);
						Cell percentualeDifferenza = riga.createCell(10);

						// CDT ID
						if(confronti.get(i).getGrimaldi().getDatiCsv().getPasseggeriBambini()==null) {
							passeggeri.setCellValue(confronti.get(i).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + " adulti");
						} else {
							passeggeri.setCellValue(confronti.get(i).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + "ad + "+confronti.get(i).getGrimaldi().getDatiCsv().getPasseggeriBambini()+" ch");
						}
						veicolo.setCellValue(confronti.get(i).getGrimaldi().getDatiCsv().getVeicolo());
						sistemazione.setCellValue(confronti.get(i).getGrimaldi().getDatiCsv().getSistemazione());
						if(confronti.get(i).getGrimaldi().getErrori()!=null) {
							prezzoGL.setCellValue(confronti.get(i).getGrimaldi().getPrezzo());
						} else {
							prezzoGL.setCellValue(confronti.get(i).getGrimaldi().getErrori());
						}
						if(confronti.get(i).getCompetitor().getErrori()!=null) {
							prezzoCompetitor.setCellValue(confronti.get(i).getCompetitor().getPrezzo());
						} else {
							prezzoCompetitor.setCellValue(confronti.get(i).getCompetitor().getErrori());
						}
						
						if(confronti.get(i).getGrimaldi().getErrori()!=null && confronti.get(i).getCompetitor().getErrori()!=null) {
							differenza.setCellValue(confronti.get(i).getDifferenzaPrezzo());
							percentualeDifferenza.setCellValue(confronti.get(i).getDifferenzaPrezzoPercentuale());
						} else {
							differenza.setCellValue("\\");
							percentualeDifferenza.setCellValue("\\");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				
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
	
	public static void createStyles(XSSFWorkbook fileExcel) {

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
