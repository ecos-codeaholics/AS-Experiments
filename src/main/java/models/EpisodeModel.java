package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import helpers.JsonEpisodeHelper;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {

	private MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();

	public boolean addEpisode(JsonEpisodeHelper data) {
		
        String date;
        String time;
		String activity;
		String medicament;
        int intensity;
        int userId;

        Document episode = new Document();
		
        date = data.getFecha();
        time = data.getHora();
        intensity = data.getNivelDolor();
        userId = data.getCedula();
		activity = data.getActividad();
		medicament = data.getMedicamento();

        episode.append("fecha", date)
		.append("hora", time)
		.append("intensidad", intensity)
		.append("cedula", userId)
		.append("medicamento", medicament)
		.append("actividad", activity);

		try {
			MongoCollection<Document> episodeCollection = db.getCollection("episode");

			System.out.println("Saving data episode");
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
