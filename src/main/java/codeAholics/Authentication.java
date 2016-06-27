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

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import config.DatabaseSingleton;

public final class Authentication {
	
	private static MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();
	
	//Se verifica los permisos para el paciente
	public static boolean autPatients (String pEmail, String pPwd){
		//se consulta en la bd si el paciente existe
		boolean authenticated = false;
		Document user = new Document();
        user.append("email", pEmail);
        user.append("password", pPwd);
        
        Document result = db.getCollection("user").find().first();
        System.out.println("test"+result);
                
    	FindIterable<Document> query = db.getCollection("user").find(user);
    	
        
        ArrayList<Document> results = new ArrayList<Document>();
        for (Document document : query) {
            results.add(document);
        }
        if(results.size() > 0){
        	System.out.println("autenticacion exitosa");
        	createSesion(pEmail);
        	authenticated = true;
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