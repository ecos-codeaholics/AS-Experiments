package config;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

/**
 * Created by snaphuman on 6/6/16.
 */
public class DatabaseConfig {

    public MongoDatabase mongo() throws MongoException {

        final MongoClient mongoClient = new MongoClient("localhost", 27017);
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("migraine");
        return mongoDatabase;
    }
}
