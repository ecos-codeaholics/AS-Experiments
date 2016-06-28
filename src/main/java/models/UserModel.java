package models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;

import codeAholics.Utilities;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

	//Atributos
	private final static Logger log = LogManager.getLogger(UserModel.class);
	
	// Metodos
	/***
	 * Crea el obejto docuemento ususario y lo alamacena el la coleeccion de ususarios.
	 * 
	 * @param pName correo del ususario
	 * @param pLastName contraeeña del ususario
	 * @param pPassword contraeeña del ususario
	 * @param pEmail contraeeña del ususario
	 * @return resultado de la operacion 
	 */
	public boolean addUser(String pName, String pLastName, String pPassword, String pEmail)  {

		log.debug("Datos Recibidos : " + pName + " " + pLastName + " " + pPassword + " " + pEmail);
		String hiddenPwd = Utilities.getHash(pPassword);
		Document user = new Document();
		user.append("email", pEmail);
		user.append("password", hiddenPwd);
		user.append("name", pName);
		user.append("last-name", pLastName);
		log.info("Creando Usuario: " + pName + pLastName);
		
		try {
			Utilities.addRegister(user, "user");
			return true;
		} catch (MongoWriteException e) {

			if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
				log.error("Email address already in use:  : " + pEmail);
				return false;
			}
			throw e;
		}
	}

}
