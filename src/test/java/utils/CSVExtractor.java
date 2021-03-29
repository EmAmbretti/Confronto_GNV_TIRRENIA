package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVExtractor {

	public static ArrayList<CSVData> process(String path) {
		ArrayList<CSVData> datiOfferte = new ArrayList<CSVData>();
		try (BufferedReader reader = new BufferedReader(
				new FileReader(path))) {
			List<String[]> list = new ArrayList<>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] array = line.split(",");
				list.add(array);
			}
			for (int x = 1; x < list.size(); x++) {
				CSVData csvObject = new CSVData();
				
				csvObject.setTipologia(list.get(x)[0]);
				if (!csvObject.getTipologia().contains("GNVGRIMALDI")) {
					csvObject.setCollegamento(list.get(x)[1]);	
				}
				csvObject.setTrattaAndata(list.get(x)[2]);
				csvObject.setMeseAndata(list.get(x)[3]);
				csvObject.setGiornoAndata(list.get(x)[4]);
				csvObject.setPasseggeriAdulti(list.get(x)[5]);
				csvObject.setPasseggeriBambini(list.get(x)[6]);
				csvObject.setPasseggeriAnimali(list.get(x)[7]);
				csvObject.setVeicolo(list.get(x)[8]);

				datiOfferte.add(csvObject);
			}
		} catch (Exception e) {
			System.out.print(", ERRORE");
			e.printStackTrace();
		}
		/*
		for (CSVData csvobj : datiOfferte) {
			System.out.println(csvobj.toString());
		}*/
		return datiOfferte;
	}
	
//	public static CSVData getTestData(String offer, String type, String metaOfferId, String path) {
//
//		ArrayList<CSVData> csvData = CSVExtractor.process(path);
//
//		int dataIndex = -1;
//		for (int i = 0; i < csvData.size(); i++) {
//			if (csvData.get(i).getOfferta().equalsIgnoreCase(offer)
//					&& csvData.get(i).getTipologia().equalsIgnoreCase(type)
//					&& csvData.get(i).getId().equalsIgnoreCase(metaOfferId)	) {
//				dataIndex = i;
//				break;
//			}
//		}
//		return csvData.get(dataIndex);
//	}
//	
//	public static CSVData getTestDataByNameAndId(String offer, String metaOfferId, String path) {
//
//		ArrayList<CSVData> csvData = CSVExtractor.process(path);
//
//		int dataIndex = -1;
//		for (int i = 0; i < csvData.size(); i++) {
//			if (csvData.get(i).getOfferta().equalsIgnoreCase(offer)
//					&& csvData.get(i).getId().equalsIgnoreCase(metaOfferId)	) {
//				dataIndex = i;
//				break;
//			}
//		}
//		return csvData.get(dataIndex);
//	}
//	
//	public static CSVData getTestDataById(String metaOfferId, String path) {
//
//		ArrayList<CSVData> csvData = CSVExtractor.process(path);
//
//		int dataIndex = -1;
//		for (int i = 0; i < csvData.size(); i++) {
//			if (csvData.get(i).getId().equalsIgnoreCase(metaOfferId) ) {
//				dataIndex = i;
//				break;
//			}
//		}
//		return csvData.get(dataIndex);
//	}
	
	public static CSVData getTestDataByOffer(String type, String path) {

		ArrayList<CSVData> csvData = CSVExtractor.process(path);
		System.out.println("SIZE : "+csvData.size());
		int dataIndex = -1;
		for (int i = 0; i < csvData.size(); i++) {
			if (csvData.get(i).getTipologia().equalsIgnoreCase(type) ) {
				dataIndex = i;
				break;
			}
		}
		return csvData.get(dataIndex);
	}
	
}
