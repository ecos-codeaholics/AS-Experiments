package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import codeAholics.Utilities;

import java.util.ArrayList;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

	// Atributos
	private final static Logger log = LogManager.getLogger(UserModel.class);

	// Metodos
	/**
	 * * Crea el obejto docuemento ususario y lo alamacena el la coleeccion de
	 * ususarios.
	 *
	 * @param pName correo del ususario
	 * @param pLastName contraeeña del ususario
	 * @param pPassword contraeeña del ususario
	 * @param pEmail contraeeña del ususario
	 * @return resultado de la operacion
	 */
	public boolean addUser(String pName, String pLastName, String pPassword, String pEmail) {
		boolean result = false;
		String[] hash = Utilities.getHash(pPassword, "");
		Document user = new Document();
		user.append("email", pEmail);
		user.append("password", hash[1]);
		user.append("salt", hash[0]);
		user.append("name", pName);
		user.append("last-name", pLastName);
		log.info("Creating user: " + pEmail + "...");

		ArrayList<Document> userAlreadyExist = Utilities.findRegisters(user, "user");

		if (userAlreadyExist.size() > 0) {
			log.info("User Already Exist");
		} else {
			try {
				Utilities.addRegister(user, "user");
				result = true;

			} catch (MongoWriteException e) {

				if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
					log.error("Duplicate user");
					result = false;
				}
				throw e;
			}
		}
		return result;
	}
}
