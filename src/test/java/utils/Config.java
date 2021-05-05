package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static final Properties properties = new Properties();
	
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

}
