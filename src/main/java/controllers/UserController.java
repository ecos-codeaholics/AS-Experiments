package controllers;

import spark.Request;
import spark.Response;

import java.util.HashMap;

import static helpers.ViewsHelper.render;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserController {

    public static String login(Request req, Response res) {

        HashMap<String, Object> params = new HashMap<>();

        params.put("title", "Login");

        return render("login.ftl", params);
    }
}
