package models;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import helpers.JsonEpisodeHelper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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

	public List<JsonEpisodeHelper> getList (int id) {

		try {

			List<JsonEpisodeHelper> dataset = new ArrayList<>();

			MongoCollection<Document> episodeCollection = db.getCollection("episode");
			FindIterable<Document> episodes = episodeCollection.find(eq("cedula", id));

			for (Document episode: episodes) {
				JsonEpisodeHelper json = new JsonEpisodeHelper();

				json.setId(episode.get("_id").toString());
				json.setActividad(episode.get("actividad").toString());
				json.setNivelDolor((int) episode.get("intensidad"));
				json.setCedula((int) episode.get("cedula"));
				json.setFecha(episode.get("fecha").toString());
				json.setHora(episode.get("hora").toString());
				json.setMedicamento(episode.get("medicamento").toString());

				dataset.add(json);
				System.out.println(json.getMedicamento());
			}

			return dataset;
		} catch (MongoWriteException e) {

			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				System.out.println("Error");
				return new ArrayList<>();
			}
			throw e;

		}
	}
}
