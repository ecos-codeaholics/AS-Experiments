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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jongo.Jongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseSingleton {

	// Atributos
	private static DatabaseSingleton instance = null;	
	private static MongoClient mongoClient = null;
	private static MongoDatabase mongoDatabase = null;
	private static DB dbClient = null;
	private static Jongo jongoAccess = null;
	private static DatabaseConfig databaseConfig = null;
	
	private final static Logger log = LogManager.getLogger(DatabaseSingleton.class);
	
	// Constructores
	@SuppressWarnings({ "deprecation", "resource" })
	protected DatabaseSingleton() {
		
		databaseConfig = new DatabaseConfig();
		
		mongoClient = new MongoClient( databaseConfig.getDbServerUrl(), Integer.parseInt(databaseConfig.getDbPort()) );
		mongoDatabase = mongoClient.getDatabase(databaseConfig.getDbName());
		
		//JONGO
		dbClient = new MongoClient().getDB(databaseConfig.getDbName());
		jongoAccess = new Jongo(dbClient);
		log.info( jongoAccess.toString());
	}

	// Metodos
	public static DatabaseSingleton getInstance() {
		if (instance == null) {
			instance = new DatabaseSingleton();
		}
		return instance;
	}

	public MongoDatabase getDatabase() {
		return mongoDatabase;
	}

	public Jongo getODM() {
		return jongoAccess;
	}

	public DatabaseConfig getConfig() {
		return databaseConfig;
	}
}
