package codeAholics;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;

/**
 * Created by davidMtz on 26/6/16.
 */
public final class Authentication {

	// Atributos
	private final static Logger log = LogManager.getLogger(Authentication.class);

	// Metodos
	/**
	 * * Valida si lo datos corresponden a un paciente registrado.
	 *
	 * @param pEmail correo del ususario
	 * @param pPwd contraeeña del ususario
	 * @return resultado de la autenticacion
	 */
	public static boolean autPatients(String pEmail, String pPwd) {
		
		boolean authenticated = false;
		log.info("Verifying user data...");
		Document user = new Document();
		user.append("email", pEmail);

		ArrayList<Document> documents = Utilities.findRegisters(user, "user");

		if (documents.isEmpty()) {
			log.info("User Doesn't Exist");
		} else {
			String salt = documents.get(0).get("salt").toString();
			String[] hash = Utilities.getHash(pPwd, salt);

			user.append("password", hash[1]);

			ArrayList<Document> results = Utilities.findRegisters(user, "user");
			if (results.size() > 0) {
				log.info( pEmail+ " authenticated!");
				createSesion(pEmail);
				authenticated = true;
			} else {
				log.info("Wrong password");
			}

		}

		return authenticated;
	}

	/**
	 * * Valida si lo datos corresponden a un doctor registrado.
	 *
	 * @param pEmail correo del ususario
	 * @param pPwd contraeeña del ususario
	 * @return resultado de la autenticacion
	 */
	public static boolean autDocs(String pUser, String pPwd) {
		// se consulta en la bd si el doc exite
		return true;
	}

	/**
	 * * Crea una sesion para un usuario dado su email.
	 *
	 * @param pEmail correo del ususario al que se le crea la sesion
	 */
	private static void createSesion(String pEmail) {

		Document sesion = new Document();
		sesion.append("email", pEmail);
		log.info("Creating Session...");
		try {
			Utilities.addRegister(sesion, "sesion");

		} catch (MongoWriteException e) {

			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				log.info("Already exist sesion for user: " + pEmail);
			}
			throw e;
		}
	}

	// private static void closedSesion(String pEmail){
	// System.out.println("Cerrando Sesion");
	// db.getCollection("sesion").deleteMany(new Document("email", pEmail));
	// }
}
