package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import helpers.JsonEpisodeHelper;

import org.bson.Document;

/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {

	private MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();

	public boolean addEpisode(JsonEpisodeHelper data) {
		
        String date;
        String time;
        int intensity;
        int userId;
        
        Document episode = new Document();
		
        date = data.getFecha();
        time = data.getHora();
        intensity = data.getIntensidad();
        userId = data.getCedula();
        
        episode.append("date", date)
		.append("time", time)
		.append("intensity", intensity)
		.append("userId", userId);

		try {
			MongoCollection<Document> episodeCollection = db.getCollection("episode");

			System.out.println("Guardando en modelo");
			episodeCollection.insertOne(episode);
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
