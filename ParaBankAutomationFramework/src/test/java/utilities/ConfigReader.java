package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;

	static {

		try {

			prop = new Properties();

			InputStream fis = ConfigReader.class.getClassLoader().getResourceAsStream("config/config.properties");

			prop.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}
}