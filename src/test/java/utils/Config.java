package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Properties properties = new Properties();
	
	static InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("config.properties");

	static {
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	public static void set(String key, String value) {
		properties.setProperty(key, value);
	}

	public static void setInputStream(String path) {
		inputStream = null;
    	properties = new Properties();
    	String fileName = path;
		File initialFile = new File(fileName);
		try {
			inputStream = new FileInputStream(initialFile);
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
