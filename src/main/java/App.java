import controllers.UserController;

import static spark.Spark.*;

/**
 * Created by snaphuman on 6/6/16.
 */
public class App {

    public static void main (String[] args) {

        staticFiles.location("/public");

        get("/login", UserController::login);
        get("/signup", UserController::signup);
        post("/signup", UserController::createUser);
    }
}
