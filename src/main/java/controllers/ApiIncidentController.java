package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import helpers.JsonEpisodeHelper;
import models.EpisodeModel;
import spark.Request;
import spark.Response;

/**
 * Created by snaphuman on 6/7/16.
 */
public class ApiIncidentController {

	// Atributos
	private static EpisodeModel episodes = new EpisodeModel();

	private static Gson GSON = new GsonBuilder().create();

	// Metodos
	/***
	 * Registra un episodio reportado desde app movil.
	 * 
	 * @param req request
	 * @param res response
	 * @return mensaje de porceso exitoso
	 */
	public static String create(Request req, Response res) {

		try {

			JsonEpisodeHelper data = GSON.fromJson(req.body(), JsonEpisodeHelper.class);

			episodes.addEpisode(data);

			System.out.println("Guardando Episodio");
		} catch (JsonSyntaxException e) {
			res.status(400);
			return "invalid json format";
		}

		req.body();
		return "success";
	}
}
