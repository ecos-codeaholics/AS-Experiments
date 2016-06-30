package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by snaphuman on 6/6/16.
 */

public class DatabaseConfig {

	// Atributos
	private static String dbServerUrl;
	private static String dbPort;
	private static String dbName;
	private static String dbCollection;
	
	public DatabaseConfig() { 
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {

			input = new FileInputStream("src/main/resources/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			dbServerUrl = prop.getProperty("mongo.url");
			dbPort = prop.getProperty("mongo.port");
			dbName = prop.getProperty("mongo.db");
			dbCollection = prop.getProperty("mongo.db.collection");

		} catch (IOException ex) {
			ex.printStackTrace();
			
			dbServerUrl = "localhost";
			dbPort = "27017";
			dbName = "migraines";
			dbCollection = "episodes";
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the dbServerUrl
	 */
	public String getDbServerUrl() {
		return dbServerUrl;
	}

	/**
	 * @return the dbPort
	 */
	public String getDbPort() {
		return dbPort;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @return the dbCollection
	 */
	public String getDbCollection() {
		return dbCollection;
	}
	
	
}
