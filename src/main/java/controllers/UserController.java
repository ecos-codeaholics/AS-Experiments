package controllers;

import models.UserModel;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static helpers.ViewsHelper.render;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserController {

    private static UserModel users = new UserModel();

    public static String login(Request req, Response res) {

        HashMap<String, Object> params = new HashMap<>();

        params.put("title", "Login");

        return render("login.ftl", params);
    }

    public static String signup(Request req, Response res) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("title", "Sign up");

        return render("signup.ftl", params);
    }

    public static String createUser(Request req, Response res) {

        String email = req.queryParams("email");
        String password = req.queryParams("password");
        String name = req.queryParams("name");
        String lastName = req.queryParams("last-name");

        HashMap<String, Object> params = new HashMap<>();
        params.put("title", "Sign up");

        users.addUser(name, lastName, password, email);

        params.put("msg", "Success");

        return render("signup.ftl", params);
    };
}
