package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpers.JsonEpisodeHelper;
import models.EpisodeModel;
import spark.Request;
import spark.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static helpers.ViewsHelper.render;

/**
 * Created by snaphuman on 7/11/16.
 */
public class IncidentController {

    private static EpisodeModel episodes = new EpisodeModel();

    private static Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    public static String formEpisodes (Request req, Response res) {

        HashMap<String, Object> params = new HashMap<>();

        params.put("title", "Find migraine episodes");

        return render("episodes.ftl", params);
    }

    public static String findEpisodes (Request req, Response res) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date start = new Date();
        Date end = new Date();

        int cedula = Integer.parseInt(req.queryParams("cedula"));
        String startDate = req.queryParams("start-date");
        String endDate = req.queryParams("end-date");

        if (startDate.isEmpty() && endDate.isEmpty()) {
            episodes.getList(cedula);
        } else {
            System.out.println("Entra por aca");

            try {
                start = formatter.parse(startDate);
                end = formatter.parse(endDate);
            } catch (ParseException e ) {
                e.printStackTrace();
            }

            List<JsonEpisodeHelper> dataset = episodes.getListByDate (cedula, start, end);

            HashMap<String, Object> params = new HashMap<>();

            params.put("title", "Results");

            for (JsonEpisodeHelper item : dataset) {
                params.put(item.NAME_ACTIVIDAD, item.getActividad());
                params.put(item.NAME_CEDULA, item.getCedula());
                params.put(item.NAME_FECHA, item.getFecha());
                params.put(item.NAME_HORA, item.getHora());
                params.put(item.NAME_INTENSIDAD, item.getNivelDolor());
                params.put(item.NAME_MEDICAMENTO, item.getMedicamento());
            }

            return render("episodes-list.ftl", params);
        }


        return "success";
    }
}
