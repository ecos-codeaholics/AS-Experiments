package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;

import codeAholics.Utilities;
import helpers.JsonEpisodeHelper;

/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {
	
	// Atributos
	private final static Logger log = LogManager.getLogger(EpisodeModel.class);

	// Metodos
	public boolean addEpisode(JsonEpisodeHelper data) {

		Document episode = new Document();
		String date = data.getFecha();
		String time = data.getHora();
		int intensity = data.getIntensidad();
		int userId = data.getCedula();

		episode.append("date", date).append("time", time).append("intensity", intensity).append("userId", userId);
		log.info("Saving episode...");

		try {
			Utilities.addRegister(episode, "episode");
			return true;
		} catch (MongoWriteException e) {

			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				log.error("Duplicate episode");
				return false;
			}
			throw e;

		}
	}
}
