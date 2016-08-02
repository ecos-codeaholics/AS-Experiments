package controllers;

import codeAholics.Authentication;
import codeAholics.IAuthenticationSvc;
import models.UserModel;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static helpers.ViewsHelper.render;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserController {

	// Atributos
	private static UserModel users = new UserModel();

	// Metodos
	/***
	 * Carga pagina de login.
	 * 
	 * @param req request
	 * @param res response
	 * @return estrututa de pagina
	 */
	public static String login(Request req, Response res) {

		HashMap<String, Object> params = new HashMap<>();

		params.put("title", "Login");

		return render("login.ftl", params);
	}
	
	/***
	 * Llama al metodo de autenticacion con lo datos que recupera del formulario.
	 * 
	 * @param req request
	 * @param res response
	 * @return resultado de la autenticacion
	 */
	public static String doLogin(Request req, Response res) {
		String email = req.queryParams("email");
		String password = req.queryParams("password");
		
		IAuthenticationSvc authenticate = new Authentication();
		
		boolean authenticated = authenticate.doAuthentication(email, password);
		
		if (authenticated) {
			HashMap<String, Object> params = new HashMap<>();
			params.put("title", "Sign up");
			params.put("msg", "Success");
			return render("signup.ftl", params);
		} else {
			HashMap<String, Object> params = new HashMap<>();
			params.put("title", "Login");
			return render("login.ftl", params);
		}

	}

	/***
	 * Carga la pagina de registro de usuarios.
	 * 
	 * @param req request
	 * @param res response
	 * @return estrututa de pagina
	 */
	public static String signup(Request req, Response res) {

		HashMap<String, Object> params = new HashMap<>();
		params.put("title", "Sign up");

		return render("signup.ftl", params);
	}

	/***
	 * Llama al metodos de crear ususarios con los datos recuperados del formulario.
	 * 
	 * @param req request
	 * @param res response
	 * @return resultado de la autenticacion
	 */
	public static String createUser(Request req, Response res) {

		String email = req.queryParams("email");
		String password = req.queryParams("password");
		String name = req.queryParams("name");
		String lastName = req.queryParams("last-name");
		String identification = req.queryParams("identification");
		String rol = req.queryParams("rol");

		HashMap<String, Object> params = new HashMap<>();
		params.put("title", "Sign up");

		users.addUser(name, lastName, password, email, identification, rol);

		params.put("msg", "Success");

		return render("signup.ftl", params);
	}
}
