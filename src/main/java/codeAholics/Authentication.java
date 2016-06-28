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
	/***
	 * Valida si lo datos corresponden a un paciente registrado.
	 * 
	 * @param pEmail correo del ususario
	 * @param pPwd contraeeña del ususario
	 * @return resultado de la autenticacion
	 */
	public static boolean autPatients(String pEmail, String pPwd) {

		boolean authenticated = false;
		String hiddenPwd = Utilities.getHash(pPwd);

		Document user = new Document();
		user.append("email", pEmail);
		user.append("password", hiddenPwd);

		ArrayList<Document> results = Utilities.findRegisters(user, "user");
		if (results.size() > 0) {
			log.info("Autenticacion Exitosa para " + pEmail);
			createSesion(pEmail);
			authenticated = true;
		}
		return authenticated;
	}

	/***
	 * Valida si lo datos corresponden a un doctor registrado.
	 * 
	 * @param pEmail correo del ususario
	 * @param pPwd contraeeña del ususario
	 * @return resultado de la autenticacion
	 */
	public static boolean autDocs(String pUser, String pPwd) {
		// se consulta en la bd si el doc exite
		return true;
	}

	/***
	 * Crea una sesion para un usuario dado su email.
	 * 
	 * @param pEmail correo del ususario al que se le crea la sesion
	 */
	private static void createSesion(String pEmail) {
		
		Document sesion = new Document();
		sesion.append("email", pEmail);
		log.info("Creando Sesion");
		try {
			Utilities.addRegister(sesion, "sesion");
			
		} catch (MongoWriteException e) {

			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				log.error("Already exist sesion for user: : " + pEmail);
			}
			throw e;
		}
	}

	// private static void closedSesion(String pEmail){
	// System.out.println("Cerrando Sesion");
	// db.getCollection("sesion").deleteMany(new Document("email", pEmail));
	// }

}