package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import config.DatabaseConfig;
import org.bson.Document;

/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {

    private static DatabaseConfig db = new DatabaseConfig();

    public boolean addEpisode (String data) {

        System.out.println(data);
        Document episode = new Document();

        episode.append("data", data);
        try {
            MongoCollection userCollection = db.mongo().getCollection("user");

            System.out.println("Guardando en modelo");
            userCollection.insertOne(episode);
            return true;
        } catch (MongoWriteException e) {

            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Error");
                return false;
            }
            throw e;

        }
    }
}
