/** Copyright or License
 *
 */

package config;
/**
 * Package: config
 *
 * Class: DatabaseSingleton DatabaseSingleton.java
 * 
 * Original Author: @author AOSORIO
 * 
 * Description: Database singleton 
 * 
 * Implementation: Configuration taken from DatabaseConfig
 *
 * Created: Jun 8, 2016 8:15:14 AM
 * 
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DatabaseSingleton {

	private static DatabaseSingleton instance = null;
	private static MongoClient mongoClient = null;
	private static MongoDatabase mongoDatabase = null; 
	
	protected DatabaseSingleton() {
		mongoClient = new MongoClient(DatabaseConfig.DB_SERVER, DatabaseConfig.DB_SERVER_PORT);
		mongoDatabase = mongoClient.getDatabase(DatabaseConfig.DB_NAME);
	}

	public static DatabaseSingleton getInstance() {
		if (instance == null) {
			instance = new DatabaseSingleton();
		}
		return instance;
	}

	public MongoDatabase getDatabase() {
		return mongoDatabase;
	}
		
}
