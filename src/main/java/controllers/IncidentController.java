package controllers;

import models.EpisodeModel;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static helpers.ViewsHelper.render;

/**
 * Created by snaphuman on 7/11/16.
 */
public class IncidentController {

    private static EpisodeModel episodes = new EpisodeModel();

    public static String formEpisodes (Request req, Response res) {

        HashMap<String, Object> params = new HashMap<>();

        params.put("title", "Find migraine episodes");

        return render("episodes.ftl", params);
    }

    public static String findEpisodes (Request req, Response res) {

        int cedula = Integer.parseInt(req.queryParams("cedula"));
        String startDate = req.queryParams("start-date");
        String endDate = req.queryParams("end-date");

        System.out.println(cedula);
        System.out.println(startDate);
        System.out.println(endDate);

        return "";
    }
}
