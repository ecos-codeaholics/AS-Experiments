package controllers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import codeAholics.Authentication;
import helpers.JsonEpisodeHelper;
import helpers.LoginHelper;
import models.EpisodeModel;
import spark.Request;
import spark.Response;

/**
 * Created by snaphuman on 6/7/16.
 */
public class ApiIncidentController {

	// Atributos
	private static EpisodeModel episodes = new EpisodeModel();

	private static Gson GSON = new GsonBuilder()
			.serializeNulls()
			.create();

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
			
		} catch (JsonSyntaxException e) {
			res.status(400);
			return "invalid json format";
		}

		req.body();
		return "success";
	}

    // TODO: User must have to be authorized, Doctor Role.
	/***
	 * Obtain episodes from user id.
	 *
	 * @param req Request
	 * @param res Response
	 * @return Serialized Json object with list of episodes
	 */
    public static String getById (Request req, Response res) {

        JsonEpisodeHelper data = GSON.fromJson(req.body(), JsonEpisodeHelper.class);

        List<JsonEpisodeHelper> dataset =  episodes.getList(data.getCedula());

        Type type = new TypeToken<List<JsonEpisodeHelper>>() {}.getType();

        String json = GSON.toJson(dataset, type);

        return json;
    }
   
    /* duplicated functionality - there is arleady a login
    public static String doLogin(Request req, Response res) {
    	
    	try {

    		LoginHelper data = GSON.fromJson(req.body(), LoginHelper.class);
    		String result = null;
    		boolean authenticated = Authentication.doUserAuthentication(data.getEmail(), data.getPassword());
    		if (authenticated) {
    			result = "true";
    		} else {
    			result = "false";
    		}
    		return result;
			
		} catch (JsonSyntaxException e) {
			res.status(400);
			return "invalid json format";
		}
	}
	*/
}
