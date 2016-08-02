package codeAholics;

import config.DatabaseSingleton;
import controllers.ApiIncidentController;
import controllers.IncidentController;
import controllers.UserController;

import static helpers.JsonHelper.json;
import static spark.Spark.*;

/**
 * Created by snaphuman on 6/6/16.
 */
public class App {

	// Metodos
	/***
	 * Metodo principal del sistema.
	 * 
	 * @param args argunmentos
	 */
	public static void main(String[] args) {

		// Initialize Database Connection
		DatabaseSingleton.getInstance();
		staticFiles.location("/public");
        /*HTTPS line --- JLRM*/
        //secure("deploy/keystore.jks", "codeaholics", null, null);
        
        
		// rutas de acceso web
		get("/login", UserController::login);
		post("/login", UserController::doLogin);
		get("/signup", UserController::signup);
		post("/signup", UserController::createUser);
		get("/episodes", IncidentController::formEpisodes); // User should be logged in as doctor
		get("/episodes/id/*/from/*/to/*", IncidentController::findEpisodes); // User should be logged in as doctor

		// Rutas Api rest -- duplicated Login ?
		//post("/api/login", ApiIncidentController::doLogin, json());
		
		//For patient mobile 
		post("/api/user/create", ApiIncidentController::create, json());
		
		//For doctor mobile
		post("/api/doc/get", ApiIncidentController::getById, json());

		// verificar permisos a funcionalidades en determinandas rutas
		before("/api/doc/*", Authorization::doDocAuthorization);
		
//		before("/api/doc/*", (request, response) -> {
//		    boolean authenticated = false;
//		    // ... check if authenticated
//		    if (!authenticated) {
//		        halt(401, "You are not welcome here");
//		    }
//		});


	}
}
