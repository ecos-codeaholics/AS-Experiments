import controllers.UserController;

import static spark.Spark.*;

/**
 * Created by snaphuman on 6/6/16.
 */
public class App {

    public static void main (String[] args) {

        staticFiles.location("/public");

        get("/user/login", UserController::login);
        get("/user/signup", UserController::signup);
        post("/user/signup", UserController::createUser);
    }
}
