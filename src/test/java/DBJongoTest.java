import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.net.UnknownHostException;
import config.DatabaseSingleton;
import helpers.JsonEpisodeHelper;

public class DBJongoTest {

	private Jongo jongo;
	private MongoCollection allEpisodes;
	Logger log = LogManager.getRootLogger();

	@Before 
	public void setUp() throws UnknownHostException {
		
		DatabaseSingleton dbOne = DatabaseSingleton.getInstance();
		jongo = dbOne.getODM();
		log.info("Jongo objtect: " + jongo);
		allEpisodes = jongo.getCollection("episodes");
		log.info(allEpisodes.toString());
	}
	
	@Test
	public void testGetODM() {
				
		DatabaseSingleton dbOne = DatabaseSingleton.getInstance();
		FindIterable<Document> iterable = dbOne.getDatabase().getCollection("episodes").find();

		com.mongodb.client.MongoCursor<Document> all = iterable.iterator();
		int counter = 0;
		while( all.hasNext()) {
			all.next();
			counter += 1;
		}
		int jongo_counter = (int) allEpisodes.count();
				
		Assert.assertEquals(counter, jongo_counter);
	
	}
	
	@Test
	public void testFindByCedula() {
		
		// Testing using Episode class
		MongoCollection friends = jongo.getCollection("episodes");

		MongoCursor<Episode> all = friends.find("{ cedula: 94418861 }").as(Episode.class);
		int counter = all.count();
		
		Assert.assertEquals(1, counter);
		
		Episode one = all.next();
		
		Assert.assertEquals(94418861, (int)one.getCedula());

	
	}

	@Test
	public void testFindByCedulaExt() {
		
		// Testing using Episode class
		MongoCollection friends = jongo.getCollection("episodes");

		MongoCursor<JsonEpisodeHelper> all = friends.find("{ cedula: 94418861 }").as(JsonEpisodeHelper.class);
		int counter = all.count();
		
		Assert.assertEquals(1, counter);
		
		JsonEpisodeHelper one = all.next();
		
		Assert.assertEquals(94418861, (int)one.getCedula());

	
	}
	
}
