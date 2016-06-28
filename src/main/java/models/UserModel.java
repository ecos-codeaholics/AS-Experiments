package models;

import org.bson.Document;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import controllers.Encode;
import org.bson.conversions.Bson;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

        private final MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();

        public boolean addUser(String name, String lastName, String password, String email) {
        boolean result = false;
               
        System.out.println(name+" "+lastName+" "+password+" "+email);
        String pass[] = Encode.encodeGenerator(password);
                
        Document user = new Document();
        user.append("email", email);
        user.append("password", pass[1]);
        user.append("salt", pass[0]);
        user.append("name", name);
        user.append("last-name", lastName);
        
        Bson filter = new Document("email",email);
        Document userAlreadyExist = db.getCollection("user").find(filter).first();
        
        if (userAlreadyExist==null) {
        
        try {
          
            MongoCollection<Document> userCollection = db.getCollection("user");
            System.out.println("Guardando Usuario");
            userCollection.insertOne(user);
            result = true;
        } catch (MongoWriteException e) {

            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Email address already in use: " + email);
                result = false;
            }
            throw e;

        }
        }
        else {
          System.out.println("User Already Exist");
        }
        return result;
    }
}
