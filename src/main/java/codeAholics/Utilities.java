package codeAholics;

import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by davidMtz on 27/6/16.
 */
public final class Utilities {
	
	// Atributos
	private final static Logger log = LogManager.getLogger(Utilities.class);
	
	private static MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();
	
	// Metodos
	/***
	 * Convierte un arreglo de bytes a String usando valores hexadecimales
	 * 
	 * @param digest arreglo de bytes a convertir
	 * @return String creado a partir de digest
	 */
	private static String toHexadecimal(byte[] digest) {
		
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1)
				hash += "0";
			hash += Integer.toHexString(b);
		}
		return hash;
	}

	/***
	 * Encripta una cadena mediante algoritmo de resumen de mensaje.
	 * 
	 * @param pPwd texto a encriptar
	 * @return mensaje encriptado
	 */
	public static String[] getHash(String pPwd, String pSalt) {

		if (pSalt.equals("")) {
			pSalt = getSalt();
		}
		String saltedPwd = pSalt + pPwd;
		String[] result = new String[2];
		result[0] = pSalt;
		byte[] digest = null;
		byte[] buffer = saltedPwd.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException ex) {
			log.error("Error creating hash");
		}
		result[1] = toHexadecimal(digest);
		return result;
	}
	
	/***
	 * Genera el salt para poder encriptar.
	 * 
	 * @return salt de encriptacion
	 */
	private static String getSalt() {

		String salt = String.valueOf(UUID.randomUUID());
		return salt;
	}
	
	/***
	 * Adiciona el Documentos en la coleccion especificada.
	 * 
	 * @param pRegister registro que se desea adicionar
	 * @param pCollection colection de destino
	 */
	public static void addRegister(Document pRegister, String pCollection) throws MongoWriteException {
		
		log.debug("Saving " + pRegister);
		log.debug("In Collection " + pCollection);
		MongoCollection<Document> collection = db.getCollection(pCollection);
		collection.insertOne(pRegister);
		log.info("-----------------------------------");
		log.info("Successful Process");
		log.info("-----------------------------------");
	}
	
	/***
	 * Busca todos los documentos de iguales atributos que el filtro.
	 * 
	 * @param pFilter docuemento patron de l busqueda
	 * @param pCollection colection donde se va a realizar la busqueda
	 */
	public static ArrayList<Document> findRegisters(Document pFilter, String pCollection) {

		FindIterable<Document> query = db.getCollection(pCollection).find(pFilter);
		ArrayList<Document> results = new ArrayList<Document>();
		for (Document document : query) {
			results.add(document);
		}
		return results;
	}

}
