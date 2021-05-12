package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
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
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Differenza;

public class ExcelMaker {

	private static CellStyle percStyle = null;
	private static CellStyle greenBGStyle = null;
	private static CellStyle greenFontStyle = null;
	private static CellStyle redFontStyle = null;
	private static CellStyle errorStyle = null;
	private static CellStyle warningStyle = null;
	private static CellStyle standardStyle = null;
	private static CellStyle tinyStyle = null;
	private static CellStyle lightBlueBGStyle = null;
	private static CellStyle turquoiseBGStyle = null;
	private static CellStyle lightTurquoiseBGStyle = null;
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
				String filename = path + File.separator + "Esito_Confronto_Grimaldi_"+Config.get("competitor")+"_"+Config.get("tratta")+"_"
						+ dateTime.format(formatterDateTimeFileName);

				boolean flagForFile = true;
				int numberForFile = 0;
				String filenameTemp = filename;

				while (flagForFile) {
					numberForFile++;
					File f = new File(filenameTemp + ".xlsx");
					try {
						if (f != null) {
							if (f.exists()) {
								filenameTemp = filename + " (" + numberForFile + ")";
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
				XSSFSheet foglio = fileExcel
						.createSheet(Config.get("tratta")+"_"+Config.get("fasciaOraria"));
				// HSSFWorkbook fileExcel = new HSSFWorkbook();
				// HSSFSheet foglio = fileExcel.createSheet("Esito " +
				// dateTime.format(formatterDateTime));

				////////////////
				createStyles(fileExcel);
				foglio.setColumnWidth(7, 900);
				foglio.setColumnWidth(8, 900);
				foglio.setColumnWidth(9, 900);
				foglio.setColumnWidth(10, 900);
				////////////////

				Row riga0 = foglio.createRow((short) 0);

				foglio.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
				foglio.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
				foglio.addMergedRegion(new CellRangeAddress(0, 0, 6, 7));
				foglio.addMergedRegion(new CellRangeAddress(0, 0, 8, 10));

				riga0.createCell(0).setCellValue("Up to: ");
				
				riga0.createCell(1).setCellValue(formatterDateTime.format(dateTime));
				riga0.getCell(1).setCellStyle(standardStyle);
				
				riga0.createCell(2).setCellStyle(standardStyle);
				
				riga0.createCell(3).setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getGrimaldi()
						.getDatiCsv().getTrattaAndata());
				riga0.getCell(3).setCellStyle(standardStyle);
				
				riga0.createCell(4).setCellStyle(standardStyle);
				riga0.createCell(5).setCellStyle(standardStyle);
				riga0.createCell(6).setCellStyle(standardStyle);
				
				riga0.createCell(6).setCellValue(Config.get("stagione"));
				riga0.getCell(6).setCellStyle(standardStyle);
				
				riga0.createCell(7).setCellStyle(standardStyle);
				
				riga0.createCell(8).setCellValue(Config.get("fasciaOraria").toUpperCase());
				
				riga0.createCell(9).setCellStyle(standardStyle);
				riga0.createCell(10).setCellStyle(standardStyle);
				
				riga0.createCell(11)
						.setCellValue(mappaConfronti.get(mappaConfronti.firstKey()).get(0).getCompetitor().getSito());
				riga0.getCell(11).setCellStyle(standardStyle);

				int x = 0;
				for (LocalDate data : mappaConfronti.keySet()) {
					Row rigaIntestazione1 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione1 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione1 = foglio.getRow((short) (x));
					}

					foglio.addMergedRegion(
							new CellRangeAddress(rigaIntestazione1.getRowNum(), rigaIntestazione1.getRowNum(), 1, 3));
					foglio.addMergedRegion(
							new CellRangeAddress(rigaIntestazione1.getRowNum(), rigaIntestazione1.getRowNum(), 4, 6));
					foglio.addMergedRegion(
							new CellRangeAddress(rigaIntestazione1.getRowNum(), rigaIntestazione1.getRowNum(), 7, 10));

					// RIGA1 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA ?
					rigaIntestazione1.createCell(1).setCellValue("COMBINAZIONI");
					rigaIntestazione1.getCell(1).setCellStyle(turquoiseBGStyle);

					rigaIntestazione1.createCell(2).setCellStyle(standardStyle);
					rigaIntestazione1.createCell(3).setCellStyle(standardStyle);
					
					rigaIntestazione1.createCell(4).setCellValue("PARTENZA");
					rigaIntestazione1.getCell(4).setCellStyle(lightBlueBGStyle);
					
					rigaIntestazione1.createCell(5).setCellStyle(standardStyle);
					rigaIntestazione1.createCell(6).setCellStyle(standardStyle);
					
					rigaIntestazione1.createCell(7).setCellValue("SCONTI");
					rigaIntestazione1.getCell(7).setCellStyle(lightBlueBGStyle);
					
					rigaIntestazione1.createCell(8).setCellStyle(standardStyle);
					rigaIntestazione1.createCell(9).setCellStyle(standardStyle);
					rigaIntestazione1.createCell(10).setCellStyle(standardStyle);
					rigaIntestazione1.createCell(11).setCellStyle(standardStyle);
					
					rigaIntestazione1.createCell(11).setCellValue("LIV.");
					rigaIntestazione1.getCell(11).setCellStyle(lightBlueBGStyle);

					Row rigaIntestazione2 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione2 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione2 = foglio.getRow((short) (x));
					}

					foglio.addMergedRegion(
							new CellRangeAddress(rigaIntestazione2.getRowNum(), rigaIntestazione2.getRowNum(), 4, 6));

					// rigaIntestazione2 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA
					// ?
					rigaIntestazione2.createCell(1).setCellValue("Pax");
					rigaIntestazione2.getCell(1).setCellStyle(lightBlueBGStyle);
					
					rigaIntestazione2.createCell(2).setCellValue("Veicolo");
					rigaIntestazione2.getCell(2).setCellStyle(lightBlueBGStyle);
					
					rigaIntestazione2.createCell(3).setCellValue("Sistemazione");
					rigaIntestazione2.getCell(3).setCellStyle(lightBlueBGStyle);
					
					rigaIntestazione2.createCell(4)
							.setCellValue(data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear());
					rigaIntestazione2.getCell(4).setCellStyle(turquoiseBGStyle);
					
					rigaIntestazione2.createCell(5).setCellStyle(standardStyle);
					rigaIntestazione2.createCell(6).setCellStyle(standardStyle);
					
					rigaIntestazione2.createCell(7).setCellValue("Ch");
					rigaIntestazione2.getCell(7).setCellStyle(greenBGStyle);
					
					rigaIntestazione2.createCell(8).setCellValue("ND");
					rigaIntestazione2.getCell(8).setCellStyle(greenBGStyle);
					
					rigaIntestazione2.createCell(9).setCellValue("ND");
					rigaIntestazione2.getCell(9).setCellStyle(greenBGStyle);
					
					rigaIntestazione2.createCell(10).setCellValue("ND");
					rigaIntestazione2.getCell(10).setCellStyle(greenBGStyle);

					rigaIntestazione2.createCell(11).setCellValue("pld");
					rigaIntestazione2.getCell(11).setCellStyle(standardStyle);

					Row rigaIntestazione3 = null;
					x++;
					if (foglio.getRow(x) == null) {
						rigaIntestazione3 = foglio.createRow((short) (x));
					} else {
						rigaIntestazione3 = foglio.getRow((short) (x));
					}

					foglio.addMergedRegion(
							new CellRangeAddress(rigaIntestazione3.getRowNum(), rigaIntestazione3.getRowNum(), 7, 10));

					// rigaIntestazione3 GESTIRE COMBINAZIONE DATA / TRATTA / PREZZO, CON UNA MAPPA
					// ?
					rigaIntestazione3.createCell(4).setCellValue("GL PrezzoWeb");
					rigaIntestazione3.getCell(4).setCellStyle(lightTurquoiseBGStyle);

					rigaIntestazione3.createCell(5).setCellValue("GL PriceList");
					rigaIntestazione3.getCell(5).setCellStyle(lightTurquoiseBGStyle);
					
					rigaIntestazione3.createCell(6).setCellValue(mappaConfronti.get(data).get(0).getCompetitor().getSito());
					rigaIntestazione3.getCell(6).setCellStyle(lightTurquoiseBGStyle);
					
					rigaIntestazione3.createCell(7).setCellValue("Δ PrezzoWeb");
					rigaIntestazione3.getCell(7).setCellStyle(lightTurquoiseBGStyle);
					
					rigaIntestazione3.createCell(11).setCellValue("Δ% PrezzoWeb");
					rigaIntestazione3.getCell(11).setCellStyle(lightTurquoiseBGStyle);
					
					rigaIntestazione3.createCell(12).setCellValue("Δ PriceList");
					rigaIntestazione3.getCell(12).setCellStyle(lightTurquoiseBGStyle);
					
					rigaIntestazione3.createCell(13).setCellValue("Δ% PriceList");
					rigaIntestazione3.getCell(13).setCellStyle(lightTurquoiseBGStyle);
					
					x = fillExcelWithDifferences(mappaConfronti.get(data), foglio, x) + 2;

				}

				for (int i = 0; i <= 15; i++) {
					foglio.autoSizeColumn(i);
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
		} else {
			System.out.println("MAPPA CONFRONTI VUOTA");
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

				foglio.addMergedRegion(new CellRangeAddress(riga.getRowNum(), riga.getRowNum(), 7, 10));

				
				
				// RIGA ITERATA
				Cell passeggeri = riga.createCell(1);
				Cell veicolo = riga.createCell(2);
				Cell sistemazione = riga.createCell(3);
				Cell prezzoGLScraping = riga.createCell(4);
				Cell prezzoGLFormula = riga.createCell(5);
				Cell prezzoCompetitor = riga.createCell(6);
				Cell differenzaScraping = riga.createCell(7);
				Cell percentualeDifferenzaScraping = riga.createCell(11);
				Cell differenzaFormula = riga.createCell(12);
				Cell percentualeDifferenzaFormula = riga.createCell(13);
								
				passeggeri.setCellStyle(standardStyle);
				veicolo.setCellStyle(standardStyle);
				sistemazione.setCellStyle(standardStyle);
				passeggeri.setCellStyle(standardStyle);
				prezzoGLScraping.setCellStyle(standardStyle);
				prezzoGLFormula.setCellStyle(standardStyle);
				prezzoCompetitor.setCellStyle(standardStyle);
				differenzaScraping.setCellStyle(standardStyle);
				percentualeDifferenzaScraping.setCellStyle(standardStyle);
				differenzaFormula.setCellStyle(standardStyle);
				percentualeDifferenzaFormula.setCellStyle(standardStyle);
				
				riga.createCell(8).setCellStyle(standardStyle);
				riga.createCell(9).setCellStyle(standardStyle);
				riga.createCell(10).setCellStyle(standardStyle);

				// CDT ID
				if (diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriBambini() == null) {
					passeggeri
							.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + " adulti");
				} else {
					passeggeri.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriAdulti() + "ad + "
							+ diff.get(index).getGrimaldi().getDatiCsv().getPasseggeriBambini() + " ch");
				}
				veicolo.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getVeicolo());
				sistemazione.setCellValue(diff.get(index).getGrimaldi().getDatiCsv().getSistemazione());
				if (diff.get(index).getGrimaldi().getErrori() == null) {
					prezzoGLScraping.setCellValue("€ " + (diff.get(index).getGrimaldi().getPrezzo()).replace(".", ","));
				} else {
					prezzoGLScraping.setCellValue(diff.get(index).getGrimaldi().getErrori());
				}
				if (diff.get(index).getCompetitor().getErrori() == null) {
					prezzoCompetitor.setCellValue("€ " + diff.get(index).getCompetitor().getPrezzo().replace(".", ","));
				} else {
					prezzoCompetitor.setCellValue(diff.get(index).getCompetitor().getErrori());
				}

				if (diff.get(index).getGrimaldi().getErrori() == null
						&& diff.get(index).getCompetitor().getErrori() == null) {
			        DecimalFormat df = new DecimalFormat("###.##");
					String diffScrapString="€ "+String.valueOf(df.format(diff.get(index).getDifferenzaPrezzo()));
					diffScrapString = diffScrapString.replace(".", ",");
					differenzaScraping.setCellValue(diffScrapString);
					
					String percDiffScrapString=String.valueOf(Math.abs(diff.get(index).getDifferenzaPrezzoPercentuale()))+" %";
					percDiffScrapString=percDiffScrapString.replace(".", ",");
					percentualeDifferenzaScraping.setCellValue(percDiffScrapString);
					
					
					if (diff.get(index).getDifferenzaPrezzo() < 0) {
						differenzaScraping.setCellStyle(greenFontStyle);
						percentualeDifferenzaScraping
								.setCellValue("- " + diff.get(index).getDifferenzaPrezzoPercentuale() + " %");
						percentualeDifferenzaScraping.setCellStyle(greenFontStyle);
					} else if (diff.get(index).getDifferenzaPrezzo() > 0) {
						differenzaScraping.setCellStyle(redFontStyle);
						percentualeDifferenzaScraping.setCellStyle(redFontStyle);
					}
				} else {
					differenzaScraping.setCellValue("\\");
					percentualeDifferenzaScraping.setCellValue("\\");
				}
				differenzaFormula.setCellFormula("IF(F"+(prezzoGLFormula.getRowIndex()+1)+"=\"\", \"\", SUM(F"+(prezzoGLFormula.getRowIndex()+1)+"-G"+(prezzoGLFormula.getRowIndex()+1)+"))");
				//percentualeDifferenzaFormula.setCellFormula("IF(AND(M"+(prezzoGLFormula.getRowIndex()+1)+"=\"\",G"+(prezzoGLFormula.getRowIndex()+1)+"=\"\"), \"\", M"+(prezzoGLFormula.getRowIndex()+1)+"/G"+(prezzoGLFormula.getRowIndex()+1)+")");
				percentualeDifferenzaFormula.setCellFormula("IF(OR(M"+(prezzoGLFormula.getRowIndex()+1)+"=\"\",G"+(prezzoGLFormula.getRowIndex()+1)+"=\"\"), \"\", M"+(prezzoGLFormula.getRowIndex()+1)+"/G"+(prezzoGLFormula.getRowIndex()+1)+")");
				percentualeDifferenzaFormula.setCellStyle(percStyle);
				//percentualeDifferenzaFormula.setCellFormula("SE(ISERROR(IF(AND(M"+(prezzoGLFormula.getRowIndex()+1)+"=\"\",G"+(prezzoGLFormula.getRowIndex()+1)+"=\"\"), \"\", M"+(prezzoGLFormula.getRowIndex()+1)+"/G"+(prezzoGLFormula.getRowIndex()+1)+")),\"\",M"+(prezzoGLFormula.getRowIndex()+1)+"/G"+(prezzoGLFormula.getRowIndex()+1)+")");
																					//=SE(VAL.ERRORE(SE(E(M5="";G5=""); ""; M5/G5));"";M5/G5)
																					//=IF(AND(H+INDEX="";G+INDEX=""); ""; H+INDEX/G+INDEX)
																					//=SE(VAL.ERR(SE(E(M6="";G6=""); SE(VAL.ERRORE(#NOME?);"";""); M6/G6));"";M6/G6)
																					//=SE(VAL.ERR(SE(E(M7="";G7=""); ""; M7/G7));"";M7/G7)
				
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

		redFontStyle = fileExcel.createCellStyle();
		Font red = fileExcel.createFont();
		red.setColor(IndexedColors.RED.getIndex());
		redFontStyle.setFont(red);
		setBorder(redFontStyle);
		redFontStyle.setAlignment(HorizontalAlignment.CENTER);

		greenFontStyle = fileExcel.createCellStyle();
		Font green = fileExcel.createFont();
		green.setColor(IndexedColors.GREEN.getIndex());
		greenFontStyle.setFont(green);
		setBorder(greenFontStyle);
		greenFontStyle.setAlignment(HorizontalAlignment.CENTER);
		
		percStyle = fileExcel.createCellStyle();
		percStyle.setDataFormat((short) 0xa);
		setBorder(percStyle);
		percStyle.setAlignment(HorizontalAlignment.CENTER);
		
		greenBGStyle = fileExcel.createCellStyle();
		greenBGStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		greenBGStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(greenBGStyle);
		
		turquoiseBGStyle = fileExcel.createCellStyle();
		turquoiseBGStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		turquoiseBGStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(turquoiseBGStyle);
		turquoiseBGStyle.setAlignment(HorizontalAlignment.CENTER);
		
		lightBlueBGStyle = fileExcel.createCellStyle();
		lightBlueBGStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		lightBlueBGStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font white = fileExcel.createFont();
		white.setColor(IndexedColors.WHITE.getIndex());
		lightBlueBGStyle.setFont(white);
		setBorder(lightBlueBGStyle);
		lightBlueBGStyle.setAlignment(HorizontalAlignment.CENTER);
		
		lightTurquoiseBGStyle = fileExcel.createCellStyle();
		lightTurquoiseBGStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		lightTurquoiseBGStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(lightTurquoiseBGStyle);
		lightTurquoiseBGStyle.setAlignment(HorizontalAlignment.CENTER);

		warningStyle = fileExcel.createCellStyle();
		warningStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		warningStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		setBorder(warningStyle);

		standardStyle = fileExcel.createCellStyle();
		setBorder(standardStyle);
		standardStyle.setAlignment(HorizontalAlignment.CENTER);

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
		 * style.setBorderBottom(CellStyle.BORDER_THIN);
		 * style.setBorderTop(CellStyle.BORDER_THIN);
		 * style.setBorderRight(CellStyle.BORDER_THIN);
		 * style.setBorderLeft(CellStyle.BORDER_THIN);
		 */
	}

}
