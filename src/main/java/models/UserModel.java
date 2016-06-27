package models;

import org.bson.Document;
import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.DatabaseSingleton;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by snaphuman on 6/6/16.
 */
public class UserModel {

        private final MongoDatabase db = DatabaseSingleton.getInstance().getDatabase();

        public boolean addUser(String name, String lastName, String password, String email) {
        
        String salt = String.valueOf(UUID.randomUUID());
        System.out.println(salt);
        String saltAndPassword = salt+password;
        System.out.println(saltAndPassword);
 
        
                    /**
             * Encode Password
             */
            
          try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltAndPassword.getBytes(),0,saltAndPassword.length());
            System.out.println("MD5: "+new BigInteger(1,md.digest()).toString(16));
            password = new BigInteger(1,md.digest()).toString(16);
                        
            
          } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
          }
          
                      /**
             * Encode Password
             */
          
          
          
        
        
        System.out.println(name+" "+lastName+" "+password+" "+email);
        
        
        

        Document user = new Document();
        user.append("email", email);
        user.append("password", password);
        user.append("salt", salt);
        user.append("name", name);
        user.append("last-name", lastName);

        try {
            MongoCollection<Document> userCollection = db.getCollection("user");

            System.out.println("Guardando Usuario");
            userCollection.insertOne(user);
            return true;
        } catch (MongoWriteException e) {

            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("Email address already in use: " + email);
                return false;
            }
            throw e;

        }
    }
        
        
    
}
