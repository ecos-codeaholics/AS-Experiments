import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import config.DatabaseConfig;
import config.DatabaseSingleton;

public class DBSingletonTest {
	
	Logger logger = LogManager.getRootLogger();
	
	@Test
	public void testUnique() {
			
		DatabaseSingleton dbOne = null;
		DatabaseSingleton dbTwo = null;

		dbOne = DatabaseSingleton.getInstance();
		logger.info("...got singleton 1: " + dbOne);
		dbTwo = DatabaseSingleton.getInstance();
		logger.info("...got singleton 2: " + dbTwo);
		logger.info("checking singletons for equality");
		
		Assert.assertEquals(true, dbOne == dbTwo);
		
	}
	
	@Test
	public void testConfiguration() {
		
		DatabaseSingleton dbOne = null;
		dbOne = DatabaseSingleton.getInstance();
		DatabaseConfig config = dbOne.getConfig();
		
		logger.info(config.getDbServerUrl());
		
		Assert.assertEquals("localhost", config.getDbServerUrl());
		
	}

}
