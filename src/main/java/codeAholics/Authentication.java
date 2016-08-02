package codeAholics;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;

/**
 * Created by davidMtz on 26/6/16.
 */
public final class Authentication implements IAuthenticationSvc {

	// Atributos
	private final static Logger log = LogManager.getLogger(Authentication.class);

	private String answerStr;
	
	public static boolean autDocs(String pUser, String pPwd) {
		// se consulta en la bd si el doc exite
		return true;
	}

	/**
	 * * Crea una sesion para un usuario dado su email.
	 *
	 * @param pEmail: correo del ususario al que se le crea la sesion
	 */
	private static void createSesion(String pEmail, String pUserProfile) {

		Document sesion = new Document();
		sesion.append("email", pEmail);
		sesion.append("user-profile", pUserProfile);
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

	// Metodos
	/**
	 * * Valida si lo datos corresponden a un paciente o doctor registrado.
	 *
	 * @param pEmail
	 *            correo del ususario
	 * @param pPwd
	 *            contraeeña del ususario
	 * @return resultado de la autenticacion
	 */
	@Override
	public boolean doAuthentication(String pEmail, String pPwd) {

		boolean authenticated = false;
		log.info("Verifying user data...");
		Document user = new Document();
		user.append("email", pEmail);
		String userType = "patient";

		ArrayList<Document> documents = Utilities.findRegisters(user, "user");

		if (documents.isEmpty()) {
			log.info("User Doesn't Exist");
		} else {
			user.append("user-profile", "user");
			ArrayList<Document> documents2 = Utilities.findRegisters(user, "user");
			if (documents2.isEmpty()) {
				log.info("User Profile Wrong");
			} else {
				String salt = documents.get(0).get("salt").toString();
				String[] hash = Utilities.getHash(pPwd, salt);
				userType = documents.get(0).get("type").toString();
				
				user.append("password", hash[1]);

				ArrayList<Document> results = Utilities.findRegisters(user, "user");
				if (results.size() > 0) {
					log.info(pEmail + " authenticated!");
					createSesion(pEmail, userType);
					authenticated = true;
					answerStr = "{status : \"Ok\"}";
				} else {
					log.info("Wrong password");
				}
			}
		}

		return authenticated;

	}

	@Override
	public String getAnswer() {
		return answerStr;
	}

}
