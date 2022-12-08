package com.stroeer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	/**
	 * Utility class to perform data read operations for test & config data
	 */

	private static Properties configProp;
	

	public static Properties loadProperties(String fileName) {
		configProp = new Properties();
		try {
			configProp.load(new FileInputStream(new File(System.getProperty("user.dir")
					+ "/src/test/resources/propertiesFile/" + fileName + ".properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProp;
	}

}
