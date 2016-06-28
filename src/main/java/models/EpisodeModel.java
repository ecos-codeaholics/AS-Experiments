package models;

import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;

import codeAholics.Utilities;
import helpers.JsonEpisodeHelper;

/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {

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

		episode.append("date", date).append("time", time).append("intensity", intensity).append("userId", userId);
		System.out.println("Guardando en Episodio");

		try {
			Utilities.addRegister(episode, "episode");
			System.out.println("Proceso Exitoso");
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
