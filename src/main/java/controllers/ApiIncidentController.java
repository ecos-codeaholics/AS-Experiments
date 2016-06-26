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

    private static EpisodeModel episodes = new EpisodeModel();

    private static Gson GSON = new GsonBuilder().create();

    public static String create (Request req, Response res) {

        
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

    // TODO: User must have to be authotized, Doctor Role.
    public static String getById (Request req, Response res) {

        JsonEpisodeHelper data = GSON.fromJson(req.body(), JsonEpisodeHelper.class);

        episodes.getList(data.getCedula());

        System.out.println(req.body());

        return "";
    }
}
