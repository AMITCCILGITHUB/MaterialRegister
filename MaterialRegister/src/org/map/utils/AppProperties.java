package org.map.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
	private static final String appPropertiesFile = "resources/app.properties";

	private static final Properties prop = new Properties();

	static {
		try {
			FileInputStream propStream = new FileInputStream(appPropertiesFile);
			prop.load(propStream);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static String getValue(String key) {

		return prop.get(key).toString().trim();
	}

	public static void setValue(String key, String value)
			throws FileNotFoundException, IOException {

		prop.setProperty(key, value);
		prop.store(new FileOutputStream(appPropertiesFile), null);
	}
}
