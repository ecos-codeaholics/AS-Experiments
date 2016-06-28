//	//Se verifica los permisos para el paciente
//	public static boolean autPatients (String pEmail, String pPwd){
//		//se consulta en la bd si el paciente existe
//		boolean authenticated = false;
//		Document user = new Document();
//        user.append("email", pEmail);
//        user.append("password", pPwd);
//        
//      Document first = userCollection.find().first();
//    

package codeAholics;


import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import config.DatabaseSingleton;
import controllers.Encode;
import org.bson.conversions.Bson;

public final class Authentication {
	
	private static MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();
	
	//Se verifica los permisos para el paciente
	public static boolean autPatients (String pEmail, String pPwd){
		//se consulta en la bd si el paciente existe
		boolean authenticated = false;
		Document user = new Document();
        user.append("email", pEmail);
        user.append("password", pPwd);
        
        Bson filter = new Document("email", pEmail);
        Document result = (Document) db.getCollection("user").find(filter).first();
        
        if (result == null){
          System.out.println("User Doesn't Exist");
        }
        else {
        String resultString = result.get("password").toString();
        String resultStringS = result.get("salt").toString();    
        //hacer metodo del hash con estos valores para compararlo con el almacenado enl a base de datos
        String pass = Encode.encodeGeneratorWithSalt(pPwd, resultStringS);
          System.out.println(pass+" "+resultString);
        if (pass.equals(resultString)){
            System.out.println("autenticacion exitosa");
        	createSesion(pEmail);
        	authenticated = true;
        }
        else {
            System.out.println("Password Incorrecto");
        }
        }
        return authenticated;
	}
	
	// Se verifican permisos para el doctor
	public static boolean autDocs (String pUser, String pPwd){
		// se consulta en la bd si el doc exite
		return true;
	}
	
	private static void createSesion (String pEmail){
		Document sesion = new Document();
		sesion.append("email", pEmail);
		try {
            MongoCollection<Document> userCollection = db.getCollection("sesion");

            System.out.println("Creando Sesion");
            userCollection.insertOne(sesion);
        } catch (MongoWriteException e) {

            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Already exist sesion for user: " + pEmail);
            }
            throw e;

        }
	}
	
	private static void closedSesion(String pEmail){
		
		db.getCollection("sesion").deleteMany(new Document("email", pEmail));
	}
	
	

}