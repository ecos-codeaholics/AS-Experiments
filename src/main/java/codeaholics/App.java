package codeaholics;
import static helpers.JsonHelper.json;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import config.DatabaseSingleton;
import controllers.ApiIncidentController;
import controllers.UserController;
import spark.servlet.SparkApplication;

/**
 * Created by snaphuman on 6/6/16.
 */
public class App implements SparkApplication{

    public static void main (String[] args) {

    }
    
    @Override
    public void init() {
    	// Initialize Database Connection
        DatabaseSingleton.getInstance();

        staticFiles.location("/public");

        get("/login", UserController::login);
        get("/signup", UserController::signup);
        post("/signup", UserController::createUser);

        // Api rest for movile integration
        post("/api/episode/create", ApiIncidentController::create, json());
    	
    }
}
