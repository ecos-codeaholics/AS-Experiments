package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import helpers.JsonEpisodeHelper;
import codeAholics.Utilities;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by snaphuman on 6/8/16.
 */
public class EpisodeModel {

	// Atributos
	private final static Logger log = LogManager.getLogger(EpisodeModel.class);

	// Metodos
	public boolean addEpisode(JsonEpisodeHelper data) {

		String date = data.getFecha();
		String time = data.getHora();
		int intensity = data.getNivelDolor();
		int userId = data.getCedula();
		String medicament = data.getMedicamento();
		String activity = data.getActividad();

		Document episode = new Document();

		episode.append("fecha", date)
				.append("hora", time)
				.append("intensidad", intensity)
				.append("cedula", userId)
				.append("medicamento", medicament)
				.append("actividad", activity);

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

	public List<JsonEpisodeHelper> getList (int id) {

		try {

			List<JsonEpisodeHelper> dataset = new ArrayList<>();

			Document cedula = new Document();
			cedula.append("cedula", id);

			ArrayList<Document> episodes = Utilities.findRegisters(cedula, "episode");

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
