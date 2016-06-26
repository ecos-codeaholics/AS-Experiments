package codeAholics;
import controllers.ApiIncidentController;
import config.DatabaseSingleton;
import controllers.UserController;

import static helpers.JsonHelper.json;
import static spark.Spark.*;

/**
 * Created by snaphuman on 6/6/16.
 */
public class App {

	public static void main(String[] args) {

		// Initialize Database Connection
		DatabaseSingleton.getInstance();
		staticFiles.location("/public");

		//rutas de acceso web
		get("/login", UserController::login);
		post("/login", UserController::doLogin);
		get("/signup", UserController::signup);
		post("/signup", UserController::createUser);

		// Api rest for movile integration
		post("/api/episode/create", ApiIncidentController::create, json());
		
		//esto es para las funcioanlideades del doctor
		before("/doc/*", (request, response) -> {
			boolean authorized = false;

			if (!authorized) {
				halt(401, "You are not welcome here");
			}
		});


	}

}
